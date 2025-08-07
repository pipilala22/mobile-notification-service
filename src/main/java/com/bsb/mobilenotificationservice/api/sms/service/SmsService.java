package com.bsb.mobilenotificationservice.api.sms.service;

import static com.bsb.mobilenotificationservice.common.constants.Constants.LIMIT_MINUTE_FOR_SEND_SMS;
import static com.bsb.mobilenotificationservice.common.constants.Constants.PWD_TEXT_FOR_SMS;

import com.bsb.mobilenotificationservice.api.sms.entity.sms.Sms;
import com.bsb.mobilenotificationservice.api.sms.entity.sms.SmsType;
import com.bsb.mobilenotificationservice.api.sms.repository.SmsCodeRepository;
import com.bsb.mobilenotificationservice.common.logger.dto.Log;
import com.bsb.mobilenotificationservice.common.messenger.MessengerStrategy;
import com.bsb.mobilenotificationservice.common.messenger.Messengers;
import com.bsb.mobilenotificationservice.common.utils.CommonUtils;
import com.bsb.mobilenotificationservice.common.utils.SmsCodeGenerationUtils;
import com.bsb.mobilenotificationservice.exception.SmsCodeLimitMinutesException;
import com.bsb.mobilenotificationservice.exception.SmsSendingToTopikFailedException;
import com.bsb.mobilenotificationservice.service_api.user_data_api.UserDataServiceApi;
import com.bsb.mobilenotificationservice.service_api.user_data_api.payload.UserDataResponse;
import com.bsb.notification.dto.email.Email;
import com.bsb.notification.dto.viber.Viber;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.KafkaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class SmsService {

  private final CommonUtils commonUtils;
  private final SmsCodeRepository smsCodeRepository;
  private final TransactionTemplate transactionTemplate;
  private final SmsCodeGenerationUtils smsCodeGenerationUtils;
  private final Map<Messengers, MessengerStrategy> messengerStrategies;
  //private final UserDataServiceApi userDataServiceApi;

  @Transactional
  public CompletableFuture<Void> sendSmsCode(Long userId, String sessionId, SmsType smsType,
      Messengers messenger, Log reglog) {
    checkLastActualSmsCodeIfExist(userId, sessionId);
    String code = smsCodeGenerationUtils.generateCode();
    var request = buildRequest(userId, code, messenger, smsType);
    Sms newCode = buildSms(userId, sessionId, smsType, code, messenger);
    return CompletableFuture.runAsync(() -> {
      transactionTemplate.execute(_ -> {
        smsCodeRepository.detachOldSmsCode(userId, sessionId);
        return null;
      });

      try {
        MessengerStrategy strategy = messengerStrategies.get(messenger);
        strategy.send(request).get();
        transactionTemplate.execute(_ -> {
          smsCodeRepository.save(newCode);
          return null;
        });
      } catch (KafkaException | ExecutionException | InterruptedException e) {
        log.error("{} sending failed", messenger, e);
        throw new CompletionException(new SmsSendingToTopikFailedException());
      }
    });
  }

  @Transactional
  public boolean validate(Long userId, String sessionId, String codeValue) {
    Optional<Sms> smsCode = smsCodeRepository.findActualSmsCode(userId, sessionId, codeValue);
    if (smsCode.isPresent()) {
      smsCode.get().setIsChecked(true);
      smsCode.get().setIsDetached(true);
      smsCodeRepository.save(smsCode.get());
      return true;
    }
    return false;
  }


  private Object buildRequest(Long userId, String code, Messengers messenger, SmsType smsType) {
    String phoneNumber = getPhoneNumber(userId);
    String emailAddress = getEmailAddress(userId);
    if (messenger == Messengers.VIBER || messenger == Messengers.SMS) {
      return buildViber(PWD_TEXT_FOR_SMS + code, PWD_TEXT_FOR_SMS + code,
          commonUtils.addPlusToPhoneNumber(phoneNumber));
    } else if (messenger == Messengers.EMAIL) {
      return buildEmail(emailAddress, PWD_TEXT_FOR_SMS + code, smsType.getName());
    } else if (messenger == Messengers.PUSH) {
      return null;//не знаю как составлять тело пуша для теста, пожтому реализация которая сейчас тут, просто возвращает null
    }
    return null;
  }

  private Viber buildViber(String text, String altText, String phone) {
    var smsSingle = new Viber();
    smsSingle.setText(text);
    smsSingle.setAlternativeSmsText(altText);
    smsSingle.setPhoneNumber(phone);
    smsSingle.setSendSmsWhenNotDelivered(Boolean.FALSE);
    smsSingle.setAdvertising(Boolean.FALSE);
    smsSingle.setSmsOnly(Boolean.TRUE);
    return smsSingle;
  }

  private Email buildEmail(String email, String body, String smsType) {
    var smsSingle = new Email();
    smsSingle.setEmailAddress(email);
    smsSingle.setBody(body);
    smsSingle.setSubject(smsType);
    return smsSingle;
  }


  private Sms buildSms(Long userId, String sessionId, SmsType type, String code,
      Messengers messenger) {
    return Sms.builder().type(type).codeValue(code).isChecked(false).isDetached(false)
        .recipientUserId(userId).sessionId(sessionId).messenger(messenger).build();
  }

  private String getPhoneNumber(Long userId) {
//    UserDataResponse userDataResponse = userDataServiceApi.findById(userId);тут не получилось достучаться к user-data(путь не определен), для теста я это не делал
//    return userDataResponse.getPhoneNumber().toString();
    return "375291625641";//тут как я понял надо брать из user-data, для теста заглушка
  }

  private void checkLastActualSmsCodeIfExist(Long userId, String sessionId) {
    var lastActualSmsCode = smsCodeRepository.findLastActualSmsCode(userId, sessionId);
    if (lastActualSmsCode.isPresent() && lastActualSmsCode.get().getCreatedAt()
        .plusMinutes(LIMIT_MINUTE_FOR_SEND_SMS).isAfter(LocalDateTime.now())) {
      throw new SmsCodeLimitMinutesException();
    }
  }

  private String getEmailAddress(Long userId) {
    return "saveliyrazdymakho@gmail.com";//так же как и с телефоном
  }


}

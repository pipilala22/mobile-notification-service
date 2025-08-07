package com.bsb.mobilenotificationservice.api.sms.controller;

import static com.bsb.mobilenotificationservice.common.constants.LogMessages.FAILED_SEND_CODE;
import static com.bsb.mobilenotificationservice.common.constants.LogMessages.SEND_CODE;
import static com.bsb.mobilenotificationservice.common.constants.LogMessages.START_SEND_CODE;
import static com.bsb.mobilenotificationservice.common.constants.LogMessages.START_VALIDATE_CODE_RESPONSE;
import static com.bsb.mobilenotificationservice.common.constants.LogMessages.SUCCESS_SEND_CODE;
import static com.bsb.mobilenotificationservice.common.constants.LogMessages.SUCCESS_VALIDATE_CODE_RESPONSE;
import static com.bsb.mobilenotificationservice.common.constants.LogMessages.VALIDATE_CODE_RESPONSE;
import static java.lang.String.format;

import com.bsb.mobilenotificationservice.api.sms.CheckSmsCodeRequest;
import com.bsb.mobilenotificationservice.api.sms.entity.sms.SmsType;
import com.bsb.mobilenotificationservice.api.sms.service.SmsService;
import com.bsb.mobilenotificationservice.common.logger.service.LogService;
import com.bsb.mobilenotificationservice.common.messenger.Messengers;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/notification-management/sms")
public class SmsController {

  private final SmsService smsCodeService;
  private final LogService logService;
  private final HttpMessageConverters messageConverters;

  @PostMapping("/send/code/{messenger}/{type}")
  public CompletableFuture<ResponseEntity<Object>> sendSmsCode(
      @RequestHeader("User-id") Long userId,
      @RequestHeader(name = "Session-id") String sessionId, @PathVariable String type,
      @PathVariable String messenger) {

    var regLog = logService.createLog(UUID.randomUUID(), format(SEND_CODE, messenger));
    logService.info(regLog.update(format(START_SEND_CODE, messenger, userId, type)));
    return smsCodeService.sendSmsCode(userId, sessionId, SmsType.valueOf(type),
            Messengers.valueOf(messenger), regLog)
        .thenApply(_ -> {
          logService.info(regLog.update(format(SUCCESS_SEND_CODE, messenger, userId, type)));
          return ResponseEntity.ok().build();
        })
        .exceptionally(_ -> {
          logService.error(regLog.update(format(FAILED_SEND_CODE, messenger, userId, type)));
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        });
  }

  @PostMapping("/validate")
  public ValidateSmsResponse validate(@RequestHeader("User-id") Long userId,
      @RequestHeader(name = "Session-id") String sessionId,
      @RequestBody CheckSmsCodeRequest codeValue) {
    var regLog = logService.createLog(UUID.randomUUID(), VALIDATE_CODE_RESPONSE);
    logService.info(regLog.update(format(START_VALIDATE_CODE_RESPONSE, userId)));
    ValidateSmsResponse validateSmsResponse = new ValidateSmsResponse(
        smsCodeService.validate(userId, sessionId, codeValue.getCodeValue()));
    logService.info(regLog.update(format(SUCCESS_VALIDATE_CODE_RESPONSE, userId)));
    return validateSmsResponse;
  }


}

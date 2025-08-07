package com.bsb.mobilenotificationservice.common.kafka.consumer;

import com.bsb.mobilenotificationservice.common.kafka.TopicsSource;
import com.bsb.mobilenotificationservice.service_api.notification_api.NotificationServiceApiImpl;
import com.bsb.notification.dto.email.Email;
import com.bsb.notification.dto.viber.Viber;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ConsumerService {
  private static final ObjectMapper objectMapper = new ObjectMapper();
  private final NotificationServiceApiImpl notificationService;
  private final TopicsSource topicsSource;

  @KafkaListener(topics = "#{topicsSource.smsSingle}")
  public void consumeSmsSingle(String message) {
    try {
      Viber request = objectMapper.readValue(message, Viber.class);
      notificationService.sendSmsSingle(request);
      log.info("SMS processed to NotificationService for {}", request.getPhoneNumber());
    } catch (Exception e) {
      log.error("Failed to process SMS from Kafka. Message: {}", message, e);
    }
  }

  @KafkaListener(topics = "#{topicsSource.viberSingle}")
  public void consumeViberSingle(String message) {
    try {
      Viber request = objectMapper.readValue(message, Viber.class);
      notificationService.sendViberSingle(request,true);
      log.info("ViberSms processed to NotificationService for {}", request.getPhoneNumber());
    } catch (Exception e) {
      log.error("Failed to process ViberSms from Kafka. Message: {}", message, e);
    }

  }

  @KafkaListener(topics = "#{topicsSource.emailSingle}")
  public void consumeEmailSingle(String message) {
    try {
      Email request = objectMapper.readValue(message, Email.class);
      notificationService.sendEmailSingle(request);
      log.info("Email processed to NotificationService for {}", request.getEmailAddress());
    } catch (Exception e) {
      log.error("Failed to process ViberSms from Kafka. Message: {}", message, e);
    }
  }
}
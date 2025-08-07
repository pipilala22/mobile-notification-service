package com.bsb.mobilenotificationservice.common.kafka;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class TopicsSource {

  @Value("${spring.kafka.topics.notification-single-sms}")
  private String smsSingle;

  @Value("${spring.kafka.topics.notification-single-viber}")
  private String viberSingle;

  @Value("${spring.kafka.topics.notification-single-email}")
  private String emailSingle;

  @Value("${spring.kafka.topics.notification-single-push}")
  private String pushSingle;

}
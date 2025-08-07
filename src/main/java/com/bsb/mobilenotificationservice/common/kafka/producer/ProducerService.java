package com.bsb.mobilenotificationservice.common.kafka.producer;

import com.bsb.mobilenotificationservice.common.kafka.TopicsSource;
import com.bsb.notification.dto.email.Email;
import com.bsb.notification.dto.push.Push;
import com.bsb.notification.dto.viber.Viber;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProducerService {
  private final KafkaTemplate<String, Object> kafkaTemplate;
  private final TopicsSource topicsSource;

  public CompletableFuture<Boolean> sendSmsSingle(Viber request) {
    return sendMessage(topicsSource.getSmsSingle(), request, "SMSSingle");
  }

  public CompletableFuture<Boolean> sendViberSingle(Viber request) {
    return sendMessage(topicsSource.getViberSingle(), request, "ViberSingle");
  }

  public CompletableFuture<Boolean> sendEmailSingle(Email request) {
    return sendMessage(topicsSource.getEmailSingle(), request, "EmailSingle");
  }

  public CompletableFuture<Boolean> sendPushSingle(Push request) {
    return sendMessage(topicsSource.getPushSingle(), request, "PushSingle");
  }

  private CompletableFuture<Boolean> sendMessage(String topic, Object request, String messageType) {
    CompletableFuture<Boolean> sending = new CompletableFuture<>();

    kafkaTemplate.send(topic, request)
        .addCallback(
            _ -> {
              log.info("{} sent to Kafka topic {}", messageType, topic);
              sending.complete(true);
            },
            ex -> {
              log.error("Failed to send {} to Kafka", messageType, ex);
              sending.complete(false);
            }
        );

    return sending;
  }

}
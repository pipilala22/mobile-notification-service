package com.bsb.mobilenotificationservice.common.messenger.service;

import com.bsb.mobilenotificationservice.common.kafka.producer.ProducerService;
import com.bsb.mobilenotificationservice.common.messenger.MessengerStrategy;
import com.bsb.notification.dto.email.Email;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailMessengerStrategy implements MessengerStrategy<Email> {

  private final ProducerService producerService;

  @Override
  public CompletableFuture<Boolean> send(Email request) {
    return producerService.sendEmailSingle(request);
  }

  @Override
  public String getMessengerType() {
    return "EMAIL";
  }
}

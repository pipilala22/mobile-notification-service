package com.bsb.mobilenotificationservice.common.messenger.service;

import com.bsb.mobilenotificationservice.common.kafka.producer.ProducerService;
import com.bsb.mobilenotificationservice.common.messenger.MessengerStrategy;
import com.bsb.notification.dto.email.Email;
import com.bsb.notification.dto.push.Push;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PushMessengerStrategy implements MessengerStrategy<Push> {

  private final ProducerService producerService;

  @Override
  public CompletableFuture<Boolean> send(Push request) {
    return producerService.sendPushSingle(request);
  }

  @Override
  public String getMessengerType() {
    return "PUSH";
  }
}

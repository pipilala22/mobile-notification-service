package com.bsb.mobilenotificationservice.common.messenger.service;

import com.bsb.mobilenotificationservice.common.kafka.producer.ProducerService;
import com.bsb.mobilenotificationservice.common.messenger.MessengerStrategy;
import com.bsb.notification.dto.viber.Viber;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ViberMessengerStrategy implements MessengerStrategy<Viber> {
  private final ProducerService producerService;

  @Override
  public CompletableFuture<Boolean> send(Viber request) {
    return producerService.sendViberSingle(request);
  }

  @Override
  public String getMessengerType() {
    return "VIBER";
  }
}
package com.bsb.mobilenotificationservice.config.messenger;

import com.bsb.mobilenotificationservice.common.messenger.MessengerStrategy;
import com.bsb.mobilenotificationservice.common.messenger.Messengers;
import com.bsb.mobilenotificationservice.common.messenger.service.EmailMessengerStrategy;
import com.bsb.mobilenotificationservice.common.messenger.service.PushMessengerStrategy;
import com.bsb.mobilenotificationservice.common.messenger.service.SmsMessengerStrategy;
import com.bsb.mobilenotificationservice.common.messenger.service.ViberMessengerStrategy;
import java.util.EnumMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessengerConfig {
  @Bean
  public Map<Messengers, MessengerStrategy> messengerStrategies(
      EmailMessengerStrategy emailMessengerStrategy,
      PushMessengerStrategy pushMessengerStrategy,
      SmsMessengerStrategy smsStrategy,
      ViberMessengerStrategy viberStrategy) {
    Map<Messengers, MessengerStrategy> strategies = new EnumMap<>(Messengers.class);
    strategies.put(Messengers.SMS, smsStrategy);
    strategies.put(Messengers.VIBER, viberStrategy);
    strategies.put(Messengers.EMAIL,emailMessengerStrategy);
    strategies.put(Messengers.PUSH, pushMessengerStrategy);
    return strategies;
  }
}

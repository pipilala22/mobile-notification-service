package com.bsb.mobilenotificationservice.common.messenger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Messengers {

  SMS("SMS"),
  VIBER("VIBER"),
  PUSH("PUSH"),
  EMAIL("EMAIL");


  private final String name;
}

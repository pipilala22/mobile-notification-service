package com.bsb.mobilenotificationservice.common.model.entity;


import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumber {

  private String phoneCountryCode;
  private String phoneZoneCode;
  private String phoneNumber;

  public String createPhoneNumberString() {
    return phoneCountryCode + phoneZoneCode + phoneNumber;
  }

}

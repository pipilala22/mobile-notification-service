package com.bsb.mobilenotificationservice.common.model.dto;


import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class PhoneNumberDto {

  private String phoneCountryCode;
  private String phoneZoneCode;
  private String phoneNumber;
}

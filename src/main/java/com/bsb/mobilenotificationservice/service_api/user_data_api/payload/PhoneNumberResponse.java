package com.bsb.mobilenotificationservice.service_api.user_data_api.payload;

import com.bsb.mobilenotificationservice.common.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhoneNumberResponse {

  private String phoneCountryCode;
  private String phoneZoneCode;
  private String phoneNumber;

  @Override
  public String toString() {
    return String.format(Constants.FULL_PHONE_NUMBER_TEMPLATE, phoneCountryCode,
        phoneZoneCode, phoneNumber);
  }
}

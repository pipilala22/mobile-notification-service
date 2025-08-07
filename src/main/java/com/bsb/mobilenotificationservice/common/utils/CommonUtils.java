package com.bsb.mobilenotificationservice.common.utils;

import static com.bsb.mobilenotificationservice.common.constants.Constants.BEGIN_COUNTRY_CODE_INDEX;
import static com.bsb.mobilenotificationservice.common.constants.Constants.BEGIN_PHONE_NUMBER_INDEX;
import static com.bsb.mobilenotificationservice.common.constants.Constants.BEGIN_ZONE_CODE_INDEX;
import static com.bsb.mobilenotificationservice.common.constants.Constants.END_COUNTRY_CODE_INDEX;
import static com.bsb.mobilenotificationservice.common.constants.Constants.END_PHONE_NUMBER_INDEX;
import static com.bsb.mobilenotificationservice.common.constants.Constants.END_ZONE_CODE_INDEX;
import static com.bsb.mobilenotificationservice.common.constants.Constants.PLUS;

import com.bsb.mobilenotificationservice.common.model.dto.PhoneNumberDto;
import com.bsb.mobilenotificationservice.common.model.entity.PhoneNumber;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CommonUtils {


  public String dropPlusFromPhoneNumber(String phoneNumber) {
    if (phoneNumber.startsWith(PLUS)) {
      phoneNumber = phoneNumber.substring(1);
    }
    return phoneNumber;
  }

  public String addPlusToPhoneNumber(String phoneNumber) {
    if (!phoneNumber.startsWith(PLUS)) {
      phoneNumber = PLUS + phoneNumber;
    }
    return phoneNumber;
  }

  public PhoneNumber getPhoneNumber(String phone) {
    phone = dropPlusFromPhoneNumber(phone);
    return PhoneNumber.builder()
        .phoneCountryCode(phone.substring(BEGIN_COUNTRY_CODE_INDEX, END_COUNTRY_CODE_INDEX))
        .phoneZoneCode(phone.substring(BEGIN_ZONE_CODE_INDEX, END_ZONE_CODE_INDEX))
        .phoneNumber(phone.substring(BEGIN_PHONE_NUMBER_INDEX, END_PHONE_NUMBER_INDEX))
        .build();
  }

  public PhoneNumberDto getPhoneNumberDtoFromString(String phone) {
    phone = dropPlusFromPhoneNumber(phone);
    return PhoneNumberDto.builder()
        .phoneCountryCode(phone.substring(BEGIN_COUNTRY_CODE_INDEX, END_COUNTRY_CODE_INDEX))
        .phoneZoneCode(phone.substring(BEGIN_ZONE_CODE_INDEX, END_ZONE_CODE_INDEX))
        .phoneNumber(phone.substring(BEGIN_PHONE_NUMBER_INDEX, END_PHONE_NUMBER_INDEX))
        .build();
  }


}

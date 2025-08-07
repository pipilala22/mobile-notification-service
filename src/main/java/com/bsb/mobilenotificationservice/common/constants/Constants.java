package com.bsb.mobilenotificationservice.common.constants;

import static lombok.AccessLevel.PRIVATE;

import java.util.Map;
import lombok.NoArgsConstructor;

/**
 * String constants for application.
 */
@NoArgsConstructor(access = PRIVATE)
public final class Constants {

  public static final long LIMIT_MINUTE_FOR_SEND_SMS = 1L;


  public static final String PWD_TEXT_FOR_SMS = "password: ";

  public static final String FULL_NAME_TEMPLATE = "%s %s %s";
  public static final String FULL_NAME_LAT_TEMPLATE = "%s %s";

  public static final String PLUS = "+";
  public static final int END_ZONE_CODE_INDEX = 5;
  public static final int BEGIN_ZONE_CODE_INDEX = 3;
  public static final int END_COUNTRY_CODE_INDEX = 3;
  public static final int END_PHONE_NUMBER_INDEX = 12;
  public static final int BEGIN_COUNTRY_CODE_INDEX = 0;
  public static final int BEGIN_PHONE_NUMBER_INDEX = 5;


  public static final String FIRST_NAME_AND_MIDDLE_NAME_TEMPLATE = "%s %s";
  public static final String FULL_PHONE_NUMBER_TEMPLATE = "%s%s%s";
}

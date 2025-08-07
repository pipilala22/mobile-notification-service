package com.bsb.mobilenotificationservice.common.constants;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

@NoArgsConstructor(access = PRIVATE)
public class LogMessages {


  /**
   * Log messages
   */
  public static final String SEND_CODE = "SEND_%s_CODE";
  public static final String START_SEND_CODE = "Start send %s code for userId = %s, type = %s";
  public static final String SUCCESS_SEND_CODE = "Success send %s code for userId = %s, type = %s";
  public static final String FAILED_SEND_CODE = "Failed send %s code for userId = %s, type = %s";
  public static final String VALIDATE_CODE_RESPONSE = "VALIDATE_CODE_RESPONSE";
  public static final String START_VALIDATE_CODE_RESPONSE = "Start validate sms code for user id = %s";
  public static final String SUCCESS_VALIDATE_CODE_RESPONSE = "Success validate sms code for user id = %s";
  public static final String DASHBOARD = "%s_DASHBOARD";

}

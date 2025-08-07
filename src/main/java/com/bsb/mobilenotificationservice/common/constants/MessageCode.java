package com.bsb.mobilenotificationservice.common.constants;

import static lombok.AccessLevel.PRIVATE;

import lombok.NoArgsConstructor;

/**
 * Error codes.
 */
@NoArgsConstructor(access = PRIVATE)
public final class MessageCode {

  public static final String PHONE_NUMBER_NOT_FOUND = "MUDS-0004";
  public static final String ENTITY_NOT_FOUND = "MUDS-0006c";
  public static final String CLIENT_NOT_FOUND_IN_RETAIL = "MUDS-0006r";
  public static final String NOT_VALID = "MUDS-0022c";
  public static final String CODE_NOT_VALID = "MUDS-0046";
  public static final String SMS_CODE_LIMIT_MINUTES = "MUDS-0047";
  public static final String CLIENT_DOES_NOT_COMPANY = "MUDS-0054"; //400 Bad Request
  public static final String UNEXPECTED = "UNEXPECTED";
  public static final String INVALID_APPLICATION_IMAGE_CODE = "MUDS-057";
  public static final String COMMENT_CANNOT_BE_EDITED_BY_ANOTHER_EXECUTOR = "MCS-0118";
  public static final String ERIP_ID_CHANGE_ATTEMPTS_LIMIT = "MUDS-059";
}

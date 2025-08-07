package com.bsb.mobilenotificationservice.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.bsb.mobilenotificationservice.common.constants.MessageCode;
import org.springframework.http.HttpStatus;

public class SmsCodeLimitMinutesException extends MobileAppException {

  private static final String DEFAULT_MESSAGE = "SMS code is sent no more than once per minute";

  public SmsCodeLimitMinutesException() {
    super(DEFAULT_MESSAGE);
  }

  public SmsCodeLimitMinutesException(final String message) {
    super(message);
  }

  @Override
  public String getErrorCode() {
    return MessageCode.SMS_CODE_LIMIT_MINUTES;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return BAD_REQUEST;
  }

}

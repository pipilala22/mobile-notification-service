package com.bsb.mobilenotificationservice.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import com.bsb.mobilenotificationservice.common.constants.MessageCode;
import org.springframework.http.HttpStatus;

public class SmsCodeNotValidException extends MobileAppException {

  private static final String DEFAULT_MESSAGE = "Sms code not valid";

  public SmsCodeNotValidException() {
    super(DEFAULT_MESSAGE);
  }

  public SmsCodeNotValidException(final String message) {
    super(message);
  }

  @Override
  public String getErrorCode() {
    return MessageCode.CODE_NOT_VALID;
  }

  @Override
  public HttpStatus getHttpStatus() {
    return NOT_FOUND;
  }

}

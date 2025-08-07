package com.bsb.mobilenotificationservice.exception;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.HttpStatus;

public class SmsSendingToTopikFailedException extends MobileAppException {

  private static final String DEFAULT_MESSAGE = "Sms was not sent";

  public SmsSendingToTopikFailedException() {super(DEFAULT_MESSAGE);}

  public SmsSendingToTopikFailedException(String message) {super(message);}

  @Override
  public HttpStatus getHttpStatus() {
    return INTERNAL_SERVER_ERROR;
  }
}

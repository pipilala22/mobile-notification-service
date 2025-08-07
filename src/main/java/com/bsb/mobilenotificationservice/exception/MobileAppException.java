package com.bsb.mobilenotificationservice.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import com.bsb.mobilenotificationservice.common.constants.MessageCode;
import com.bsb.mobilenotificationservice.exception.model.FieldError;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Base class for application business exceptions.
 * <p>
 * Business exception can be expected to be handled or just rethrown higher.
 */
@Getter
@Setter
public class MobileAppException extends RuntimeException {

  private String header;
  private String message;
  private final List<? super Serializable> params = new ArrayList<>();

  private final transient Collection<FieldError> fieldErrors = new HashSet<>();

  public MobileAppException(final String message) {
    super(message);
  }

  public MobileAppException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public MobileAppException(final String message, final List<? extends Serializable> params) {
    this(message);
    this.params.addAll(params);
  }

  /**
   * Error code getter.
   *
   * @return string error code.
   */
  public String getErrorCode() {
    return MessageCode.UNEXPECTED;
  }

  /**
   * Http status getter.
   *
   * @return http status.
   */
  public HttpStatus getHttpStatus() {
    return BAD_REQUEST;
  }
}

package com.bsb.mobilenotificationservice.exception.model;

import com.bsb.mobilenotificationservice.exception.MobileAppException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data transfer object that contains information for a client about exception.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL)
public class ErrorInfo {

  private String header;
  private String code;
  private String message;
  @JsonInclude(Include.NON_EMPTY)
  private Collection<FieldError> fieldErrors;

  public ErrorInfo(final MobileAppException exception) {
    this.header = exception.getHeader();
    this.code = exception.getErrorCode();
    this.message = exception.getMessage();
    this.fieldErrors = exception.getFieldErrors();
  }
}

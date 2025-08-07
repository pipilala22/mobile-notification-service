package com.bsb.mobilenotificationservice.exception.model;

import java.io.Serializable;
import lombok.Data;

/**
 * Representation error field.
 */
@Data
public final class FieldError implements Serializable {

  private String fieldName;
  private String errorType;
}

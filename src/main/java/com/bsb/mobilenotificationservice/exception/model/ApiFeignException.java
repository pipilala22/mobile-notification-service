package com.bsb.mobilenotificationservice.exception.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiFeignException extends RuntimeException {

  private String header;
  private String code;
  private String message;
  private Map<Object, Object> payload;
  private Integer status;
}

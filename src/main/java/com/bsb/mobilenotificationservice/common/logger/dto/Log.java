package com.bsb.mobilenotificationservice.common.logger.dto;

import static java.time.LocalDateTime.now;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Log dto.
 */
@Data
@AllArgsConstructor
public class Log {

  private Object logId;

  private String logMsg;

  private String logType;

  private LocalDateTime logTime;

  private Object entities;

  public Log(final Object logId, final String logType) {
    this.logId = logId;
    this.logType = logType;
  }

  public Log update(final String message, Object entities) {
    this.logMsg = message;
    this.logTime = now();
    this.entities = entities;
    return this;
  }

  public Log update(final String message) {
    this.logMsg = message;
    this.logTime = now();
    return this;
  }
}

package com.bsb.mobilenotificationservice.common.logger.service;


import com.bsb.mobilenotificationservice.common.logger.dto.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

/**
 * Log service.
 */
@Log4j2
@Service
@RequiredArgsConstructor
public class LogService {

  private final ObjectMapper objectMapper;

  /**
   * Method for logging.
   *
   * @param logDto {@link Log} dto.
   */
  public void info(final Log logDto) {
    log.info(getJsonStr(logDto));
  }

  public void warn(final Log logDto) {
    log.warn(getJsonStr(logDto));
  }

  public void error(final Log logDto) {
    log.error(getJsonStr(logDto));
  }

  /**
   * Method for create {@link Log} dto.
   *
   * @param logId init logId.
   * @return configured {@link Log} dto.
   */
  public Log createLog(final Object logId, final String logType) {
    return new Log(logId, logType);
  }

  /**
   * Method for convert {@link Object} to json string representation.
   *
   * @param obj {@link Object} entity for convert.
   * @return json string representation for {@link Object}.
   */
  @SneakyThrows
  private String getJsonStr(final Object obj) {
    var string = objectMapper.writeValueAsString(obj);
    return string.replace('\n', '_').replace('\r', '_');
  }
}
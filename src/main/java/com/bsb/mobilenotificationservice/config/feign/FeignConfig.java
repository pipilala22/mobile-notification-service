package com.bsb.mobilenotificationservice.config.feign;

import feign.RequestInterceptor;
import java.util.Enumeration;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RequiredArgsConstructor
public class FeignConfig {

  @Bean
  public RequestInterceptor headerPropagationInterceptor() {
    return template -> {
      ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
      if (requestAttributes != null) {
        HttpServletRequest currentRequest = requestAttributes.getRequest();
        Enumeration<String> headerNames = currentRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
          String headerName = headerNames.nextElement();
          if (StringUtils.equalsIgnoreCase(headerName, HttpHeaders.ACCEPT_LANGUAGE)) {
            template.header(headerName, currentRequest.getHeader(headerName));
          }
        }
      }
    };
  }
}
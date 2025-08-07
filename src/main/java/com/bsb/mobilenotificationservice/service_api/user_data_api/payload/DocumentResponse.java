package com.bsb.mobilenotificationservice.service_api.user_data_api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentResponse {

  private Long id;

  private String type;
  private Integer countryId;
  private String series;
  private String number;
  private String authority;
  private Integer authorityCode;
  private LocalDate issueDate;
  private LocalDate expireDate;
  private String personalNumber;
  private Boolean active;
}

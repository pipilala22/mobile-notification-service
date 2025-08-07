package com.bsb.mobilenotificationservice.service_api.user_data_api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AgreementResponse {

  private Long id;
  private Boolean contractCheck;
  private String contractVersion;
  private Long applicationId;
  private Boolean fatcaCheck;
  private Boolean dataCorrectCheck;
  private Boolean agreementCheck;
  private Boolean dataProcessingCheck;
}

package com.bsb.mobilenotificationservice.service_api.user_data_api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressResponse {

  private Long id;
  @NotBlank
  private String countryCode;
  private String region;
  private String district;
  private String rural;
  private String settlementType;
  @NotBlank
  private String settlementName;
  private String streetType;
  @NotBlank
  private String streetName;
  private String house;
  private String building;
  private String office;
  @NotBlank
  private String type;
  private Long soato;
}
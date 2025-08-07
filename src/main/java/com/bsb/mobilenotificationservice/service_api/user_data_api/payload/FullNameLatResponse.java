package com.bsb.mobilenotificationservice.service_api.user_data_api.payload;

import com.bsb.mobilenotificationservice.common.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FullNameLatResponse {

  @NotBlank
  @Size(max = 255)
  private String firstNameLat;
  @NotBlank
  @Size(max = 255)
  private String lastNameLat;

  @Override
  public String toString() {
    return String.format(Constants.FULL_NAME_LAT_TEMPLATE, firstNameLat, lastNameLat);
  }
}

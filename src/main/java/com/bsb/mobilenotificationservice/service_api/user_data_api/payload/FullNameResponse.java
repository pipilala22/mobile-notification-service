package com.bsb.mobilenotificationservice.service_api.user_data_api.payload;

import com.bsb.mobilenotificationservice.common.constants.Constants;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FullNameResponse {

  @NotBlank
  @Size(max = 255)
  private String firstName;

  @NotBlank
  @Size(max = 255)
  private String lastName;

  @Size(max = 255)
  private String middleName;

  @Override
  public String toString() {
    return String.format(Constants.FULL_NAME_TEMPLATE, lastName, firstName,
        StringUtils.isNotBlank(middleName) ? middleName : StringUtils.EMPTY);
  }

  public String getFirstNameAndMiddleName() {
    return String.format(Constants.FIRST_NAME_AND_MIDDLE_NAME_TEMPLATE, firstName,
        StringUtils.isNotBlank(middleName) ? middleName : StringUtils.EMPTY);
  }
}

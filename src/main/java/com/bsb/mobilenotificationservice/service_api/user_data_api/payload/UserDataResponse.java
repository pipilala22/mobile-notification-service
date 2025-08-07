package com.bsb.mobilenotificationservice.service_api.user_data_api.payload;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldNameConstants
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDataResponse {

  private Long id;

  @Valid
  @NotNull
  private FullNameResponse fullName;
  @Valid
  @NotNull
  private FullNameLatResponse fullNameLat;
  @Valid
  @NotNull
  private PhoneNumberResponse phoneNumber;

  @NotEmpty
  private Set<DocumentResponse> documents;
  @NotEmpty
  private Set<AddressResponse> addresses;
  private Set<AgreementResponse> agreements;

  @NotBlank
  private String email;
  @NotNull
  private Byte sex;
  @NotBlank
  private String birthPlace;
  private String fullBirthPlace;
  @NotNull
  private LocalDate birthDate;
  @NotBlank
  private String citizenship;
  private Integer isResident;
  private UUID msiId;
  private Long eripId;
  @NotNull
  private Long retailId;
  private String codeWord;
  private LocalDateTime createdAt;
}

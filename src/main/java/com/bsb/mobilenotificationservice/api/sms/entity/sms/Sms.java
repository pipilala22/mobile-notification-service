package com.bsb.mobilenotificationservice.api.sms.entity.sms;

import com.bsb.mobilenotificationservice.common.messenger.Messengers;
import com.bsb.mobilenotificationservice.common.model.entity.base.TimeStampedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Data
@Entity
@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class Sms extends TimeStampedEntity<Long> {

  private String sessionId;
  private String codeValue;
  @Column(name = "sms_type")
  @Enumerated(value = EnumType.STRING)
  private SmsType type;
  private Long recipientUserId;

  @Enumerated(value = EnumType.STRING)
  private Messengers messenger;
  @NotNull
  private Boolean isChecked;
  @NotNull
  private Boolean isDetached;


}

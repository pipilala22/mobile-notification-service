package com.bsb.mobilenotificationservice.api.sms.entity.sms;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SmsType {

  USER_CODE_WORD("Запрос на получение кодового слова"),
  DIGITAL_CARD("Получение данных виртуальной карты"),
  PHYSICAL_CARD("Получение данных физической карты"),
  UPDATE_USER_DATA("Обновление личных данных"),
  UPDATE_PHONE_NUMBER("Обновление номера телефона"),
  TRANSFER_BY_PHONE_NUMBER("Перевод по номеру телефона"),
  REANQUETING("Отправка СМС при переанкетировании"),
  ERIP_CONFIRMATION("Подтвеждение ЕРИП"),
  DASHBOARD("Рассылка");
  private final String name;

}

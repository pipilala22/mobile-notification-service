package com.bsb.mobilenotificationservice.service_api.notification_api;

import com.bsb.notification.dto.email.Email;
import com.bsb.notification.dto.email.EmailBroadcast;
import com.bsb.notification.dto.push.Push;
import com.bsb.notification.dto.push.PushBroadcast;
import com.bsb.notification.dto.viber.Viber;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class NotificationServiceApiImpl {

  private final RestTemplate restTemplateWithoutLB;

  @Value("${app.api.services.notification.endpoints.sms.single}")
  private String SMS_SINGLE;

  @Value("${app.api.services.notification.endpoints.email.single}")
  private String EMAIL_SINGLE;

  @Value("${app.api.services.notification.endpoints.email.broadcast}")
  private String EMAIL_BROADCAST;

  @Value("${app.api.services.notification.endpoints.viber.single}")
  private String VIBER_SINGLE;

  @Value("${app.api.services.notification.endpoints.push.single}")
  private String PUSH_SINGLE;

  @Value("${app.api.services.notification.endpoints.push.broadcast}")
  private String PUSH_BROADCAST;


  /**
   * 1.1 SMS_SINGLE 1.2 SMS_BROADCAST
   * <p>
   * 2.1 VIBER_SINGLE 2.2 VIBER_BROADCAST
   * <p>
   * 3.1 EMAIL_SINGLE 3.2 EMAIL_BROADCAST
   * <p>
   * 4.1 PUSH_SINGLE 4.2 PUSH_BROADCAST
   */

  // 1.1 SMS_SINGLE
  public void sendSmsSingle(Viber request) {
    request.setSmsOnly(Boolean.TRUE);
    restTemplateWithoutLB.postForLocation(VIBER_SINGLE, request);
  }

  // 1.2 SMS_BROADCAST
  public void sendSmsBroadcast(List<Viber> requests) {
    requests.forEach(request -> {
      request.setSmsOnly(Boolean.TRUE);
      restTemplateWithoutLB.postForLocation(VIBER_SINGLE, request);
    });
  }

  // 2.1 VIBER_SINGLE
  public void sendViberSingle(Viber request, Boolean sendSmsWhenNotDelivered) {
    request.setSmsOnly(Boolean.FALSE);
    request.setSendSmsWhenNotDelivered(sendSmsWhenNotDelivered);
    restTemplateWithoutLB.postForLocation(VIBER_SINGLE, request);
  }

  // 2.2 VIBER_BROADCAST
  public void sendViberBroadcast(List<Viber> requests, Boolean sendSmsWhenNotDelivered) {
    requests.forEach(request -> {
      request.setSmsOnly(Boolean.FALSE);
      request.setSendSmsWhenNotDelivered(sendSmsWhenNotDelivered);
      restTemplateWithoutLB.postForLocation(VIBER_SINGLE, request);
    });
  }

  // 3.1 EMAIL_SINGLE
  public void sendEmailSingle(Email request) {
    restTemplateWithoutLB.postForLocation(EMAIL_SINGLE, request);
  }

  // 3.2 EMAIL_BROADCAST
  public void sendEmailBroadcast(EmailBroadcast request) {
    restTemplateWithoutLB.postForLocation(EMAIL_BROADCAST, request);
  }

  // 4.1 PUSH_SINGLE
  public void sendPushSingle(Push request) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Push> entity = new HttpEntity<>(request, headers);
    restTemplateWithoutLB.postForEntity(PUSH_SINGLE, entity, String.class);
  }

  // 4.2 PUSH_BROADCAST
  public void sendPushBroadcast(PushBroadcast request) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<PushBroadcast> entity = new HttpEntity<>(request, headers);
    restTemplateWithoutLB.postForEntity(PUSH_BROADCAST, entity, String.class);
  }

}

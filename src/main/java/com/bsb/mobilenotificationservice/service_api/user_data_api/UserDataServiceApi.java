package com.bsb.mobilenotificationservice.service_api.user_data_api;

import com.bsb.mobilenotificationservice.config.feign.FeignConfig;
import com.bsb.mobilenotificationservice.service_api.user_data_api.payload.UserDataResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "MOBILE-USER-DATA-SERVICE", configuration = FeignConfig.class)
public interface UserDataServiceApi {

  @GetMapping(value = "/api/v1/user-data-management/users")
  UserDataResponse findById(@RequestHeader("user-id") Long userId);


}

package com.bsb.mobilenotificationservice;

import static org.springframework.boot.SpringApplication.run;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MobileNotificationApplication {


  public static void main(final String[] args) {

    run(MobileNotificationApplication.class, args);
  }
}

package com.polarbookshop.catalogservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "polar")
public class PolarProperties {
    /**
     *  A message to welcome users.
     */
    private String greeting;    // 사용자 정의 속성인 polar.greeting 속성이 문자열로 인식되는 필드
    public String getGreeting() { return greeting; }
    public void setGreeting(String greeting) { this.greeting = greeting; }
}

package api.passwordService.configuration;

import api.passwordService.enums.HeaderEnum;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignConfig {

  @Bean
  public RequestInterceptor traceEventRequestInterceptor() {
    return requestTemplate -> {
      requestTemplate.header("X-Origen", HeaderEnum.API_PASSWORD.name());
    };
  }
}

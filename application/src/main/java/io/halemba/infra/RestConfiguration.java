package io.halemba.infra;

import com.fasterxml.jackson.databind.Module;
import io.vavr.jackson.datatype.VavrModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestConfiguration {

    @Bean
    Module vavrModule() {
        return new VavrModule();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

}

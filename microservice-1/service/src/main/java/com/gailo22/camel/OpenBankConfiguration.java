package com.gailo22.camel;

import com.openbankproject.api.spec.ATMApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenBankConfiguration {

    @Bean
    public ATMApi getATMApi() {
        // The API integration can be further customised here if required
        return new ATMApi();
    }
}
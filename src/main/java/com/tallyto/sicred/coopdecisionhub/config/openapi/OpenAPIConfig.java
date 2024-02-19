package com.tallyto.sicred.coopdecisionhub.config.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI coopDesicionHubApi() {

        return new OpenAPI()
                .info(new Info().title("Coop Decision Hub")
                        .contact(new Contact().name("Tállyto Rodrigues").email("rodrigues.tallyto@gmail.com"))
                        .version("v0.0.1")
                        .license(new License().name("MIT").url("https://opensource.org/license/mit/")));
    }


}

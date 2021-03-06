package com.lighting.framework.core.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @author
 * @since 2020/9/2
 */

@Profile("dev")
@Configuration
@Component
public class SwaggerConfig {

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Lighting Api").version("1.0").build();
    }
}
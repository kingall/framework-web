package com.lighting.framework.configure;

import com.lighting.framework.core.configure.SwaggerConfig;
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
public class SystemSwaggerConfig extends SwaggerConfig {
    @Bean
    Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("系统管理接口")
                .apiInfo(super.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lighting.framework.system"))
                .paths(PathSelectors.any())
                .build();
    }
}
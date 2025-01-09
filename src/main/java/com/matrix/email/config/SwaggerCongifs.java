package com.matrix.email.config;


import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerCongifs {
	

	@Bean
    public GroupedOpenApi allModulesApi() {
        return GroupedOpenApi.builder()
                .group("all-modules") // Name this group for all modules
                .pathsToMatch("/**")   // Include all paths
                .build();
    }
	
	

}



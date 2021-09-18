package com.example.dbwebapplication

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors

import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2


@Configuration
@EnableSwagger2
class Swagger {
    /*@Value("\${application.version}")
    private val version: String? = null*/
    @Bean
    fun swaggerApiConfig(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .paths(PathSelectors.any())
            .apis(RequestHandlerSelectors.basePackage("com.example"))
            .build()
    }
    /*private fun apiInfo(): ApiInfo? {
        return ApiInfo(
            "Langton ant app", "rest api for langton ant app", version, null,
            Contact("name", "N/A", "email"), null, null, Collections.EMPTY_LIST
        )
    }*/
}

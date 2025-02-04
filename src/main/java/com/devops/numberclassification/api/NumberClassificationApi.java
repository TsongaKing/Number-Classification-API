/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.devops.numberclassification.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Number Classification API",
        version = "1.0",
        description = "API that provides mathematical properties and fun facts about numbers"
    ),
    servers = {
        @Server(url = "http://localhost:8080", description = "Local Server"),
        @Server(url = "https://your-deployed-url.com", description = "Production Server")
    }
)
public class NumberClassificationApi {
    public static void main(String[] args) {
        SpringApplication.run(NumberClassificationApi.class, args);
    }
}

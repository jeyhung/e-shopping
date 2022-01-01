package io.github.jeyhung.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ApiGatewayMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayMicroserviceApplication.class, args);
    }

}

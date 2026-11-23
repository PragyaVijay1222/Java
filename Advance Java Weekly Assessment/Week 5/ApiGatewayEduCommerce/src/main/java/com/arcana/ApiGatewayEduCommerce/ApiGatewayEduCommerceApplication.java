package com.arcana.ApiGatewayEduCommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayEduCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayEduCommerceApplication.class, args);
		
//		Includes: StudentService, ResultService, AttendanceService, config-server-edu-commerce, ServiceRegistryEduCommerce, ApiGatewayEduCommerce
	}

}

package com.arcana.config_server_edu_commerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer 
public class ConfigServerEduCommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigServerEduCommerceApplication.class, args);
	}

}

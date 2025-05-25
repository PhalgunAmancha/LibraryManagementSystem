package com.practice.library_dependent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class LibraryDependentApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryDependentApplication.class, args);
	}

}

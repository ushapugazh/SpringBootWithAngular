package com.sb.ms.ang.empdetails;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EmpdetailsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpdetailsApplication.class, args);
	}

}

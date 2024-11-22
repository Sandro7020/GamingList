package com.info.GamingList.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.info.GamingList.controller",
        "com.info.GamingList.model",
        "com.info.GamingList.repository",
        "com.info.GamingList.service"
})

public class GamingListApplication {

	public static void main(String[] args) {
		SpringApplication.run(GamingListApplication.class, args);
	}

}

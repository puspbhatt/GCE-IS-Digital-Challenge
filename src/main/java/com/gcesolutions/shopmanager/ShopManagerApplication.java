package com.gcesolutions.shopmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ShopManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShopManagerApplication.class, args);
	}

}

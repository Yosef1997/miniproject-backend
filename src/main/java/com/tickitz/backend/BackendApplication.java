package com.tickitz.backend;


import com.tickitz.backend.config.RsaKeyConfigProperties;
import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@EnableConfigurationProperties(RsaKeyConfigProperties.class)
@SpringBootApplication
@Log
public class BackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		log.info("Tickitz Backend App started successfully");
	}
}

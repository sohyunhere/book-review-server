package com.example.bookreviewserver;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableBatchProcessing
@EnableScheduling
@SpringBootApplication
public class BookReviewServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookReviewServerApplication.class, args);
	}

}

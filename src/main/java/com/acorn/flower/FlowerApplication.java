package com.acorn.flower;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

//custom.properties 파일 로딩하도록 설정
@PropertySource("classpath:application.yml")
@PropertySource(value = "classpath:custom.properties")
@SpringBootApplication
public class FlowerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlowerApplication.class, args);
	}

}

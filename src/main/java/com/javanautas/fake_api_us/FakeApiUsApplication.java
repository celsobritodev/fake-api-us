package com.javanautas.fake_api_us;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;



//Spring Boot 3. CRUD, API externa, Swagger, Postgres.
//https://youtu.be/XgN9E7oNJ-o

@SpringBootApplication
@EnableFeignClients
public class FakeApiUsApplication {

	public static void main(String[] args) {
		SpringApplication.run(FakeApiUsApplication.class, args);
	}

}

package com.festivalP.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


//나중에 db연결할 때 exclude = DataSourceAutoConfiguration.class 부분 지워야한다.
@SpringBootApplication
public class Group2ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(Group2ProjectApplication.class, args);
	}

}

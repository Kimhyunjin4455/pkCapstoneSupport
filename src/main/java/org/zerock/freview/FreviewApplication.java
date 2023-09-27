package org.zerock.freview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FreviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreviewApplication.class, args);
	}

}

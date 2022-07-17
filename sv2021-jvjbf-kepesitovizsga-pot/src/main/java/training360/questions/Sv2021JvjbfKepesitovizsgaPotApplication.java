package training360.questions;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Sv2021JvjbfKepesitovizsgaPotApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sv2021JvjbfKepesitovizsgaPotApplication.class, args);
	}

	@Bean
	public ModelMapper createModelmapper() {
		return new ModelMapper();
	}
}

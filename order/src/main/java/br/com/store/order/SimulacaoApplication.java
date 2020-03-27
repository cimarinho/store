package br.com.store.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;

@SpringBootApplication
@EnableBinding
public class SimulacaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimulacaoApplication.class, args);
	}

}

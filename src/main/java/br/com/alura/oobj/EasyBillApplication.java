package br.com.alura.oobj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class EasyBillApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyBillApplication.class, args);
	}

}

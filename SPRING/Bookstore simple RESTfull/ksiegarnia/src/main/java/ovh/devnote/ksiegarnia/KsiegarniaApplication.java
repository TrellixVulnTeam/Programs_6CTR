package ovh.devnote.ksiegarnia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@ComponentScan({"ovh.devnote.ksiegarnia"})
@EntityScan({"ovh.devnote.ksiegarnia.Entity"})
@EnableJpaRepositories("ovh.devnote.ksiegarnia.DAO")
public class KsiegarniaApplication {

	public static void main(String[] args) {
		SpringApplication.run(KsiegarniaApplication.class, args);
	}

}

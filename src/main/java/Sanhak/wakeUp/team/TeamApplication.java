package Sanhak.wakeUp.team;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
//밑에 2개 추가
@EnableJpaAuditing
@ComponentScan(basePackages = "Sanhak.wakeUp")
public class TeamApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeamApplication.class, args);
	}

}

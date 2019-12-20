package kr.owens.inhanoticeboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InhanoticeboardApplication {

  public static void main(String[] args) {
    SpringApplication.run(InhanoticeboardApplication.class, args);
  }

}

package kr.owens.inhanoticeboard;

import java.security.SecureRandom;
import java.util.Date;
import java.util.stream.IntStream;
import kr.owens.inhanoticeboard.entity.BoardEntity;
import kr.owens.inhanoticeboard.repository.BoardRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class InhanoticeboardApplication {

  public static void main(String[] args) {
    SpringApplication.run(InhanoticeboardApplication.class, args);
  }

  private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
  private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
  private static final String NUMBER = "0123456789";

  private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
  private static SecureRandom random = new SecureRandom();

  public static String generateRandomString(int length) {
    if (length < 1) {
      throw new IllegalArgumentException();
    }

    StringBuilder sb = new StringBuilder(length);
    for (int i = 0; i < length; i++) {

      // 0-62 (exclusive), random returns 0-61
      int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
      char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);

      // debug
      System.out.format("%d\t:\t%c%n", rndCharAt, rndChar);

      sb.append(rndChar);

    }

    return sb.toString();

  }

  @Bean
  public CommandLineRunner test(BoardRepository repository) {
    return args ->
        IntStream.rangeClosed(1, 150).forEach(i -> {
          BoardEntity entity = BoardEntity.builder()
              .nickName("tester")
              .good(0)
              .viewCount(0)
              .content(generateRandomString(30))
              .regDate(new Date())
              .title(generateRandomString(10))
              .build();
          System.out.println(i + " : entity saved");

          repository.save(entity);
        });
  }
}

package br.com.btwow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BtwowApplication {

  public static void main(String[] args) {
    SpringApplication.run(BtwowApplication.class, args);
  }
}

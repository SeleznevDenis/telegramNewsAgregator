package ru.dselezneww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("ru.dselezneww")
@EnableAutoConfiguration
@SpringBootConfiguration
public class TelegramNewsFeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramNewsFeederApplication.class, args);
    }

}

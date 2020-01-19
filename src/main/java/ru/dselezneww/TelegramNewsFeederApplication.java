package ru.dselezneww;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("ru.dselezneww")
@EnableAutoConfiguration
@SpringBootConfiguration
@EnableScheduling
@EnableCaching
public class TelegramNewsFeederApplication {

    public static void main(String[] args) {
        SpringApplication.run(TelegramNewsFeederApplication.class, args);
    }

}

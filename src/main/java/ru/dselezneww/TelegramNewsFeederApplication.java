package ru.dselezneww;

import com.google.common.cache.CacheBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.telegram.telegrambots.ApiContextInitializer;

import java.util.concurrent.TimeUnit;

@ComponentScan("ru.dselezneww")
@EnableAutoConfiguration
@SpringBootConfiguration
@EnableScheduling
@EnableCaching
public class TelegramNewsFeederApplication {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(TelegramNewsFeederApplication.class, args);
    }

    @Bean("news")
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager() {
            @Override
            protected Cache createConcurrentMapCache(String name) {
                return new ConcurrentMapCache(
                        name,
                        CacheBuilder.newBuilder()
                        .expireAfterWrite(1, TimeUnit.DAYS)
                        .build().asMap(),
                        false
                );
            }
        };
    }

}

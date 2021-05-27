package com.myproject.redisserver;

import com.myproject.redisserver.config.RedissonProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties({RedissonProperties.class})
@SpringBootApplication
public class RedisServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisServerApplication.class, args);
	}

}

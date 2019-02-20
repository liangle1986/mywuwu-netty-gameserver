package com.mywuwu.gameserver.webserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(scanBasePackages =
        {"com.mywuwu.gameserver.webserver",
                "com.mywuwu.gameserver.data",
                "com.mywuwu.gameserver.core",
                "com.mywuwu.gameserver.mapper"
        })
@EnableMongoRepositories("com.mywuwu.gameserver.data")
public class WebServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class);
    }
}

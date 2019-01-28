package com.mywuwu.gameserver.hallserver;


import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(scanBasePackages =
        {"com.mywuwu.gameserver.hallserver",
                "com.mywuwu.gameserver.core"
        })
@Configuration
@EnableAsync
public class HallServerApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(HallServerApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}

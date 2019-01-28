package com.mywuwu.gameserver.yingsanzhangserver;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication(scanBasePackages =
        {"com.mywuwu.gameserver.yingsanzhangserver",
                "com.mywuwu.gameserver.data",
                "com.mywuwu.gameserver.core"
        })
@EnableMongoRepositories("com.mywuwu.gameserver.data")
//@EnableAsync
public class YingSanZhangServerApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(YingSanZhangServerApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);
    }
}

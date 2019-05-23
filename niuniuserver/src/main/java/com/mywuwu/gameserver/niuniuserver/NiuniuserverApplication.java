package com.mywuwu.gameserver.niuniuserver;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages =
        {"com.mywuwu.gameserver.niuniuserver",
                "com.mywuwu.gameserver.data",
                "com.mywuwu.gameserver.core"
        })
@EnableMongoRepositories("com.mywuwu.gameserver.data")
//@EnableAsync
public class NiuniuserverApplication {

    public static void main(String[] args) {
            new SpringApplicationBuilder(NiuniuserverApplication.class)
                    .web(WebApplicationType.NONE)
                    .run(args);
    }

}

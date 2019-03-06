package com.mywuwu.gameserver.niuniuserver;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages =
        {"com.mywuwu.gameserver.niuniuserver",
                "com.mywuwu.gameserver.mapper",
                "com.mywuwu.gameserver.core"
        })
//@EnableAsync
public class NiuniuserverApplication {

    public static void main(String[] args) {
            new SpringApplicationBuilder(NiuniuserverApplication.class)
                    .web(WebApplicationType.NONE)
                    .run(args);
    }

}

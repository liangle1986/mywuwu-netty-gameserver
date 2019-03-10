package com.mywuwu.gameserver.webserver;

import com.mywuwu.gameserver.mapper.common.ds.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;
//import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@SpringBootApplication(scanBasePackages =
        {
                "com.mywuwu.gameserver.webserver",
//                "com.mywuwu.gameserver.data",
                "com.mywuwu.gameserver.core",
                "com.mywuwu.gameserver.mapper"
        })
//@EnableMongoRepositories("com.mywuwu.gameserver.data")
public class WebServerApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WebServerApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(WebServerApplication.class);
    }
}

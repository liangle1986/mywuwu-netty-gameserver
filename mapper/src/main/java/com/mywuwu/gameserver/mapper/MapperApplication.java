package com.mywuwu.gameserver.mapper;

import com.mywuwu.gameserver.mapper.common.ds.DynamicDataSourceRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;

@Import(DynamicDataSourceRegister.class)
@MapperScan(basePackages = "com.mywuwu.gameserver.mapper.mapper")
@SpringBootApplication
public class MapperApplication {

	public static void main(String[] args) {
		SpringApplication.run(MapperApplication.class, args);
	}

}


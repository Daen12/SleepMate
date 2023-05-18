package com.ssafy.YogaMate.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.YogaMate.model.dao")
public class DBConfig {

}

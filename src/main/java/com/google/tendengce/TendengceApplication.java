package com.google.tendengce;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude = MybatisAutoConfiguration.class)
@ServletComponentScan
@EnableAutoConfiguration
@EnableCaching
@MapperScan("com.google.tendengce.mapper")
@EnableEurekaClient
@EnableDiscoveryClient
@EnableFeignClients
public class TendengceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TendengceApplication.class, args);
	}

}

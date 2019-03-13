package com.debug.imooc.dubbo.one.server;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;  //1.x
//import org.springframework.boot.web.servlet.support.SpringBootServletInitializer; //2.x版本
import org.springframework.context.annotation.ImportResource;


@SpringBootApplication
@ImportResource(value = {"classpath:spring/spring-*.xml"})
@MapperScan(basePackages = "com.debug.imooc.dubbo.two.model.mapper")
//@EnableScheduling
//@RestController
public class BootMoreApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder){
        return builder.sources(BootMoreApplication.class);
    }

    public static void main(String[] args) throws Exception{
        SpringApplication.run(BootMoreApplication.class,args);
    }
//    @RequestMapping("/run")
//    public String run(){
//        return "This is dubbo two!";
//    }
}

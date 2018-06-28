package com.example.contorlserver;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
public class ContorlserverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContorlserverApplication.class, args);

/*      SpringApplication app = new SpringApplication(ContorlserverApplication.class);
        app.setBannerMode(Banner.Mode.OFF);//关闭banner
        app.setBannerMode(Banner.Mode.CONSOLE);//输出到控制台
        app.setBannerMode(Banner.Mode.LOG);//输出到日志
        app.run(args);*/
    }
}

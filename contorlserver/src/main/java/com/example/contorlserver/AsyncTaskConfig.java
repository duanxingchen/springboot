package com.example.contorlserver;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executor;

@Configuration
@ComponentScan("com.example.contorlserver")
@EnableAsync
public class AsyncTaskConfig implements AsyncConfigurer {

    @Override
    public Executor  getAsyncExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setThreadNamePrefix("Anno-Executor");
        executor.setCorePoolSize(5);// 最小线程数
        executor.setMaxPoolSize(5);
        executor.initialize();

        return  executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return null;
    }
}

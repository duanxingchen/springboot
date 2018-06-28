package com.example.contorlserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static java.lang.System.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ContorlserverApplication.class)
@WebAppConfiguration

public class ContorlserverApplicationTests {

    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AsyncTaskConfig.class);
    ThreadAsyncDemo threadAsyncDemo = context.getBean(ThreadAsyncDemo.class);
    //List<Future<String>> lstFuture = new ArrayList<Future<String>>();// 存放所有的线程，用于获取结果

    @Test
    public  void loads() throws InterruptedException, ExecutionException {
//        while (true) {
            threadAsyncDemo.asyncVoidTest();
            threadAsyncDemo.asyncParameterTest("zengna");
         //   Future<String> future = threadAsyncDemo.asyncReturnFutureTest(10);
         //   out.println(future.get());
//            }

        while (true) {
        System.out.print("主线程");
        Thread.sleep(10000);
        }
    }
    }


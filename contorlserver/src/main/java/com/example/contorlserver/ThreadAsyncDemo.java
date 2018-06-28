package com.example.contorlserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Future;

@Service
public class ThreadAsyncDemo {

    @Autowired
    SocketServer socketServer;

    @Async
    public  void asyncVoidTest() throws InterruptedException {
        System.out.println("asyncVoidTest");
        try {
            socketServer.socketServerStart();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        while (true) {
//            System.out.println("asyncVoidTest");
//            Thread.sleep(10000);
//        }
    }

    @Async
    public  void asyncParameterTest(String s) throws InterruptedException {
        while (true) {
            System.out.println("asyncParameterTest" + s);
            Thread.sleep(1000);

        }
    }


    @Async
    public Future<String> asyncReturnFutureTest(int i) throws InterruptedException {
        Future<String> future;

        Thread.sleep(10000);
        future = new AsyncResult<String>("success"+i);

        System.out.println("asyncReturnFutureTest"+i);
        return future;
    }
}

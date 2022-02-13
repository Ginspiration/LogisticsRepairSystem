package com.ginspiration.webchatui;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//@SpringBootTest
class WebchatUiApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("线程创建中");
        //4、创建一个定长且定时执行任务的线程池
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(()-> System.out.println(18798798),1, TimeUnit.SECONDS);
        System.out.println("线程执行完成");
    }

}

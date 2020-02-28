package com.jhhg.nova.eventbus;

import com.google.common.eventbus.Subscribe;

import java.time.Instant;

/***
 * @ClassName: EventListenerA
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/26 上午10:18
 * @version : V1.0
 */
public class EventListenerA {

    @Subscribe
    public void test1(TestEvent testEvent) {

        System.out.println(Instant.now()+"监听者A-->订阅者1，收到事件："+testEvent.getEventName()+
                ",线程为："+Thread.currentThread().getName());

        System.out.println("监听者A，线程休眠3s，模拟处理业务...");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Subscribe
    public void test2(TestEvent testEvent) {

        System.out.println(Instant.now()+"监听者A-->订阅者2，收到事件："+testEvent.getEventName()+
                ",线程为："+Thread.currentThread().getName());
    }
}

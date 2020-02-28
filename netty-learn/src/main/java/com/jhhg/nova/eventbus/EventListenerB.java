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
public class EventListenerB {

    @Subscribe
    public void test(TestEvent testEvent) {

        System.out.println(Instant.now()+"监听者B，收到事件："+testEvent.getEventName()+
                ",线程为："+Thread.currentThread().getName());
        System.out.println("监听者B，线程休眠8s，模拟处理业务...");
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

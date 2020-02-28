package com.jhhg.nova.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/***
 * @ClassName: EventBusCenter
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/26 上午10:34
 * @version : V1.0
 */
public class EventBusCenter {

    /**静态内部类*/
    private static class EventBusHolder {

        private static EventBus eventBus = new EventBus();

        private static Executor executor = Executors.newFixedThreadPool(10);

        private static AsyncEventBus asyncEventBus = new AsyncEventBus("asyncEventBus",executor);

    }
    /**私有构造方法，防止外部来new*/
    private EventBusCenter() {}

    /**外部调用这个方法会导致EventBusHold的初始化，会初始化它的静态域*/
    public static EventBus getInstance() {
        return EventBusHolder.eventBus;
    }

    public static AsyncEventBus getAsyncInstance() {
        return EventBusHolder.asyncEventBus;
    }

    public static void register(Object obj) {
        getInstance().register(obj);
    }

    public static void unRegister(Object obj) {
        getInstance().unregister(obj);
    }

    public static void post(Object obj) {
        getInstance().post(obj);
    }
}


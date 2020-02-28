package com.jhhg.nova.eventbus;

import com.google.common.eventbus.AsyncEventBus;
import com.sun.xml.internal.rngom.parse.host.Base;

/***
 * @ClassName: TestAsyncEventBus
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/28 上午9:59
 * @version : V1.0
 */
public class TestAsyncEventBus {

    public static void main(String[] args) {


        System.out.println("Main Thread 开始");
        AsyncEventBus asyncEventBus = EventBusCenter.getAsyncInstance();



        asyncEventBus.register(new SendSMSListener());
        asyncEventBus.register(new ERPListener());

        BaseEvent baseEvent = new BaseEvent();
        baseEvent.setOrderId("20192343434213");
        baseEvent.setOrderPrice(345.65);
        baseEvent.setUserPhone("15589994619");
        asyncEventBus.post(baseEvent);

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrderId("20192343434");
        orderEvent.setOrderPrice(345.65);
        orderEvent.setUserPhone("15589994619");
        orderEvent.setSmsCode("201");
        orderEvent.setSmsPrivateKey("sfwerfgertwerwrerwesfdfggfgf");

        asyncEventBus.post(orderEvent);

        System.out.println("Main thread继续处理其他任务");






    }
}

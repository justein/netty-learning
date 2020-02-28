package com.jhhg.nova.eventbus;

/***
 * @ClassName: TestEventBus
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/26 上午10:37
 * @version : V1.0
 */
public class TestEventBus {

    public static void main(String[] args) {

        EventListenerA eventListenerA = new EventListenerA();
        EventListenerB eventListenerB = new EventListenerB();

        TestEvent testEventA = new TestEvent("online-evt",12);
        TestEvent testEventB = new TestEvent("offline-evt",11);

        /**将监听者注册到总线*/
        EventBusCenter.register(eventListenerA);
        EventBusCenter.register(eventListenerB);

        /**将事件投递出去*/
        EventBusCenter.post(testEventA);
        EventBusCenter.post(testEventB);

        /**卸载掉一个listener*/
        EventBusCenter.unRegister(eventListenerB);
        System.out.println("卸载掉监听者B，线程休眠2s...");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /**再投递一个事件*/
        EventBusCenter.post(new TestEvent("sendms-evt",19));



    }
}

package com.jhhg.nova.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/***
 * @ClassName: ERPListener
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/28 上午10:30
 * @version : V1.0
 */
public class ERPListener {

    @Subscribe
    @AllowConcurrentEvents
    public void sendOrder2ERP(BaseEvent baseEvent) {

        /**模拟推送订订单信息到ERP*/
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ERPListener: "+Thread.currentThread().getName());
        System.out.println("订单："+baseEvent.getOrderId()+"已经推送到ERP生产库");
    }
}

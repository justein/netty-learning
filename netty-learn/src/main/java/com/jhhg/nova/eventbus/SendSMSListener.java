package com.jhhg.nova.eventbus;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.Subscribe;

/***
 * @ClassName: SendSMSListener
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/28 上午9:59
 * @version : V1.0
 */
public class SendSMSListener {

    @Subscribe
    @AllowConcurrentEvents
    public void purchaseOrderNotice(OrderEvent orderEvent) {

        /**模拟发送短信的过程*/
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SendSMSListener: "+Thread.currentThread().getName());
        System.out.println("短信网关: "+orderEvent.getSmsCode()+",校验Key: "+orderEvent.getSmsPrivateKey()+"成功！");
        System.out.println("发送下单成功短信: "+orderEvent.getUserPhone()+"订单金额："+orderEvent.getOrderPrice());
    }
}

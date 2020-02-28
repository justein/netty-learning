package com.jhhg.nova.eventbus;

/***
 * @ClassName: BaseEvent
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/28 上午10:00
 * @version : V1.0
 */
public class BaseEvent {

    private String userPhone;
    private String orderId;
    private Double orderPrice;


    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(Double orderPrice) {
        this.orderPrice = orderPrice;
    }
}

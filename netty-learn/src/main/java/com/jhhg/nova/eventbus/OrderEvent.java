package com.jhhg.nova.eventbus;

/***
 * @ClassName: OrderEvent
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/28 上午10:02
 * @version : V1.0
 */
public class OrderEvent extends BaseEvent {

    private String smsCode;
    private String smsPrivateKey;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getSmsPrivateKey() {
        return smsPrivateKey;
    }

    public void setSmsPrivateKey(String smsPrivateKey) {
        this.smsPrivateKey = smsPrivateKey;
    }
}

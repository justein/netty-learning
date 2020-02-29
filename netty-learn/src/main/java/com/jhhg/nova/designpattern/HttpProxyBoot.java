package com.jhhg.nova.designpattern;

/***
 * @ClassName: HttpProxyBoot
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 上午11:12
 * @version : V1.0
 */
public class HttpProxyBoot extends BootJob {
    @Override
    public void start() {
        System.out.println("httpProxy start...");
        startNext();
    }

    @Override
    public void stop() {
        System.out.println("httpProxy stop...");
        stopNext();
    }
}

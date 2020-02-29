package com.jhhg.nova.designpattern;

/***
 * @ClassName: MonitorBoot
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 上午11:11
 * @version : V1.0
 */
public class MonitorBoot extends BootJob {
    @Override
    public void start() {
        System.out.println("MonitorBoot start...");
        startNext();
    }

    @Override
    public void stop() {
        System.out.println("MonitorBoot stop...");
        stopNext();
    }
}

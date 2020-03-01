package com.jhhg.nova.designpattern;

/***
 * @ClassName: AdminBoot
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 上午11:08
 * @version : V1.0
 */
public class AdminBoot extends BootJob {
    @Override
    public void start() {
        System.out.println("AdminBoot start...");
        startNext();
    }

    @Override
    public void stop() {
        System.out.println("AdminBoot stop...");
        stopNext();

    }


}

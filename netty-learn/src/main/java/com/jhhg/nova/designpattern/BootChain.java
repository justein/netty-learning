package com.jhhg.nova.designpattern;

import com.google.common.eventbus.EventBus;

/***
 * @ClassName: BootChain
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 上午9:15
 * @version : V1.0
 */
public final class BootChain {

    private final BootJob boot = new BootJob() {
        @Override
        public void start() {
            System.out.println("MPush Server bootstrap chain start ...");
            startNext();
        }

        @Override
        public void stop() {
            System.out.println("MPush Server bootstrap chain stop ...");
            stopNext();
        }
    };

    /**因为要构造出服务链，用last代表链上的最后一个服务*/
    private BootJob last = boot;

    public void start() {
        boot.start();
    }

    public void stop() {
        boot.stop();
    }

    public static BootChain chain() {
        return new BootChain();
    }

    public BootChain boot() {
        return this;
    }

    /**用于表示服务链上的最后一个服务*/
    public void end() {
        setNext(new BootJob() {
            @Override
            public void start() {
                /**链上最后一个服务启动成功，表示整个服务启动完成，发布服务启动成功事件*/
                new EventBus().post(new ServerStartupEvent());
            }

            @Override
            public void stop() {
                /**链上最后一个服务关闭成功，表示整个服务关闭完成，发布服务关闭成功事件*/
                new EventBus().post(new ServerStopEvent());
            }

            @Override
            public String getName() {
                return "Last Job";
            }
        });
    }

    public BootChain setNext(BootJob bootJob) {
        this.last = last.setNext(bootJob);
        return this;
    }
}

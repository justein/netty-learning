package com.jhhg.nova.designpattern;

/***
 * @ClassName: ServerLauncher
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 上午9:14
 * @version : V1.0
 */
public class ServerLauncher {

    public void init() {

        BootChain bootChain = BootChain.chain();

        bootChain.boot()
                .setNext(new AdminBoot())
                .setNext(new HttpProxyBoot())
                .setNext(new MonitorBoot())
                .end();

        bootChain.start();
        /**模拟服务运行*/
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        bootChain.stop();
    }
}

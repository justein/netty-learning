package com.jhhg.nova.designpattern;

import com.jhhg.nova.basic.Server;

/***
 * @ClassName: Main
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 上午9:16
 * @version : V1.0
 */
public class Main {

    public static void main(String[] args) {

        ServerLauncher serverLauncher = new ServerLauncher();

        serverLauncher.init();

        Runtime.getRuntime().addShutdownHook(
                new Thread(()-> System.out.println("进行服务关闭和资源释放等清理工作"))
        );
    }


}

package com.jhhg.nova.designpattern;

import java.util.function.Supplier;

/***
 * @ClassName: BootJob
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/29 上午9:15
 * @version : V1.0
 */
public abstract class BootJob {
    /**因为要让子类也能调用抽象类中的公共方法，所以这里定义一个抽象类的成员变量*/
    protected BootJob next;

    /**组件启动方法*/
    public abstract void start();

    /**组件停止方法*/
    public abstract void stop();

    /**启动下一个*/
    public void startNext() {
        if (next!=null) {
            next.start();

        }
    }

    public void stopNext() {
        if (next!=null) {
            next.stop();

        }
    }

    public BootJob setNext(BootJob next) {
        this.next = next;
        return next;
    }

    public BootJob setNext(Supplier<BootJob> next, boolean enabled) {
        if (enabled) {
            return setNext(next.get());
        }
        return this;
    }

    public BootJob getNext() {
        return next;
    }

    public String getNextName() {
        return next.getName();
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }
}

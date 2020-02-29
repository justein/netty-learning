package com.jhhg.nova.generic;

/***
 * @ClassName: Plate
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/28 下午4:44
 * @version : V1.0
 */
public class Plate<T> {

    private T item;

    public T getItem() {
        return item;
    }

    public void setItem(T item) {
        this.item = item;
    }
}

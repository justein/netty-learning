package com.jhhg.nova.generic;

/***
 * @ClassName: GenericTest
 * @Description: TODO
 * @Author: Lyn
 * @Date: 2020/2/28 下午4:45
 * @version : V1.0
 */
public class GenericTest {

    public static void main(String[] args) {

        /**这里虽然Apple is-a Fruit，但是java编译期认为"装苹果的盘子not is-a 装水果的盘子"，所以这里编译会报错*/
//        Plate<Fruit> plate = new Plate<Apple>();
        /**那要怎么改呢，关键是要让这俩盘子建立关系，然后就有了 ? extend X 和 ？ super X*/
        /**这个的意思就是 能盛放水果或者一切水果子类的盘子 ，苹果是水果的子类，自然能放咯*/
        Plate<? extends Fruit> plate = new Plate<Apple>();

        /** ？extends X ，有个问题就是编译器不知道盘子里边到底放的是什么？
         * 可能是Fruit？可能是Apple？可能是Banana?所以编译器只知道 这是一个盛放东西的盘子，盛放的是什么？不知道
         * 所以这样往里放，编译器认为不对。
         * */
        /**
         * 所以通配符<?>和类型参数<T>的区别就在于，对编译器来说所有的T都代表同一种类型。
         * 但通配符<?>没有这种约束，Plate<?>单纯的就表示：盘子里放了一个东西，是什么我不知道。
         * */
        //plate.setItem(new Fruit());
        //plate.setItem(new Apple());


        /**下界<? super T>不影响往里存，但往外取只能放在Object对象里*/
        /**这个的意思就是 能盛放水果以及一切水果父类的盘子 */
        Plate<? super Fruit> plate1 = new Plate<Food>();

        plate1.setItem(new Fruit());
        plate1.setItem(new Apple());

        /**但是这个往外取会部分失效*/
        /**因为下界规定了元素的最小粒度的下限，实际上是放松了容器元素的类型控制。既然元素是Fruit的
         * 基类，那往里存粒度比Fruit小的都可以。但往外读取元素就费劲了，只有所有类的基类Object对象
         * 才能装下。但这样的话，元素的类型信息就全部丢失。
         * */
        //Apple apple = plate1.getItem();

        /**PECS原则*/
        //Producer Extends Consumer Super
        /**频繁往外读取内容的，适合用上界Extends。经常往里插入的，适合用下界Super。*/

        /**
         * <? super Fruit>的逻辑是：泛型类型可以是Object，可以是Food(假设food是fruit的父类)，
         * 但是不论是Object还是Food，显然Apple都是他们的子类，因此可以set进去。
         * <? extends Fruit>表示泛型类型可以使Apple，Peach，Banana，
         * 所以你无法set任何东西进去，因为你根本不能确定实际类型是什么，你只能保证取出来的类型至少是Fruit类型。
         *
         * */
    }
}

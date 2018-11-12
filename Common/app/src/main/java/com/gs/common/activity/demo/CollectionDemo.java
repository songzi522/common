package com.gs.common.activity.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by GuanSong
 * Description:
 * on 2018/6/21.
 */

public class CollectionDemo {

    private static final String TAG = "CollectionDemo";

    public static void main(String[] args) {
//        Collection collection = new ArrayList();
//        show(collection);

//        Collection collection1 = new ArrayList();
//        Collection collection2 = new ArrayList();
//        show(collection1, collection2);

        // 迭代器
        useIterator();

    }

    private static void useIterator() {
        Collection collection = new ArrayList();

        collection.add("abc1");
        collection.add("abc2");
        collection.add("abc3");
        collection.add("abc4");

        System.out.println(collection);

        //使用了Collection中的iterator()方法。调用集合中的迭代器方法，是为了获取集合中的迭代器对象

        // while循环结束后，迭代器还可以用
//        Iterator iterator = collection.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

        // 节省内存  it的内存  结束后it的内存就释放， 迭代器it就不能用了
        for (Iterator it = collection.iterator(); it.hasNext();) {
            System.out.println(it.next());
        }

        //        System.out.println(iterator.next());
        //        System.out.println(iterator.next());
        //        System.out.println(iterator.next());
        //        System.out.println(iterator.next());

    }

    private static void show(Collection collection1, Collection collection2) {
        // 1、collection1添加元素
        collection1.add("abc1");
        collection1.add("abc2");
        collection1.add("abc3");
        collection1.add("abc4");

        // 2、collection2添加元素
        collection2.add("abc1");
        collection2.add("abc6");
        collection2.add("abc7");
        collection2.add("abc8");

        System.out.println("c1: " + collection1);
        System.out.println("c2: " + collection2);

        // 3、演示add
//        collection1.add(collection2);
//        System.out.println("c1: " + collection1);

        // 4、演示addAll
//        collection1.addAll(collection2);
//        System.out.println("c1: " + collection1);

        // 4、演示removeAll  作用：将两个集合中的相同元素从调用removeAll的集合中删除  ，即删除两个集合的交集
//        collection1.removeAll(collection2);
//        System.out.println("c1: " + collection1);

        // 5、演示retainAll  作用：取交集，保留和指定集合相同的元素，而删除不同的元素   和removeAll功能相反
        collection1.retainAll(collection2);
        System.out.println("c1: " + collection1);


    }


    private static void show(Collection coll) {
        // 1、添加元素  add
        coll.add("abc1");
        coll.add("abc2");
        coll.add("abc3");

        System.out.println(coll);

        // 2、删除元素  remove  会改变集合的长度
//        coll.remove("abc1");

        // 3、清空集合
        coll.clear();

        System.out.println(coll);


    }

}

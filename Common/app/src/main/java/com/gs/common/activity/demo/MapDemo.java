package com.gs.common.activity.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by GuanSong
 * Description:
 * on 2018/6/19.
 */

public class MapDemo {

    private static final String TAG = "MapDemo";

    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        method(map);
    }


    private static void method(Map<Integer, String> map) {//学号和姓名

        //添加元素
        System.out.println(map.put(1, "旺财")); // null
        System.out.println(map.put(1, "猫咪1"));  //旺财  存相同键，值会覆盖

        map.put(2, "猫咪2");
        map.put(3, "猫咪3");

        //删除
        System.out.println("remove: " + map.remove(2));

        //判断
        System.out.println("containsKey: " + map.containsKey(3));

        //获取
        System.out.println("get: " + map.get(3));


        System.out.println(map);


    }


}

package com.gs.common.interfaceface;


/**
 * 在其他类中使用ACalculator进行两数之和的计算。其他类并不需要了解ACalculator是通过什么方式计算的，
 * 只需要了解Calculator接口中的相关方法定义即可。
 */
public class Test {
    public static void main(String[] args) {
        Calculator calculator = new ACalculator();
        int sum = calculator.add(12, 14);
        System.out.print("sum = " + sum);
    }
}
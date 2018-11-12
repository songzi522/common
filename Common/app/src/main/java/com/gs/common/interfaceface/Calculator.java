package com.gs.common.interfaceface;

/**
 * 接口使用2： 定义一个规范（协议）
 *
 * 同一个接口可以有多个不同的实现类，但每一个实现类都必须重写接口中所有的抽象方法。即接口不考虑这些实现类各自采用什么方式
 * 实现这些功能，但它要求所有的实现类都必须有这些功能。
 */
public interface Calculator {

    /* 计算器可以计算两个数的和 */
    int add(int a, int b);

    /* 计算器可以计算两个数的差 */
    int sub(int a, int b);

    /* 计算器可以计算两个数的积 */
    long mul(int a, int b);

    /* 计算器可以计算两个数的商 */
    float div(int a, int b);

}
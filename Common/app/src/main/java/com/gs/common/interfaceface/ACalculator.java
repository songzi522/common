package com.gs.common.interfaceface;

public class ACalculator implements Calculator {
    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int sub(int a, int b) {
        return a - b;
    }

    @Override
    public long mul(int a, int b) {
        return a * b;
    }

    @Override
    public float div(int a, int b) {
        return (float) a / (float) b;
    }
}
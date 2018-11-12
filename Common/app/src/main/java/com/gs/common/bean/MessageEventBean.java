package com.gs.common.bean;

/**
 * Created by Administrator on 2018/3/8.
 */

public class MessageEventBean {

    public final static int TYPE_DEFAULT = 0;
    public final static int TYPE_ONE = 1;
    public final static int TYPE_TWO = 2;

    private String message;
    private String name;
    private int age;
    private int type;

    public MessageEventBean(String message, String name, int age, int type) {
        this.message = message;
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

}

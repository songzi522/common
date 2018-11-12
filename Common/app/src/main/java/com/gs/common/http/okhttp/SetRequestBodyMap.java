package com.gs.common.http.okhttp;

import java.util.HashMap;
import java.util.Map;

public class SetRequestBodyMap {
    public static Map set_DevicesInfo(String pkCudevId, String name) {
        Map<String, String> datas = new HashMap<String, String>();//这里是拼接的请求参数
        datas.put("pkCudevId", "");
        datas.put("name", " ");
        return datas;
    }



    public static Map set_UserInfo(String username, String password, String nickname) {
        Map<String, String> datas = new HashMap<String, String>();//这里是拼接的请求参数
        datas.put("username",username);
        datas.put("password",password);
        datas.put("nickname",nickname);
        datas.put("client",2+"");
        datas.put("clientname","android");
        return datas;
    }


    public static Map set_UserInfo1(String username, String password) {
        Map<String, String> datas = new HashMap<String, String>();//这里是拼接的请求参数
        datas.put("username",username);
        datas.put("password",password);
        datas.put("client",2+"");
        datas.put("clientname","android");
        datas.put("pushservice","");
        datas.put("pushToken","");
        return datas;
    }


    public static Map set_Info2(String yiyue_org) {
        Map<String, String> datas = new HashMap<>();//这里是拼接的请求参数
        datas.put("yiyue_org",yiyue_org);
        return datas;
    }

}
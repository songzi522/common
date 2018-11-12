package com.gs.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	public static String getCurrentTime(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String str = formatter.format(curDate);
		return str;
	}
	public static String getCurrentYear(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String str = formatter.format(curDate);
		return str;
	}
	public static String getYear(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String str = formatter.format(curDate);
		return str;
	}
	public static String getMonth(){
		SimpleDateFormat formatter = new SimpleDateFormat("MM");
		Date curDate = new Date(System.currentTimeMillis());//获取当前时间
		String str = formatter.format(curDate);
		return str;
	}
}

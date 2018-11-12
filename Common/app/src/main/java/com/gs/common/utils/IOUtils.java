package com.gs.common.utils;

import java.io.Closeable;
import java.io.IOException;

/**
 * Copyright (C) 2016,深圳市红鸟网络科技股份有限公司 All rights reserved.
 * 项目名称：
 * 类描述：UI工具类
 * 创建人：Kevin
 * 创建时间：2016/4/26 10:53
 * 修改人：Kevin
 * 修改时间：2016/4/26 10:53
 * 修改备注：
 * Version: 1.0.0
 */
public class IOUtils {
    /**
     * 关闭流
     */
    public static boolean close(Closeable io) {
        if (io != null) {
            try {
                io.close();
            } catch (IOException e) {
                HnLogUtils.e("err", e.getMessage());
            }
        }
        return true;
    }
}

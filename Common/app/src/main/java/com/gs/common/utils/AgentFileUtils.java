/*
 *   Copyright (C)  2016 android@19code.com
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */

package com.gs.common.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.text.format.Formatter;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * FileUtils.java 文件工具类

 closeIO 关闭IO流
 isFileExist 文件是否存在
 writeFile 将字符串写入到文件
 readFile 从文件中读取字符串
 copyFileFast 快速复制
 shareFile 分享文件
 zip zip压缩
 unzip zip解压
 formatFileSize 格式化文件大小
 Stream2File 将输入流写入到文件
 createFolder 创建文件夹
 createFolder 创建文件夹(支持覆盖已存在的同名文件夹)
 getFolderName 获取文件夹名称
 deleteFile 删除目录下的文件
 openImage 打开图片
 openVideo 打开视频
 openURL 打开URL

 */
public class AgentFileUtils {
    public static final String ROOT_DIR = "AppCartoon";
    public static final String DOWNLOAD_DIR = "download";
    public static final String CACHE_DIR = "cache";
    public static final String ICON_DIR = "pics";

    /** 判断SD卡是否挂载 */
    public static boolean isSDCardAvailable() {
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            return true;
        } else {
            return false;
        }
    }

    /** 获取下载目录 */
    public static String getDownloadDir(Context context) {
        return getDir(DOWNLOAD_DIR,context);
    }

    /** 获取缓存目录 */
    public static String getCacheDir(Context context) {
        return getDir(CACHE_DIR, context);
    }

    /** 获取icon目录 */
    public static String getIconDir(Context context) {
        return getDir(ICON_DIR,context);
    }

    /** 获取应用目录，当SD卡存在时，获取SD卡上的目录，当SD卡不存在时，获取应用的cache目录 */
    public static String getDir(String name,Context context) {
        StringBuilder sb = new StringBuilder();
        if (isSDCardAvailable()) {
            sb.append(getExternalStoragePath());
        } else {
            sb.append(getCachePath(context));
        }
        sb.append(name);
        sb.append(File.separator);
        String path = sb.toString();
        if (createFolder(path)) {
            return path;
        } else {
            return null;
        }
    }
    /** 获取应用的cache目录 */
    public static String getCachePath( Context context) {
        File f =context.getCacheDir();
        if (null == f) {
            return null;
        } else {
            return f.getAbsolutePath() + "/";
        }
    }
    /** 获取SD下的应用目录 */
    public static String getExternalStoragePath() {
        StringBuilder sb = new StringBuilder();
        sb.append(Environment.getExternalStorageDirectory().getAbsolutePath());
        sb.append(File.separator);
        sb.append(ROOT_DIR);
        sb.append(File.separator);
        return sb.toString();
    }
    /**
     *
     * 获取SDCard的目录路径功能
     *
     * @return
     */
    public static String getSDCardPath() {
        File sdcardDir = null;
        // 判断SDCard是否存在
        boolean sdcardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdcardExist) {
            sdcardDir = Environment.getExternalStorageDirectory();
        }
        return sdcardDir.toString();
    }
    /**
     * 关闭IO流
     * @param closeables
     */
    public static void closeIO(Closeable... closeables) {
        if (null == closeables || closeables.length <= 0) {
            return;
        }
        for (Closeable cb : closeables) {
            try {
                if (null == cb) {
                    continue;
                }
                cb.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 删除文件
     * @param filename
     * @return
     */
    public static boolean deleteFile(String filename) {
        return new File(filename).delete();
    }

    /**
     * 文件是否存在
     * @param filePath
     * @return
     */
    public static boolean isFileExist(String filePath) {
        return new File(filePath).exists();
    }

    /**
     * 将字符串写入到文件
     * @param filename
     * @param content
     * @return
     */
    public static boolean writeFile(String filename, String content) {
        boolean isSuccess = false;
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(filename, false));
            bufferedWriter.write(content);
            isSuccess = true;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeIO(bufferedWriter);
        }
        return isSuccess;
    }

    /**
     * 从文件中读取字符串
     * @param filename
     * @return
     */
    public static String readFile(String filename) {
        File file = new File(filename);
        BufferedReader bufferedReader = null;
        String str = null;
        try {
            if (file.exists()) {
                bufferedReader = new BufferedReader(new FileReader(filename));
                str = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeIO(bufferedReader);
        }
        return str;
    }

    /**
     * 快速复制
     * @param is
     * @param os
     * @throws IOException
     */
    public static void copyFileFast(FileInputStream is, FileOutputStream os) throws IOException {
        FileChannel in = is.getChannel();
        FileChannel out = os.getChannel();
        in.transferTo(0, in.size(), out);
    }

    /**
     * 分享文件
     * @param context
     * @param title
     * @param filePath
     */
    public static void shareFile(Context context, String title, String filePath) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        Uri uri = Uri.parse("file://" + filePath);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        context.startActivity(Intent.createChooser(intent, title));
    }

    /**
     * zip压缩
     * @param is
     * @param os
     */
    public static void zip(InputStream is, OutputStream os) {
        GZIPOutputStream gzip = null;
        try {
            gzip = new GZIPOutputStream(os);
            byte[] buf = new byte[1024];
            int len;
            while ((len = is.read(buf)) != -1) {
                gzip.write(buf, 0, len);
                gzip.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeIO(is);
            closeIO(gzip);
        }
    }

    /**
     * zip解压
     * @param is
     * @param os
     */
    public static void unzip(InputStream is, OutputStream os) {
        GZIPInputStream gzip = null;
        try {
            gzip = new GZIPInputStream(is);
            byte[] buf = new byte[1024];
            int len;
            while ((len = gzip.read(buf)) != -1) {
                os.write(buf, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeIO(gzip);
            closeIO(os);
        }
    }

    /**
     * 格式化文件大小
     * @param context
     * @param size
     * @return
     */
    public static String formatFileSize(Context context, long size) {
        return Formatter.formatFileSize(context, size);
    }

    /**
     * 将输入流写入到文件
     * @param is
     * @param fileName
     */
    public static void Stream2File(InputStream is, String fileName) {
        byte[] b = new byte[1024];
        int len;
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(new File(fileName));
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
                os.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            closeIO(is);
            closeIO(os);
        }
    }

    /**
     *  创建文件夹
     * @param filePath
     * @return
     */
    public static boolean createFolder(String filePath) {
        return createFolder(filePath, false);
    }

    /**
     * 创建文件夹(支持覆盖已存在的同名文件夹)
     * @param filePath
     * @param recreate
     * @return
     */
    public static boolean createFolder(String filePath, boolean recreate) {
        String folderName = getFolderName(filePath);
        if (folderName == null || folderName.length() == 0 || folderName.trim().length() == 0) {
            return false;
        }
        File folder = new File(folderName);
        if (folder.exists()) {
            if (recreate) {
                deleteFile(folderName);
                return folder.mkdirs();
            } else {
                return true;
            }
        } else {
            return folder.mkdirs();
        }
    }

    /**
     * 获取文件夹名称
     * @param filePath
     * @return
     */
    public static String getFolderName(String filePath) {
        if (filePath == null || filePath.length() == 0 || filePath.trim().length() == 0) {
            return filePath;
        }
        int filePos = filePath.lastIndexOf(File.separator);
        return (filePos == -1) ? "" : filePath.substring(0, filePos);
    }

    /**
     * 删除目录下的文件
     * @param folder
     * @return
     */
    public static boolean deleteFiles(String folder) {
        if (folder == null || folder.length() == 0 || folder.trim().length() == 0) {
            return true;
        }
        File file = new File(folder);
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        if (!file.isDirectory()) {
            return false;
        }
        for (File f : file.listFiles()) {
            if (f.isFile()) {
                f.delete();
            } else if (f.isDirectory()) {
                deleteFile(f.getAbsolutePath());
            }
        }
        return file.delete();
    }

    /**
     * 打开图片
     * @param mContext
     * @param imagePath
     */
    public static void openImage(Context mContext, String imagePath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromFile(new File(imagePath));
        intent.setDataAndType(uri, "image/*");
        mContext.startActivity(intent);
    }

    /**
     * 打开视频
     * @param mContext
     * @param videoPath
     */
    public static void openVideo(Context mContext, String videoPath) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("oneshot", 0);
        intent.putExtra("configchange", 0);
        Uri uri = Uri.fromFile(new File(videoPath));
        intent.setDataAndType(uri, "video/*");
        mContext.startActivity(intent);
    }

    /**
     * 打开URL
     * @param mContext
     * @param url
     */
    public static void openURL(Context mContext, String url) {
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        mContext.startActivity(intent);
    }


    // 写一个文件到SDCard
    private void writeFileToSDCard(String data) throws IOException {
        // 比如可以将一个文件作为普通的文档存储，那么先获取系统默认的文档存放根目录
        File parent_path = Environment.getExternalStorageDirectory();

        // 可以建立一个子目录专门存放自己专属文件
        File dir = new File(parent_path.getAbsoluteFile(), "zhangphil");
        dir.mkdir();

        File file = new File(dir.getAbsoluteFile(), "myfile.txt");

        HnLogUtils.d("zheng", "文件路径"+file.getAbsolutePath());

        // 创建这个文件，如果不存在
        file.createNewFile();

        FileOutputStream fos = new FileOutputStream(file);

        //String data = "hello,world! Zhang Phil @ CSDN";
        byte[] buffer = data.getBytes();

        // 开始写入数据到这个文件。
        fos.write(buffer, 0, buffer.length);
        fos.flush();
        fos.close();

        HnLogUtils.d("文件写入", "成功");
    }

    /**
     * 追加文件：使用FileWriter
     *
     * @param fileName
     * @param content
     */
    private static String dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/testFile/testFile.txt";
    public static void appendFile(String content) {
        try {
            Calendar now = new GregorianCalendar();
            SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            final String time = simpleDate.format(now.getTime());

            // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(getSDPath() + "/testFile.txt", true);
            writer.write(time+"  "+content);
            writer.write("\r\n");//写入换行符
            writer.write("-------------------------------------------------");
            writer.write("\r\n");//写入换行符
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();// 获取跟目录
        }
        return sdDir.toString();
    }

}

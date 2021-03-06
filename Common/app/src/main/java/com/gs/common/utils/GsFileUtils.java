package com.gs.common.utils;

import java.io.File;


/**
 * Created by GuanSong
 * Description:
 * on 2018/11/3.
 */

public class GsFileUtils {

    /**
     * 删除已存储的文件
     */
//    public static void deletefile(String fileName) {
//        try {
//            // 找到文件所在的路径并删除该文件
//            File file = new File(Environment.getExternalStorageDirectory(), fileName);
//            file.delete();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }


    /** 删除文件，可以是文件或文件夹
     * @param delFile 要删除的文件夹或文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String delFile) {
        File file = new File(delFile);
        if (!file.exists()) {
//            Toast.makeText(HnUiUtils.getContext(), "删除文件失败:" + delFile + "不存在！", Toast.LENGTH_SHORT).show();
            HnLogUtils.e("删除文件失败:" + delFile + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteSingleFile(delFile);
            else
                return deleteDirectory(delFile);
        }
    }

    /** 删除单个文件
     * @param filePath$Name 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteSingleFile(String filePath$Name) {
        File file = new File(filePath$Name);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                HnLogUtils.e("--Method--", "Copy_Delete.deleteSingleFile: 删除单个文件" + filePath$Name + "成功！");
                return true;
            } else {
                HnLogUtils.e("删除单个文件" + filePath$Name + "失败！");
                return false;
            }
        } else {
            HnLogUtils.e("删除单个文件失败：" + filePath$Name + "不存在！");
            return false;
        }
    }

    /** 删除目录及目录下的文件
     * @param filePath 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String filePath) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!filePath.endsWith(File.separator))
            filePath = filePath + File.separator;
        File dirFile = new File(filePath);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            HnLogUtils.e("删除目录失败：" + filePath + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (File file : files) {
            // 删除子文件
            if (file.isFile()) {
                flag = deleteSingleFile(file.getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (file.isDirectory()) {
                flag = deleteDirectory(file
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            HnLogUtils.e("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            HnLogUtils.e("--Method--", "Copy_Delete.deleteDirectory: 删除目录" + filePath + "成功！");
            return true;
        } else {
            HnLogUtils.e("删除目录：" + filePath + "失败！");
            return false;
        }
    }



}

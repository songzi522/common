package com.gs.common.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by GuanSong
 * Description:Bitmap 操作类
 * on 2018/11/2.
 */

public class GsBitmapUtil {


    /**
     * 压缩图片
     *
     * @param oldPath 图片路径
     * @return
     */
    public static String compressBitmap(String oldPath) {
        BitmapFactory.Options option = new BitmapFactory.Options();
        option.inJustDecodeBounds = true;

        int sampleLength = option.outWidth > option.outHeight ? option.outHeight : option.outWidth;
        int smapleSize = sampleLength / 100;

        option.inSampleSize = smapleSize;
        option.inPreferredConfig = Bitmap.Config.RGB_565;
        option.inJustDecodeBounds = false;
        Bitmap compressedBitmap = BitmapFactory.decodeFile(oldPath, option);
        int byteCount = compressedBitmap.getByteCount();
        HnLogUtils.d(byteCount + "byte");
        return storeBitmap(compressedBitmap);
    }

    public static String storeBitmap(Bitmap photo) {
        String storePath = null;
        BufferedOutputStream bos = null;
        try {
            if (HnFileUtils.isSDCardAvailable()) {   //如果可用
                //获取sdcard目录  /storage/emulated/0/Android/data/com.gs.common/files
                storePath = HnFileUtils.getExternalStoragePath() + "files";

                ///storage/emulated/0/PH_DZY
//                storePath = Environment.getExternalStorageDirectory() + "/Gs_Common";
                HnLogUtils.e("storePath: " + storePath);
            } else {
                Toast.makeText(HnUiUtils.getContext(), "sd不可用,暂无法使用拍照上传头像功能", Toast.LENGTH_SHORT).show();
                return null;
            }

            File f = new File(storePath);

            if (!f.exists()) {
                boolean isCreate = f.mkdirs();
                if (!isCreate) {
                    Toast.makeText(HnUiUtils.getContext(), "拍照上传失败", Toast.LENGTH_SHORT).show();
                    HnLogUtils.e("拍照上传失败");
                    return null;
                }
            }

            // storage/emulated/0/Android/data/com.gs.common/files/1541383203871.jpg
            ///storage/emulated/0/PH_DZY/1541403768284.jpg
            String picPath = storePath + "/" + System.currentTimeMillis() + ".jpg";
            HnLogUtils.e("picPath: " + picPath);

            File pic = new File(picPath);
            FileOutputStream fos = new FileOutputStream(pic);
            bos = new BufferedOutputStream(fos);
            boolean isStoreSuceess = photo.compress(Bitmap.CompressFormat.JPEG, 80, bos);

            if (isStoreSuceess) {
                IOUtils.close(bos);
                return picPath;
            } else {
                Toast.makeText(HnUiUtils.getContext(), "拍照上传失败", Toast.LENGTH_SHORT).show();
                HnLogUtils.e("拍照上传失败");
                IOUtils.close(bos);
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(HnUiUtils.getContext(), "拍照上传失败", Toast.LENGTH_SHORT).show();
            HnLogUtils.e("拍照上传失败");
            IOUtils.close(bos);
            return null;
        }
    }

}

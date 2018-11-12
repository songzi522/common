package com.gs.common.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.gs.common.R;
import com.gs.common.utils.GsBitmapUtil;
import com.gs.common.utils.HnLogUtils;
import com.gs.common.utils.HnUiUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureTest3Activity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.imageView2)
    ImageView imageView2;


    //拍照
    private static final int TAKE_PHOTO = 1111;
    //从图库选取图片
    private static final int PICK_PHOTO = 2222;


    private String mIconLocalPath;

    private int mIconEdgeLength;


    //申请多个权限 相机和写入设置（CAMERA和WRITE_EXTERNAL_STORAGE）
    private static final int REQUEST_CAMERA_AND_WRITE_EXTERNAL_STORAGE = 6666;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_test3);
        ButterKnife.bind(this);

        initPermission();
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                //说明权限都已经通过，可以做你想做的事情去
                openTakePhoto();
                break;
            case R.id.btn4:
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_PHOTO);
                break;
            case R.id.btn2:
                uploadPhoto();
                break;
            case R.id.btn3:
                deletePictureFile();
                break;
        }
    }

    private void deletePictureFile() {
//        if (GsFileUtils.delete(mIconLocalPath)) {
//            HnLogUtils.e("删除成功");
//        }

//        String localCameraPath = "/storage/emulated/0/DCIM/Camera/";
//        deleteImage(localCameraPath);

        deleteImage(mIconLocalPath);

    }

    //删除图库照片
    private boolean deleteImage(String imgPath) {
        ContentResolver resolver = this.getContentResolver();
        Cursor cursor = MediaStore.Images.Media.query(resolver, MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID}, MediaStore.Images.Media.DATA + "=?",
                new String[]{imgPath}, null);
        boolean result = false;
        if (null != cursor && cursor.moveToFirst()) {
            long id = cursor.getLong(0);
            Uri contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            Uri uri = ContentUris.withAppendedId(contentUri, id);
            HnLogUtils.e("--deleteImage--uri:" + uri);
            int count = this.getContentResolver().delete(uri, null, null);
            result = count == 1;
        } else {
            File file = new File(imgPath);
            result = file.delete();
        }
        HnLogUtils.e("--deleteImage--imgPath:" + imgPath + "--result:" + result);
        return result;
    }


    //申请两个权限，相机和文件读写
    //1、首先声明一个数组permissions，将需要的权限都放在里面
    String[] permissions = new String[]{Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    //2、创建一个mPermissionList，逐个判断哪些权限未授予，未授予的权限存储到mPerrrmissionList中
    List<String> mPermissionList = new ArrayList<>();

    //权限判断和申请
    private void initPermission() {
        mPermissionList.clear();//清空没有通过的权限
        //逐个判断你要的权限是否已经通过
        for (int i = 0; i < permissions.length; i++) {
            if (ContextCompat.checkSelfPermission(this, permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                mPermissionList.add(permissions[i]);//添加还未授予的权限
            }
        }

        //申请权限
        if (mPermissionList.size() > 0) {//有权限没有通过，需要申请
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CAMERA_AND_WRITE_EXTERNAL_STORAGE);
        } else {
            //说明权限都已经通过，可以做你想做的事情去
//            openTakePhoto();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        boolean hasPermissionDismiss = false;//有权限没有通过

        //判断请求码
        switch (requestCode) {
            case REQUEST_CAMERA_AND_WRITE_EXTERNAL_STORAGE://6、相机和写入设置（CAMERA和WRITE_EXTERNAL_STORAGE）
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] == -1) {
                        hasPermissionDismiss = true;
                    }
                }

                //如果有权限没有被允许
                if (hasPermissionDismiss) {
                    //跳转到系统设置权限页面，或者直接关闭页面，不让他继续访问
                    HnLogUtils.e("未获取到权限");
//                    showPermissionDialog();
                } else {
                    //全部权限通过，可以进行下一步操作。。。
                    HnLogUtils.e("已经获取到权限");
//                    openTakePhoto();
                }
                break;
        }
    }



    /**
     * 拍照的方法
     */
    private void openTakePhoto() {
        /**
         * 在启动拍照之前最好先判断一下sdcard是否可用
         */
        String state = Environment.getExternalStorageState(); //拿到sdcard是否可用的状态码
        if (state.equals(Environment.MEDIA_MOUNTED)) {   //如果可用

            //调用后置摄像头
//            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            //调用前置摄像头
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra("android.intent.extras.CAMERA_FACING", 1);
            startActivityForResult(intent, TAKE_PHOTO);

        } else {
            Toast.makeText(HnUiUtils.getContext(), "sdcard不可用", Toast.LENGTH_SHORT).show();
        }
    }

    private Bitmap bitmapPhoto;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            String path = null;
            if (requestCode == PICK_PHOTO) {//从图库选择图片
                if (data.getData() != null || data.getExtras() != null) { //防止没有返回结果
                    Uri uri = data.getData();
                    HnLogUtils.e("uri: " + uri);
                    if (uri != null) {
                        path = getRealFilePath(HnUiUtils.getContext(), uri); //拿到图片
                        HnLogUtils.e("获取图片path: " + path);
                    }
                }
            } else if (requestCode == TAKE_PHOTO) {//拍照
                //两种方式 获取拍好的图片
                if (data.getData() != null || data.getExtras() != null) { //防止没有返回结果

                    Uri uri = data.getData();
                    HnLogUtils.e("uri: " + uri);
                    if (uri != null) {
                        path = getRealFilePath(HnUiUtils.getContext(), uri); //拿到图片
                    }

                    if (path == null) {
                        Bundle bundle = data.getExtras();
                        if (bundle != null) {
                            Bitmap photo = (Bitmap) bundle.get("data");
                            bitmapPhoto = photo;
                            path = GsBitmapUtil.storeBitmap(photo);
                        } else {
                            Toast.makeText(HnUiUtils.getContext(), "找不到图片", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            }

            // storage/emulated/0/Android/data/com.gs.common/files/1541383203871.jpg
            if (path != null) {
                mIconLocalPath = path;
//                HnLogUtils.e("mIconLocalPath: " + mIconLocalPath);
                Glide.with(PictureTest3Activity.this).load("file://" + path).into(imageView);
//                imageView.setImageBitmap(bitmapPhoto);


            }

        }

    }

    /**
     * Try to return the absolute file path from the given Uri
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePath(final Context context, final Uri uri) {
        if (null == uri)
            return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri,
                    new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

    private void uploadPhoto() {
        if (mIconLocalPath != null) {
//            HnLogUtils.e(compressBitmap(mIconLocalPath));
//            RequestClient2.postUploadPhoto(GsBitmapUtil.compressBitmap(mIconLocalPath), new AsyncHttpResponseHandler() {
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
//                    String strResponse = new String(responseBody);
//                    HnLogUtils.json(strResponse);
//
//                    try {
//                        JSONObject jsonObject = new JSONObject(strResponse);
//                        if ("SUCCESS".equals(jsonObject.getString("state"))) {
//                            HnLogUtils.e(jsonObject.getString("url"));
//                        } else {
//                            HnToast.showToastShort("头像上传失败,请重试");
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                        HnToast.showToastShort("头像上传失败,请重试");
//                    }
//                }
//
//                @Override
//                public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
//
//                }
//            });

        }

    }


}

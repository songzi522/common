package com.gs.common.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.gs.common.R;
import com.gs.common.utils.HnLogUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PictureTest2Activity extends AppCompatActivity {

    @BindView(R.id.thumbnail)
    Button thumbnail;
    @BindView(R.id.fullSize)
    Button fullSize;
    @BindView(R.id.addGallery)
    Button addGallery;
    @BindView(R.id.imageView)
    ImageView mImageView;

    private static final int REQ_THUMB = 222;
    private static final int REQ_GALLERY = 333;
    private static final int REQ_TAKE_PHOTO = 444;

    private static final int REQUEST_EXTERNAL_STORAGE = 4444;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture_test2);
        ButterKnife.bind(this);
    }

    private void init() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 200);

            if (ContextCompat.checkSelfPermission(PictureTest2Activity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                //如果不同意，就去请求权限   参数1：上下文，2：权限，3：请求码
                ActivityCompat.requestPermissions(
                        PictureTest2Activity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_EXTERNAL_STORAGE);
            }

        }


    }

    @OnClick({R.id.thumbnail, R.id.fullSize, R.id.addGallery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.thumbnail:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {//判断是否有相机应用
                    startActivityForResult(takePictureIntent, REQ_THUMB);
                }
                break;
            case R.id.fullSize:
                Intent takePictureIntent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // Ensure that there's a camera activity to handle the intent
                if (takePictureIntent2.resolveActivity(getPackageManager()) != null) {//判断是否有相机应用
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();//创建临时图片文件
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        //FileProvider 是一个特殊的 ContentProvider 的子类，
                        //它使用 content:// Uri 代替了 file:/// Uri. ，更便利而且安全的为另一个app分享文件
                        Uri photoURI = FileProvider.getUriForFile(PictureTest2Activity.this,
                                "com.gs.common.fileprovider",
                                photoFile);
                        HnLogUtils.e("photoURI:" + photoURI.toString());
                        takePictureIntent2.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                        startActivityForResult(takePictureIntent2, REQ_TAKE_PHOTO);
                    }
                }
                break;
            case R.id.addGallery:
                break;
        }

    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        //.getExternalFilesDir()方法可以获取到 SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //创建临时文件,文件前缀不能少于三个字符,后缀如果为空默认未".tmp"
        File image = File.createTempFile(
                imageFileName,  /* 前缀 */
                ".jpg",         /* 后缀 */
                storageDir      /* 文件夹 */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQ_THUMB:
                if (resultCode != Activity.RESULT_OK) return;
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                mImageView.setImageBitmap(imageBitmap);
                break;
            case REQ_TAKE_PHOTO://返回结果
                if (resultCode != Activity.RESULT_OK) return;

                // Get the dimensions of the View
                int targetW = mImageView.getWidth();
                int targetH = mImageView.getHeight();

                // Get the dimensions of the bitmap
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                bmOptions.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                int photoW = bmOptions.outWidth;
                int photoH = bmOptions.outHeight;

                // Determine how much to scale down the image
                int scaleFactor = Math.min(photoW / targetW, photoH / targetH);

                // Decode the image file into a Bitmap sized to fill the View
                bmOptions.inJustDecodeBounds = false;
                bmOptions.inSampleSize = scaleFactor;
                bmOptions.inPurgeable = true;

                Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
                mImageView.setImageBitmap(bitmap);
                break;

        }


    }
}

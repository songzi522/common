package com.gs.common.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PerActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    private static final int REQUEST_CALL_PHONE = 1111;
    private static final int REQUEST_LOCATION = 2222;
    private static final int REQUEST_OVERLAY = 3333;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                if (ContextCompat.checkSelfPermission(PerActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //如果不同意，就去请求权限   参数1：上下文，2：权限，3：请求码
                    ActivityCompat.requestPermissions(
                            PerActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            REQUEST_CALL_PHONE);
                } else {
                    //同意就拨打
//                    call();
                }
                break;
            case R.id.btn2:
                if (ContextCompat.checkSelfPermission(PerActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    //如果不同意，就去请求权限   参数1：上下文，2：权限，3：请求码
                    ActivityCompat.requestPermissions(
                            PerActivity.this,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                            REQUEST_LOCATION);
                }
                break;
            case R.id.btn3:
                if (Build.VERSION.SDK_INT >= 23) {
                    if (!Settings.canDrawOverlays(PerActivity.this)) {
                        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                Uri.parse("package:" + getPackageName()));
                        startActivityForResult(intent, REQUEST_OVERLAY);
                    }else {
                        Toast.makeText(this, "已经获取到浮窗权限", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.btn4:
                break;
        }
    }

    private void call() {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:10086"));
        try {
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //判断请求码
        switch (requestCode) {
            case REQUEST_CALL_PHONE:
                //如果同意，就拨打
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "已经获取到电话权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "未获取到电话权限", Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "已经获取到定位权限", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "未获取到定位权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_OVERLAY) {
            if (Build.VERSION.SDK_INT >= 23) {
                if (!Settings.canDrawOverlays(this)) {
                    // SYSTEM_ALERT_WINDOW permission not granted...
                    Toast.makeText(PerActivity.this, "没有获取到浮窗权限", Toast.LENGTH_SHORT);
                }
            }
        }

    }




}

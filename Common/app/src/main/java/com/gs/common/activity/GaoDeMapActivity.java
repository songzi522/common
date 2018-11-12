package com.gs.common.activity;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.gs.common.R;
import com.gs.common.dialog.LocalDialogInterface;
import com.gs.common.dialog.NormalDialog;
import com.gs.common.utils.LocationUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class GaoDeMapActivity extends AppCompatActivity {

    @BindView(R.id.tvLatitude)
    TextView tvLatitude;
    @BindView(R.id.tvLongitude)
    TextView tvLongitude;

    private LocationUtils mLocationUtils;
    private double mLatitude;
    private double mLongitude;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gao_de_map);
        ButterKnife.bind(this);

        getLocation();

    }

    private void getLocation() {
        mLocationUtils = new LocationUtils();
        mLocationUtils.setOnLocationListener(new LocationUtils.OnLocationListener() {
            @Override
            public void onLocationListener(String address, String country, String province, String city,
                                           String district, String street, String streetNum, double latitude,
                                           double longitude) {
                mLatitude = latitude;
                mLongitude = longitude;

//                Log.e("哈哈1", String.valueOf(latitude));
//                Log.e("哈哈2", String.valueOf(longitude));

                /**
                 * 在android6.0以上系统需要手动设置定位权限，否则无法获取到该权限
                 */
                tvLatitude.setText(String.valueOf("纬度：" + mLatitude));
                tvLongitude.setText(String.valueOf("经度：" + mLongitude));

            }
        });

        mLocationUtils.setOnLocationErrorListener(new LocationUtils.OnLocationErrorListener() {
            @Override
            public void onLocationErrorListener(AMapLocation aMapLocation) {
                if (aMapLocation.getErrorCode() == 12) {
                    Toast.makeText(GaoDeMapActivity.this, "定位失败，请打开定位权限", Toast.LENGTH_SHORT).show();
                    showOpenGPsDialog();
                    return;
                } else {
                    Toast.makeText(GaoDeMapActivity.this, "定位失败", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private int GPS_REQUEST_CODE = 10;

    private void showOpenGPsDialog() {
        new NormalDialog.Builder(GaoDeMapActivity.this)
                .setTitleVisible(true)
                .setTitleText("温馨提示")
                .setContentText("定位失败，请打开定位权限")
                .setOnclickListener(new LocalDialogInterface.OnLeftAndRightClickListener<NormalDialog>() {
                    @Override
                    public void clickLeftButton(NormalDialog dialog, View view) {
//                        Toast.makeText(GaoDeMapActivity.this, "左边按钮", Toast.LENGTH_SHORT).show();
//                        openGPS(GaoDeMapActivity.this);
//                        setLocationMode(GaoDeMapActivity.this, Settings.Secure.LOCATION_MODE_HIGH_ACCURACY);
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        startActivityForResult(intent, GPS_REQUEST_CODE);

                        dialog.dismiss();
                    }

                    @Override
                    public void clickRightButton(NormalDialog dialog, View view) {
                        Toast.makeText(GaoDeMapActivity.this, "右边按钮", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                })
                .build()
                .show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GPS_REQUEST_CODE) {
            //做需要做的事情，比如再次检测是否打开GPS了 或者定位
            Toast.makeText(GaoDeMapActivity.this, "定位已经打开，请重新登陆", Toast.LENGTH_SHORT).show();
            getLocation();
        }
    }

    /**
     * 此方法 android4.0以后禁用
     *
     * 强制帮用户打开GPS
     *
     * @param context
     */
    public static final void openGPS(Context context) {
        Intent GPSIntent = new Intent();
        GPSIntent.setClassName("com.android.settings",
                "com.android.settings.widget.SettingsAppWidgetProvider");
        GPSIntent.addCategory("android.intent.category.ALTERNATIVE");
        GPSIntent.setData(Uri.parse("custom:3"));
        try {
            PendingIntent.getBroadcast(context, 0, GPSIntent, 0).send();
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }



    /**
     * 测试环境：Android5.0+

     测试目标：强制打开GPS

     测试所需必备条件：测试APP必须具有ROOT权限，或者是system级别app

     本项目在获取该权限是遇到阻碍：<uses-permission android:name="android.permission.WRITE_SECURE_SETTINGS" />
     Permission is only granted to system apps。 由于本项目不是系统级别APP 因此无法获取此权限
     *
     * mode can be one of:
     * android.provider.Settings.Secure.LOCATION_MODE_HIGH_ACCURACY
     * android.provider.Settings.Secure.LOCATION_MODE_OFF
     * android.provider.Settings.Secure.LOCATION_MODE_SENSORS_ONLY;
     * android.provider.Settings.Secure.LOCATION_MODE_BATTERY_SAVING
     *
     * @param context
     * @param mode
     */
    public static void setLocationMode(Context context, int mode) {
        Intent intent = new Intent("com.android.settings.location.MODE_CHANGING");
        int currentMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE,
                Settings.Secure.LOCATION_MODE_OFF);
        intent.putExtra("CURRENT_MODE", currentMode);
        intent.putExtra("NEW_MODE", mode);

        Log.e("jerry", "currentMode=" + currentMode + " newmode=" + mode);
        context.sendBroadcast(intent, android.Manifest.permission.WRITE_SECURE_SETTINGS);
        Settings.Secure.putInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE, mode);
    }


}
package com.gs.common.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gs.common.R;
import com.gs.common.utils.LocationUtils2;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 *  http://blog.csdn.net/qq_33689414/article/details/54136922
 */
public class GetLocationActivity extends AppCompatActivity {

    private boolean flag;

    private static Context context;

    @BindView(R.id.gsp_btn)
    Button gspBtn;
    @BindView(R.id.network_btn)
    Button networkBtn;
    @BindView(R.id.best_btn)
    Button bestBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        ButterKnife.bind(this);

        context = getApplicationContext();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initPermission();//针对6.0以上版本做权限适配
    }

    @OnClick({R.id.gsp_btn, R.id.network_btn, R.id.best_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.gsp_btn:
                if (flag) {
                    getGPSLocation();
                } else {
                    Toast.makeText(this, "no permission", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.network_btn:
                if (flag) {
                    getNetworkLocation();
                } else {
                    Toast.makeText(this, "no permission", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.best_btn:
                if (flag) {
                    getBestLocation();
                } else {
                    Toast.makeText(this, "no permission", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void initPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //检查权限
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            } else {
                flag = true;
            }
        } else {
            flag = true;
        }
    }

    /**
     * 权限的结果回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            flag = grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED;
        }
    }


    /**
     * 通过GPS获取定位信息
     */
    public void getGPSLocation() {
        Location gps = LocationUtils2.getGPSLocation(this);
        if (gps == null) {
            //设置定位监听，因为GPS定位，第一次进来可能获取不到，通过设置监听，可以在有效的时间范围内获取定位信息
            LocationUtils2.addLocationListener(context, LocationManager.GPS_PROVIDER, new LocationUtils2.ILocationListener() {
                @Override
                public void onSuccessLocation(Location location) {
                    if (location != null) {
                        Toast.makeText(GetLocationActivity.this, "gps onSuccessLocation location:  lat==" + location.getLatitude() + "     lng==" + location.getLongitude(), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(GetLocationActivity.this, "gps location is null", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(this, "gps location: lat==" + gps.getLatitude() + "  lng==" + gps.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 通过网络等获取定位信息
     */
    private void getNetworkLocation() {
        Location net = LocationUtils2.getNetWorkLocation(this);
        if (net == null) {
            Toast.makeText(this, "net location is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "network location: lat==" + net.getLatitude() + "  lng==" + net.getLongitude(),
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 采用最好的方式获取定位信息
     */
    private void getBestLocation() {
        Criteria c = new Criteria();//Criteria类是设置定位的标准信息（系统会根据你的要求，匹配最适合你的定位供应商），一个定位的辅助信息的类
        c.setPowerRequirement(Criteria.POWER_LOW);//设置低耗电
        c.setAltitudeRequired(true);//设置需要海拔
        c.setBearingAccuracy(Criteria.ACCURACY_COARSE);//设置COARSE精度标准
        c.setAccuracy(Criteria.ACCURACY_LOW);//设置低精度
        //... Criteria 还有其他属性，就不一一介绍了
        Location best = LocationUtils2.getBestLocation(this, c);
        if (best == null) {
            Toast.makeText(this, " best location is null", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "best location: lat==" + best.getLatitude() + " lng==" + best.getLongitude(), Toast.LENGTH_SHORT).show();
        }
    }
}

package com.gs.common.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.gs.common.R;
import com.gs.common.utils.LocationUtils;
import com.gs.common.utils.LocationUtils3;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * http://blog.csdn.net/wenzhi20102321/article/details/54585648
 */
public class GetLocation3Activity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;

    //定位都要通过LocationManager这个类实现
    private LocationManager locationManager;
    private String provider; //是否为网络位置控制器
    //当前可用的位置控制器
    List<String> list;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location3);
        ButterKnife.bind(this);

        textView = (TextView) findViewById(R.id.tv);
        //获取定位服务
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //获取当前可用的位置控制器
        list = locationManager.getProviders(true);
        //检查是否打开了GPS或网络定位
        if (list.contains(LocationManager.GPS_PROVIDER)) {
            //是否为GPS位置控制器
            provider = LocationManager.GPS_PROVIDER;
            textView.append("GPS位置控制器" + "\n");
        } else if (list.contains(LocationManager.NETWORK_PROVIDER)) {
            //是否为网络位置控制器
            provider = LocationManager.NETWORK_PROVIDER;
            textView.append("网络位置控制器" + "\n");
        } else {
            Toast.makeText(this, "请检查网络或GPS是否打开", Toast.LENGTH_LONG).show();
            return;
        }
    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        //初始化
        LocationUtils3.initLocation(GetLocation3Activity.this);
        //获取经纬度
        Log.e("经度：", LocationUtils3.longitude + "");
        Log.e("纬度：", LocationUtils3.latitude + "");
    }

    //获得我所在的位置的经纬度
    public void getLocation(View view) {
        if (Build.VERSION.SDK_INT >= 23) {
            //如果用户并没有同意该权限
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
            }
        } else {//低版本手机，直接获取位置信息

        }
        Location location = locationManager.getLastKnownLocation(provider);
        if (location != null) {
            //获取当前位置，这里只用到了经纬度
            String string = " 纬度为：" + location.getLatitude() + ",经度为："
                    + location.getLongitude();
            Toast.makeText(this, string, Toast.LENGTH_SHORT).show();
            textView.append(string + "\n");
        }

    }

    //位置的监听
    public void bandLocationListener(View view) {
        //绑定定位事件，监听位置是否改变
        //第一个参数为控制器类型第二个参数为监听位置变化的时间间隔（单位：毫秒）
        //第三个参数为位置变化的间隔（单位：米）第四个参数为位置监听器
        locationManager.requestLocationUpdates(provider, 2000, 2,
                locationListener);
        if (Build.VERSION.SDK_INT >= 23) {

            //如果用户并没有同意该权限
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //申请权限
                // requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);
            } else {
                //有权限直接获取地理位置

            }

        } else {//低版本手机，直接获取位置信息


        }
    }

    LocationListener locationListener = new LocationListener() {

        @Override
        //状态改变的回调方法
        public void onStatusChanged(String arg0, int arg1, Bundle arg2) {

        }

        @Override
        //提供者（网络或GPS）被打开的回调方法
        public void onProviderEnabled(String arg0) {
        }

        @Override
        //提供者（网络或GPS）被关闭的回调方法
        public void onProviderDisabled(String arg0) {
        }

        @Override
        //经纬度改变的回调方法，基本都是使用这个回调方法
        public void onLocationChanged(Location arg0) {
            // 更新当前经纬度
            // Toast.makeText(MainActivity.this, "" + arg0.toString(), Toast.LENGTH_SHORT).show();
            textView.append("经度：" + arg0.getLongitude() + "，纬度：" + arg0.getLatitude() + "\n");
        }
    };

    //关闭时解除监听器
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (locationManager != null) {
            if (Build.VERSION.SDK_INT >= 23) {

                //如果用户并没有同意该权限
                if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    //申请权限
                }
            }
            locationManager.removeUpdates(locationListener);
        }
    }


}

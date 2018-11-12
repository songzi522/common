package com.gs.common.utils;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.gs.common.MyApplication;

/*

Copyright (C) 2017,深圳市红鸟网络科技股份有限公司 All rights reserved.
项目名称：yingmi2
类描述：
创建人：Daniel Wu
创建时间：2017/8/10 14:59
修改人：
修改时间：2017/8/10 14:59
修改备注：
Version: 1.0.0
*/
public class LocationUtils {
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    private AMapLocationClientOption mLocationOption = null;
    private OnLocationListener onLocationListener;
    private OnLocationErrorListener onLocationErrorListener;
    private getLonLatListener getLonLatListener;

    public LocationUtils() {
        getLocation();
    }

    public void setOnLocationListener(OnLocationListener onLocationListener) {
        this.onLocationListener = onLocationListener;
    }

    public void setOnLocationErrorListener(OnLocationErrorListener onLocationErrorListener) {
        this.onLocationErrorListener = onLocationErrorListener;
    }

    public void setgetLonLatListener(LocationUtils.getLonLatListener getLonLatListener) {
        this.getLonLatListener = getLonLatListener;
    }

    //声明定位回调监听器
    public AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    //可在其中解析amapLocation获取相应内容。
                    String address = aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                    HnPrefUtils.setString(PrefKey.Location.ADDRESS, address);
                    String country = aMapLocation.getCountry();//国家信息
//                    HnPrefUtils.setString(PrefKey.Location.COUNTRY, country);
                    String province = aMapLocation.getProvince();//省信息
//                    HnPrefUtils.setString(PrefKey.Location.PROVINCE, province);
                    String city = aMapLocation.getCity();//城市信息
//                    HnPrefUtils.setString(PrefKey.Location.CITY, city);
                    String district = aMapLocation.getDistrict();//城区信息
//                    HnPrefUtils.setString(PrefKey.Location.COUNTY, district);
                    String street = aMapLocation.getStreet();//街道信息
//                    HnPrefUtils.setString(PrefKey.Location.STREET, street);
                    String streetNum = aMapLocation.getStreetNum();//街道门牌号信息
//                    HnPrefUtils.setString(PrefKey.Location.STREET_NUM, streetNum);

                    HnLogUtils.d(
                            "address:------" + address
                                    + "-----country:------" + country
                                    + "-----province:------" + province
                                    + "-----city:------" + city
                                    + "-----district:------" + district
                                    + "-----street:------" + street
                                    + "-----streetNum:------" + streetNum
                    );

                    if (onLocationListener != null) {
                        onLocationListener.onLocationListener(address, country, province, city,
                                district, street, streetNum, aMapLocation.getLatitude(), aMapLocation.getLongitude());
                    }
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    HnLogUtils.e("location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                    if (onLocationErrorListener != null) {
                        onLocationErrorListener.onLocationErrorListener(aMapLocation);
                    }
                }
            }
            mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
            mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        }
    };

    public void getLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(MyApplication.getContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();

        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。会同时使用网络定位和GPS定位，
        // 优先返回最高精度的定位结果，以及对应的地址描述信息。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);

        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。不会使用GPS和其他传感器，
        // 只会使用网络定位（Wi-Fi和基站定位）
                mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);

        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。不需要连接网络，只使用GPS进行定位，
        // 这种模式下不支持室内环境的定位，自 v2.9.0 版本支持返回地址描述信息。
        //        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);

        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。
        // 如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);

        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();
    }

    public interface OnLocationListener {
        void onLocationListener(String address, String country, String province, String city,
                                String district, String street, String streetNum, double latitude, double longitude);
    }

    public interface OnLocationErrorListener {
        void onLocationErrorListener(AMapLocation aMapLocation);
    }

    public interface getLonLatListener {
        void getLatLon(double longititude, double latitude);
    }

}

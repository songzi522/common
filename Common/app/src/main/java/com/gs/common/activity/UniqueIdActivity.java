package com.gs.common.activity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gs.common.R;

import java.lang.reflect.Method;
import java.net.NetworkInterface;
import java.net.SocketException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UniqueIdActivity extends AppCompatActivity {

    String imeistring = null;

    String imsistring = null;

    TelephonyManager telephonyManager;


    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.tvImei)
    TextView tvImei;
    @BindView(R.id.tv2)
    TextView tv2;
    @BindView(R.id.tvImsi)
    TextView tvImsi;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;
    @BindView(R.id.tv3)
    TextView tv3;
    @BindView(R.id.tvSerialnum)
    TextView tvSerialnum;
    @BindView(R.id.tv4)
    TextView tv4;
    @BindView(R.id.tvAndroidID)
    TextView tvAndroidID;
    @BindView(R.id.tv5)
    TextView tv5;
    @BindView(R.id.tvMacAddress)
    TextView tvMacAddress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niqueuid);
        ButterKnife.bind(this);

        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        String imei = telephonyManager.getDeviceId();
        String imsi = telephonyManager.getSubscriberId();
        String mtype = Build.MODEL; // 手机型号
        String numer = telephonyManager.getLine1Number(); // 手机号码，有的可得，有的不可得

        /**
         * getDeviceId() function Returns the unique device ID.
         * for example,the IMEI for GSM and the MEID or ESN for CDMA phones.
         */
        imeistring = telephonyManager.getDeviceId();

        /**
         * getSubscriberId() function Returns the unique subscriber ID,
         * for example, the IMSI for a GSM phone.
         */
        imsistring = telephonyManager.getSubscriberId();

        tvImei.setText(imeistring);
        tvImsi.setText(imsistring);

        Log.e("哈哈", "imei: " + imeistring);
        Log.e("哈哈", "imsi: " + imsistring);


        String serialnum = null;

        try {

            Class<?> c = Class.forName("android.os.SystemProperties");

            Method get = c.getMethod("get", String.class, String.class);

            serialnum = (String) (get.invoke(c, "ro.serialno", "unknown"));

            tvSerialnum.setText(serialnum);

            Log.e("哈哈", "序列号: " + serialnum);

        } catch (Exception ignored) {

        }


        String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        tvAndroidID.setText(androidId);

        Log.e("哈哈", "androidId: " + androidId);


//        tvMacAddress.setText(getMacAddress());
//
//        Log.e("哈哈", "getMacAddress(): " + getMacAddress());
    }


    public static String getMacAddress() {
 /*获取mac地址有一点需要注意的就是android 6.0版本后，以下注释方法不再适用，不管任何手机都会返回"02:00:00:00:00:00"这个默认的mac地址，这是googel官方为了加强权限管理而禁用了getSYstemService(Context.WIFI_SERVICE)方法来获得mac地址。*/
        //        String macAddress= "";
//        WifiManager wifiManager = (WifiManager) MyApp.getContext().getSystemService(Context.WIFI_SERVICE);
//        WifiInfo wifiInfo = wifiManager.getConnectionInfo();
//        macAddress = wifiInfo.getMacAddress();
//        return macAddress;

        String macAddress = null;
        StringBuffer buf = new StringBuffer();
        NetworkInterface networkInterface = null;
        try {
            networkInterface = NetworkInterface.getByName("eth1");
            if (networkInterface == null) {
                networkInterface = NetworkInterface.getByName("wlan0");
            }
            if (networkInterface == null) {
                return "02:00:00:00:00:02";
            }
            byte[] addr = networkInterface.getHardwareAddress();
            for (byte b : addr) {
                buf.append(String.format("%02X:", b));
            }
            if (buf.length() > 0) {
                buf.deleteCharAt(buf.length() - 1);
            }
            macAddress = buf.toString();
        } catch (SocketException e) {
            e.printStackTrace();
            return "02:00:00:00:00:02";
        }
        return macAddress;
    }


}

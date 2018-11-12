package com.gs.common;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.gs.common.activity.BaseShowActivity;
import com.gs.common.activity.CheckPermissionsActivity;
import com.gs.common.activity.DialogActivity;
import com.gs.common.activity.GetUriParameterActivity;
import com.gs.common.activity.GlideActivity;
import com.gs.common.activity.HorizontalCenterActivity;
import com.gs.common.activity.ListViewActivity;
import com.gs.common.activity.MapAndLocationActivity;
import com.gs.common.activity.OkhttpActivity;
import com.gs.common.activity.PerActivity;
import com.gs.common.activity.PercentFrameLayoutAty;
import com.gs.common.activity.PictureTest2Activity;
import com.gs.common.activity.PictureTest3Activity;
import com.gs.common.activity.PictureTest4Activity;
import com.gs.common.activity.QRCodeActivity;
import com.gs.common.activity.RvActivity;
import com.gs.common.activity.ShowLoadDialogActivity;
import com.gs.common.activity.ThreadActivity;
import com.gs.common.activity.ToastActivity;
import com.gs.common.activity.ToolBarActivity;
import com.gs.common.activity.UniqueIdActivity;
import com.gs.common.activity.WeiXinActivity;
import com.gs.common.activity.count_timer.CountTimerActivity;
import com.gs.common.activity.lifeCycle.LifeCycleActivity;
import com.gs.common.activity.post_data.PostDataActivity;
import com.gs.common.activity.rx_android.RxAndroidActivity;
import com.gs.common.activity.test.TestActivity;
import com.gs.common.animation.AnimationActivity;
import com.gs.common.broadcast.BroadCastActivity;
import com.gs.common.dispatchTouchEvent.DispatchTouchEventActivity;
import com.gs.common.fragments.FragmentTestActivity;
import com.gs.common.interfacetest2.InterfaceTestActivity;
import com.gs.common.service.ServiceTestActivity;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick({R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.btn10, R.id.btn11, R.id.btn12, R.id.btn13, R.id.btn14, R.id.btn15,
            R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19, R.id.btn20, R.id.btn21, R.id.btn22, R.id.btn23,
            R.id.btn24, R.id.btn25, R.id.btn26, R.id.btn27, R.id.btn28, R.id.btn29, R.id.btn30, R.id.btn31
            , R.id.btn32, R.id.btn33, R.id.btn34, R.id.btn35})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn31:
                startActivity(new Intent(MainActivity.this, PictureTest2Activity.class));
                break;
            case R.id.btn32:
                startActivity(new Intent(MainActivity.this, PictureTest3Activity.class));
                break;
            case R.id.btn33:
                startActivity(new Intent(MainActivity.this, PictureTest4Activity.class));
                break;
            case R.id.btn34:
                break;
            case R.id.btn35:
                break;
            case R.id.btn0:
                startActivity(new Intent(MainActivity.this, UniqueIdActivity.class));
                break;
            case R.id.btn1:
                startActivity(new Intent(MainActivity.this, ThreadActivity.class));
                break;
            case R.id.btn2:
                startActivity(new Intent(MainActivity.this, DialogActivity.class));
                break;
            case R.id.btn3:
                startActivity(new Intent(MainActivity.this, AnimationActivity.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(MainActivity.this, QRCodeActivity.class));
                break;
            case R.id.btn5:
                startActivity(new Intent(MainActivity.this, GlideActivity.class));
                break;
            case R.id.btn6:
                startActivity(new Intent(MainActivity.this, OkhttpActivity.class));
                break;
            case R.id.btn7:
                startActivity(new Intent(MainActivity.this, PerActivity.class));
                break;
            case R.id.btn8:
                startActivity(new Intent(MainActivity.this, ToolBarActivity.class));
                break;
            case R.id.btn9:
                startActivity(new Intent(MainActivity.this, RxAndroidActivity.class));
                break;
            case R.id.btn10:
                startActivity(new Intent(MainActivity.this, ToastActivity.class));
                break;
            case R.id.btn11:
                startActivity(new Intent(MainActivity.this, WeiXinActivity.class));
                break;
            case R.id.btn12:
                startActivity(new Intent(MainActivity.this, ListViewActivity.class));
                break;
            case R.id.btn13:
                startActivity(new Intent(MainActivity.this, PercentFrameLayoutAty.class));
                break;
            case R.id.btn14:
                startActivity(new Intent(MainActivity.this, RvActivity.class));
                break;
            case R.id.btn15:
                startActivity(new Intent(MainActivity.this, PostDataActivity.class));
                break;
            case R.id.btn16:
                startActivity(new Intent(MainActivity.this, CountTimerActivity.class));
                break;
            case R.id.btn17:
                startActivity(new Intent(MainActivity.this, BaseShowActivity.class));
                break;
            case R.id.btn18:
                startActivity(new Intent(MainActivity.this, ShowLoadDialogActivity.class));
                break;
            case R.id.btn19:
                startActivity(new Intent(MainActivity.this, MapAndLocationActivity.class));
                break;
            case R.id.btn20:
                startActivity(new Intent(MainActivity.this, CheckPermissionsActivity.class));
                break;
            case R.id.btn21:
                startActivity(new Intent(MainActivity.this, GetUriParameterActivity.class));
                break;
            case R.id.btn22:
                startActivity(new Intent(MainActivity.this, InterfaceTestActivity.class));
                break;
            case R.id.btn23:
                startActivity(new Intent(MainActivity.this, TestActivity.class));
                break;
            case R.id.btn24:
                startActivity(new Intent(MainActivity.this, LifeCycleActivity.class));
                break;
            case R.id.btn25:
                startActivity(new Intent(MainActivity.this, ServiceTestActivity.class));
                break;
            case R.id.btn26:
                startActivity(new Intent(MainActivity.this, FragmentTestActivity.class));
                break;
            case R.id.btn27:
                startActivity(new Intent(MainActivity.this, DispatchTouchEventActivity.class));
                break;
            case R.id.btn28:
                startActivity(new Intent(MainActivity.this, InterfaceTestActivity.class));
                break;
            case R.id.btn29:
                startActivity(new Intent(MainActivity.this, BroadCastActivity.class));
                break;
            case R.id.btn30:
                startActivity(new Intent(MainActivity.this, HorizontalCenterActivity.class));
                break;


        }


    }


    //获取进程ID和进程名称
    private void getProcessId() {
        int pid = android.os.Process.myPid();
        String processName = "";
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        List<ActivityManager.RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : list){
            if (runningAppProcessInfo.pid == pid){
                processName = runningAppProcessInfo.processName;
            }
        }

        //获取之前进程id,然后通过id杀死那个进程
//        android.os.Process.killProcess(android.os.Process.myPid());

//        Log.e(TAG,"当前进程id:" + pid);
//        Log.e(TAG,"当前进程名称:" + processName);
    }


}

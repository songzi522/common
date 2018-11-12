package com.gs.common.activity.base;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.gs.common.R;

public class Base1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base1);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            new AlertDialog.Builder(Base1Activity.this)
                    .setTitle("温馨提示")//设置对话框标题
                    .setMessage("确定要退出吗？")//设置显示的内容
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                            Toast.makeText(Base1Activity.this, "点击了确定", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("返回", new DialogInterface.OnClickListener() {//添加返回按钮
                        @Override
                        public void onClick(DialogInterface dialog, int which) {//响应事件
                        }
                    }).show();//在按键响应事件中显示此对话框
        }
        return false;
    }

}

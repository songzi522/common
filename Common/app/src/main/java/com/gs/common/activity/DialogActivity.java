package com.gs.common.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.gs.common.R;
import com.gs.common.dialog.BottomSelectDialog;
import com.gs.common.dialog.LocalDialogInterface;
import com.gs.common.dialog.NormalDialog;
import com.gs.common.dialog.SingleBtnDialog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DialogActivity extends AppCompatActivity {

    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;
    @BindView(R.id.btn5)
    Button btn5;
    @BindView(R.id.btn6)
    Button btn6;
    @BindView(R.id.btn7)
    Button btn7;
    @BindView(R.id.btn8)
    Button btn8;
    @BindView(R.id.btn9)
    Button btn9;
    @BindView(R.id.btn10)
    Button btn10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7,
            R.id.btn8, R.id.btn9, R.id.btn10})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                new AlertDialog.Builder(DialogActivity.this)
                        .setTitle("温馨提示")//设置对话框标题
                        .setMessage("确定要退出吗？")//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                finish();
                            }
                        })
                        .setNegativeButton("返回", new DialogInterface.OnClickListener() {//添加返回按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//响应事件
                                Log.i("alertdialog", " 请保存数据！");
                            }
                        }).show();//在按键响应事件中显示此对话框
                break;

            case R.id.btn2:
                new AlertDialog.Builder(this)
                        .setTitle("标题")
                        .setMessage("简单的消息提示框")
                        .show();
                break;

            case R.id.btn3:
                new AlertDialog.Builder(this)
                        .setView(R.layout.dialog_commom2)
                        .show();
                break;

            case R.id.btn4:
                new AlertDialog.Builder(this)
                        .setView(R.layout.dialog_commom2)
                        .setPositiveButton("支付看结果", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        })
                        .show();
                break;
            case R.id.btn5:
                new SingleBtnDialog.Builder(DialogActivity.this)
                        .setTvMiddle("4")
                        .setTvLeft("左")
                        .setTvRight("右")
                        .setTvDetails("细节")
                        .setTvBottom("底部")
                        .setSingleListener(new LocalDialogInterface.OnSingleClickListener<SingleBtnDialog>() {
                            @Override
                            public void clickSingleButton(SingleBtnDialog dialog, View view) {
                                Toast.makeText(DialogActivity.this, "哈哈", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        })
                        .build()
                        .show();
                break;
            case R.id.btn6:
                new BottomSelectDialog.Builder(DialogActivity.this)
                        .setOnWechatPayClickListener(new LocalDialogInterface.OnWechatPayClickListener<BottomSelectDialog>() {
                            @Override
                            public void clickWechatPayButton(BottomSelectDialog dialog, View view) {
                                Toast.makeText(DialogActivity.this, "WechatPay", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build()
                        .show();
                break;
            case R.id.btn7:
                new NormalDialog.Builder(DialogActivity.this)
                        .setTitleVisible(true)
                        .setTitleText("温馨提示")
                        .setContentText("内容一")
                        .setLeftBtnText("游客登录")
                        .setRightBtnText("登录医悦")
                        .setSingleBtnText("确定")
//                        .setSingleMode(true)
//                        .setNoBtnMode(true)
                        .setOnclickListener(new LocalDialogInterface.OnLeftAndRightClickListener<NormalDialog>() {
                            @Override
                            public void clickLeftButton(NormalDialog dialog, View view) {
                                Toast.makeText(DialogActivity.this, "左边按钮", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void clickRightButton(NormalDialog dialog, View view) {
                                Toast.makeText(DialogActivity.this, "右边按钮", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setSingleListener(new LocalDialogInterface.OnSingleClickListener<NormalDialog>() {
                            @Override
                            public void clickSingleButton(NormalDialog dialog, View view) {
                                Toast.makeText(DialogActivity.this, "SingleButton", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build()
                        .show();
                break;

            case R.id.btn8:
                startActivity(new Intent(DialogActivity.this,ShowWebViewByDialogAty.class));
                break;
            case R.id.btn9:
                break;
            case R.id.btn10:
                break;
        }
    }


}

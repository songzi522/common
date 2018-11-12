package com.gs.common.activity.post_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gs.common.R;
import com.gs.common.utils.ToastUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class StartAtyForResTest1Activity extends AppCompatActivity {

    private final int request_code_1 = 1001;

    private static final String TAG = "StartAtyForResTest1Acti";

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
    @BindView(R.id.tv1)
    TextView tv1;

    public static final String TAG_LATITUDE = "tag_latitude";
    public static final String TAG_LONGITUDE = "tag_longitude";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_aty_for_res_test1);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
//                Intent intent1 = new Intent(StartAtyForResTest1Activity.this, StartAtyForResTest2Activity.class);
//                startActivityForResult(intent1, request_code_1);
                break;
            case R.id.btn2:
//                Intent intent = new Intent(StartAtyForResTest1Activity.this, StartAtyForResTest2Activity.class);
//                Bundle bundle = new Bundle();
//                bundle.putDouble(TAG_LATITUDE, 23.7250117360);
//                bundle.putDouble(TAG_LONGITUDE,112.0605468750);
//                intent.putExtras(bundle);
//                startActivity(intent);
//                finish();
                break;
            case R.id.btn3:
                Intent intent = new Intent(StartAtyForResTest1Activity.this, StartAtyForResTest2Activity.class);
                intent.putExtra(TAG_LONGITUDE, 112.0605468750 + "");
                startActivity(intent);
                finish();
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == request_code_1 && resultCode == 2) {
            String intentPostData = data.getStringExtra("intent_post");
            ToastUtil.showToastShort(intentPostData);
            tv1.setText(intentPostData);
        }

    }

}

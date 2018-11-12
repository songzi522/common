package com.gs.common.activity.post_data;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.gs.common.activity.post_data.StartAtyForResTest1Activity.TAG_LONGITUDE;

public class StartAtyForResTest2Activity extends AppCompatActivity {

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

    private static final String TAG = "StartAtyForResTest2Acti";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_aty_for_res_test2);
        ButterKnife.bind(this);


//        Bundle bundle = new Bundle();
//        bundle = getIntent().getExtras();
//        double s2 = bundle.getDouble(StartAtyForResTest1Activity.TAG_LONGITUDE);
//        Log.e(TAG, s2 + "");


        /**
         * case R.id.btn3:
         Intent intent = new Intent(StartAtyForResTest1Activity.this, StartAtyForResTest2Activity.class);
         intent.putExtra(TAG_LONGITUDE, 112.0605468750 + "");
         startActivity(intent);
         finish();
         break;
         */
        String s = getIntent().getStringExtra(TAG_LONGITUDE);
        Log.e(TAG, s + "");


//        double longitude = Double.parseDouble(getIntent().getStringExtra(StartAtyForResTest1Activity.TAG_LONGITUDE));
//        double latitude = Double.parseDouble(getIntent().getStringExtra(StartAtyForResTest1Activity.TAG_LATITUDE));
//
//        Log.e(TAG, longitude + "");
//        Log.e(TAG, latitude + "");
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                Intent intent = new Intent();
                intent.putExtra("intent_post", "哈哈哈");
                setResult(2, intent);
                finish();
                break;
            case R.id.btn2:
                break;
            case R.id.btn3:
                break;
            case R.id.btn4:
                break;
            case R.id.btn5:
                break;
        }

    }


}

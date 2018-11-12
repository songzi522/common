package com.gs.common.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetUriParameterActivity extends AppCompatActivity {

    @BindView(R.id.tvParam1)
    TextView tvParam1;
    @BindView(R.id.tvParam2)
    TextView tvParam2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_uri_parameter);
        ButterKnife.bind(this);

        String url = "http://phdzyapi.phmd247.com/dzy/v1/wechat/index?type=1&userid=264";

        String param1 = Uri.parse(url).getQueryParameter("userid");
        tvParam1.setText(param1);

        String param2 = Uri.parse(url).getQueryParameter("type");
        tvParam2.setText(param2);

    }


}

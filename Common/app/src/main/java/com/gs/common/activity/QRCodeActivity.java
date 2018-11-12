package com.gs.common.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.gs.common.R;
import com.gs.common.utils.RCodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QRCodeActivity extends AppCompatActivity {

    @BindView(R.id.ivQrcode)
    ImageView ivQrcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);
        ButterKnife.bind(this);

        String url = "http://service.phmd247.com/h5/register.html?jg=" + "JG100";
        Bitmap mBitmap = RCodeUtils.createQRCodeBitmap(url, 480, 480);
        ivQrcode.setImageBitmap(mBitmap);

    }

}

package com.gs.common.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gs.common.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Test4Activity extends AppCompatActivity {

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.tvAge)
    TextView tvAge;
    @BindView(R.id.etAge)
    EditText etAge;
    @BindView(R.id.btn1)
    Button btn1;

    static final String STATE_NAME = "state_name";
    static final String STATE_AGE = "state_age";

    String mCurrentName, mCurrentAge;

    private static final String TAG = "Test4Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test4);
        ButterKnife.bind(this);

        Log.e(TAG, "onCreate()");

        if (savedInstanceState != null) {
            // Restore value of members from saved state
            etName.setText(savedInstanceState.getString(STATE_NAME));
            etAge.setText(savedInstanceState.getString(STATE_AGE));
        } else {
            // Probably initialize members with default values for a new instance
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        Log.e(TAG, "onSaveInstanceState()");

        outState.putString(STATE_NAME, mCurrentName);
        outState.putString(STATE_AGE, mCurrentAge);

        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.e(TAG, "onRestoreInstanceState()");

        etName.setText(savedInstanceState.getString(STATE_NAME));
        etAge.setText(savedInstanceState.getString(STATE_AGE));

    }

    @OnClick(R.id.btn1)
    public void onViewClicked() {
        mCurrentName = etName.getText().toString();
        mCurrentAge = etAge.getText().toString();
        startActivity(new Intent(Test4Activity.this, TestActivity.class));
    }


}

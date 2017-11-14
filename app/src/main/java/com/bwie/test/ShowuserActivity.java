package com.bwie.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.SPUtils;

public class ShowuserActivity extends AppCompatActivity {

    @BindView(R.id.textmobile)
    TextView textmobile;
    @BindView(R.id.btnlog)
    Button btnlog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showuser);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.btnlog)
    public void onViewClicked() {

        SPUtils.clear(ShowuserActivity.this);
        finish();
    }
}

package com.bwie.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

import MineMVP.MineApi.Mineapi;
import MineMVP.MineBean.ZhuceEventBusBean;
import MineMVP.presenter.ZhucePresenter;
import MineMVP.view.ZhuceIview;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZhuceActivity extends AppCompatActivity implements ZhuceIview{

    @BindView(R.id.imgback)
    ImageView imgback;
    @BindView(R.id.zhucemobil)
    EditText zhucemobil;
    @BindView(R.id.zhucepassword)
    EditText zhucepassword;
    @BindView(R.id.btnzhuce)
    Button btnzhuce;
    ZhucePresenter zhucePresenter;
    String zhucemonile;
    String zhupass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhuce);
        ButterKnife.bind(this);
      zhucePresenter = new ZhucePresenter(this);
    }

    @OnClick({R.id.imgback, R.id.btnzhuce})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imgback:
                finish();
                break;
            case R.id.btnzhuce:
                 zhucemonile = zhucemobil.getText().toString();
                 zhupass = zhucepassword.getText().toString();
                HashMap<String, String> map = new HashMap<>();
                map.put("mobile",zhucemonile);
                map.put("password",zhupass);
                zhucePresenter.Postzhuce(Mineapi.LOGIN_URL,map);

                break;
        }
    }

    @Override
    public void PostZhuce(String code) {
        Log.d("main","sssssssssssssssssssss"+code);
        if(code.equals("0")){
//            代表注册已经请求成功,需要我们发送注册成功的手机号和密码
            EventBus.getDefault().post(new ZhuceEventBusBean(zhucemonile,zhupass));
            finish();
        }else{

            Toast.makeText(ZhuceActivity.this,"注册失败了亲",Toast.LENGTH_SHORT).show();
        }
    }
}

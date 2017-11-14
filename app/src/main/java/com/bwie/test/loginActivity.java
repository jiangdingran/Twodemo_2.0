package com.bwie.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

import MineMVP.MineApi.Mineapi;
import MineMVP.MineBean.ZhuceEventBusBean;
import MineMVP.presenter.MinePresenter;
import MineMVP.view.Loginview;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.SPUtils;

public class loginActivity extends AppCompatActivity implements Loginview{

    @BindView(R.id.editmobile)
    EditText editmobile;
    @BindView(R.id.editpassword)
    EditText editpassword;
    @BindView(R.id.tvzhuce)
    TextView tvzhuce;
    @BindView(R.id.btnlogin)
    Button btnlogin;
    MinePresenter minePresenter;
    String mobile;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        ButterKnife.bind(this);
      minePresenter = new MinePresenter(this);


    }

    @OnClick({R.id.tvzhuce, R.id.btnlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvzhuce:
//                进行注册判断，我们需要在当我们注册成功之后，把注册的账号和密码那会我们的登陆页面
                if (!EventBus.getDefault().isRegistered(this))
                {
                    EventBus.getDefault().register(this);
                }

                Intent intent = new Intent(loginActivity.this, ZhuceActivity.class);
                startActivity(intent);
                break;
            case R.id.btnlogin:
                 mobile = editmobile.getText().toString();
               password = editpassword.getText().toString();

                HashMap<String, String> map = new HashMap<>();
                map.put("mobile",mobile);
                map.put("password",password);
                minePresenter.PostLogin(Mineapi.LOGIN_URL,map);

                break;
        }
    }

    @Override
    public void PostLogin(String code) {
        Log.d("Main","==========================="+code);
        if(code.equals("0")){
//            如果我们请求会来的数据code为0代表请求成功，保存数据为true
            SPUtils.put(loginActivity.this,"jiang",true);
//            当我们登陆成功之后我们需要发送粘性时间给我们的fragmentMyUSER图片地下的文字
            EventBus.getDefault().postSticky(new ZhuceEventBusBean(mobile,password));
            finish();
        }else{
            Toast.makeText(loginActivity.this,"失败了",Toast.LENGTH_SHORT).show();
        }
    }
//    拿到我们的注册成功之后返回的数据,赋值给我们的登陆界面的输入框
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(ZhuceEventBusBean messageEvent){
        editmobile.setText(messageEvent.getMobile());
        editpassword.setText(messageEvent.getPassword());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}

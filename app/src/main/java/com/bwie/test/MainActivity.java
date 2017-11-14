package com.bwie.test;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.RadioButton;

import com.facebook.drawee.backends.pipeline.Fresco;

import Fragment.FragmentClass;
import Fragment.FragmentGoshop;
import HomeMVP.view.FragmentHome;
import Fragment.FragmentMyUser;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import utils.SPUtils;

public class MainActivity extends FragmentActivity {

    @BindView(R.id.btn1)
    RadioButton btn1;
    @BindView(R.id.btn2)
    RadioButton btn2;
    @BindView(R.id.btn3)
    RadioButton btn3;
    @BindView(R.id.btn4)
    RadioButton btn4;
    private FragmentHome fh;
    private FragmentClass fc;
    private FragmentGoshop fg;
    private FragmentMyUser fmu;
    boolean sharp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        btn1.setChecked(true);
        setDefaultFragment();

    }

    //创建我们的sharp让他在获取焦点之前设置为false
    @Override
    protected void onResume() {
        super.onResume();
         sharp = (boolean) SPUtils.get(MainActivity.this, "jiang", false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        btn1.setChecked(true);
        setDefaultFragment();

    }

    private void setDefaultFragment(){

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        fh = new FragmentHome();
        transaction.replace(R.id.fragmentlayout,fh);
        transaction.commit();

    }


    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();

        switch (view.getId()) {
            case R.id.btn1:
                if(fh==null){
                  fh= new FragmentHome();
                }
                transaction.replace(R.id.fragmentlayout,fh);
                break;
            case R.id.btn2:
                if(fc==null){
                    fc = new FragmentClass();
                }
//                对我们储存的登陆状态进行判断为true就执行我们的fragment,false就跳转到登陆页面
                if(sharp==true){
                    transaction.replace(R.id.fragmentlayout,fc);
                }else{
                    Intent intent = new Intent(MainActivity.this, loginActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.btn3:
                if(fg==null){
                    fg = new FragmentGoshop();
                }
                transaction.replace(R.id.fragmentlayout,fg);


                break;
            case R.id.btn4:
                if(fmu==null){
                    fmu = new FragmentMyUser();
                }
                // 对我们储存的登陆状态进行判断为true就执行我们的fragment,false就跳转到登陆页面
                if(sharp==true){
                    transaction.replace(R.id.fragmentlayout,fmu);
                }else{
                    Intent intent = new Intent(MainActivity.this, loginActivity.class);
                    startActivity(intent);
                }


                break;
        }
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SPUtils.clear(MainActivity.this);
    }
}

package Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.test.R;
import com.bwie.test.ShowuserActivity;
import com.bwie.test.loginActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import MineMVP.MineBean.ZhuceEventBusBean;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import utils.SPUtils;

/**
 * Created by 蒋丁然 on 2017/11/8.
 */

public class FragmentMyUser extends Fragment {

    @BindView(R.id.imgLogin)
    ImageView imgLogin;
    Unbinder unbinder;
    @BindView(R.id.textname)
    TextView textname;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragmentuser_layout, container, false);
        unbinder = ButterKnife.bind(this, view);
//        进行注册
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.imgLogin)
    public void onViewClicked() {
//        对我们的头像的点击事件进行判断,如果成功就跳转到详情界面进行可以退出的处理
//        没有登陆,就进行登陆
        boolean login_code = (boolean) SPUtils.get(getActivity(), "jiang",false);
        if(login_code==true){

            Intent intent = new Intent(getActivity(), ShowuserActivity.class);
            startActivity(intent);

        }else{
            Intent intent = new Intent(getActivity(), loginActivity.class);
            startActivity(intent);
        }



    }
//    拿到登陆界面的登陆成功之后的手机号
    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void ononMoonStickyEvent(ZhuceEventBusBean messageEvent){
        textname.setText(messageEvent.getMobile());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

}


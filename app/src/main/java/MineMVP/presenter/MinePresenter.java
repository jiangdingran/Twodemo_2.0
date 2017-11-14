package MineMVP.presenter;

import java.util.Map;

import MineMVP.model.Loginmodel;
import MineMVP.view.Loginview;

/**
 * Created by 蒋丁然 on 2017/11/13.
 */

public class MinePresenter implements Loginmodel.OnfinishLoginLiten{

    private final Loginview iview;
    private final Loginmodel loginmodel;

    public MinePresenter(Loginview iview) {
        this.iview = iview;
        this.loginmodel = new Loginmodel();
        loginmodel.setOnfinishLoginLiten(this);
    }
    public void PostLogin(String url, Map<String,String> map){

        loginmodel.getUrl(url,map);

    }


    @Override
    public void onfinishLogin(String code) {
        iview.PostLogin(code);
    }
}

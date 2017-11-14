package HomeMVP.presenter;

import HomeMVP.bean.Mysupclass;
import HomeMVP.model.Usermodel;
import HomeMVP.view.Iview;

/**
 * Created by 蒋丁然 on 2017/11/9.
 */

public class Fragmentonepresenter implements Usermodel.OnfinishLiten{

    private final Iview iview;
    private final Usermodel usermodel;

    public Fragmentonepresenter(Iview iview) {
        this.iview = iview;
        this.usermodel = new Usermodel();
        usermodel.setOnfinishLiten(this);
    }
   public void getdata(){

       usermodel.getUrl();
   }


    @Override
    public void onfinsh(Mysupclass.DataBean data) {
        iview.getdata(data);
    }
}

package MineMVP.presenter;

import java.util.Map;

import MineMVP.model.Zhucemodel;
import MineMVP.view.ZhuceIview;

/**
 * Created by 蒋丁然 on 2017/11/13.
 */

public class ZhucePresenter implements Zhucemodel.OnfinishZhuceLite{

    private final ZhuceIview zhuceIview;
    private final Zhucemodel zhucemodel;

    public ZhucePresenter(ZhuceIview zhuceIview) {
        this.zhuceIview = zhuceIview;
        this.zhucemodel = new Zhucemodel();
        zhucemodel.setOnfinishZhuceLite(this);
    }
    public void Postzhuce(String url, Map<String,String> map){

        zhucemodel.Postzhuce(url,map);
    }

    @Override
    public void PostZhue(String code) {
        zhuceIview.PostZhuce(code);
    }
}

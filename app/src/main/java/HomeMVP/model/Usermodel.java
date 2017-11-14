package HomeMVP.model;

import android.util.Log;

import HomeMVP.bean.Mysupclass;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import utils.ApiServer;
import utils.RetroFactory;

/**
 * Created by 蒋丁然 on 2017/11/8.
 */

public class Usermodel implements Imodel{

    private Mysupclass.DataBean data;
    private OnfinishLiten onfinishLiten;
    public interface OnfinishLiten{
        void onfinsh(Mysupclass.DataBean data);

    }

    public void setOnfinishLiten(OnfinishLiten onfinishLiten) {
        this.onfinishLiten = onfinishLiten;
    }

    @Override
    public void getUrl() {

        ApiServer instance = RetroFactory.getInstance();

        Observable<Mysupclass> homes = instance.getHomes();
        homes.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Mysupclass>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("sss","失败");
                        e.printStackTrace();
                    }

                    @Override
                    public void onNext(Mysupclass mysupclass) {
                      data = mysupclass.getData();
                        Log.d("sss","sss"+data.getAd1().get(0).getTitle());
                        onfinishLiten.onfinsh(data);
                    }
                });


    }

}

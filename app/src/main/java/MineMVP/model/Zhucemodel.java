package MineMVP.model;

import java.util.Map;

import MineMVP.MineApiservice.ApiService;
import MineMVP.MineBean.zhuceBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 蒋丁然 on 2017/11/13.
 */

public class Zhucemodel implements ZhuceImodel{

    private OnfinishZhuceLite onfinishZhuceLite;
    public interface OnfinishZhuceLite{
        void PostZhue(String code);

    }

    public void setOnfinishZhuceLite(OnfinishZhuceLite onfinishZhuceLite) {
        this.onfinishZhuceLite = onfinishZhuceLite;
    }

    @Override
    public void Postzhuce(String url, Map<String, String> map) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<zhuceBean> zhuceBeanObservable = apiService.PostZhuce("user/reg", map);
        zhuceBeanObservable.subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Observer<zhuceBean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {

                                }

                                @Override
                                public void onNext(zhuceBean zhuceBean) {
                                    String code = zhuceBean.getCode();
                                   onfinishZhuceLite.PostZhue(code);
                                }
                            });


    }
}

package MineMVP.model;

import android.util.Log;

import java.util.Map;

import MineMVP.MineApiservice.ApiService;
import MineMVP.MineBean.LoginBean;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by 蒋丁然 on 2017/11/8.
 */

public class Loginmodel implements Imodel{

    private OnfinishLoginLiten onfinishLoginLiten;

    public interface OnfinishLoginLiten{

        void onfinishLogin(String code);
    }

    public void setOnfinishLoginLiten(OnfinishLoginLiten onfinishLoginLiten) {
        this.onfinishLoginLiten = onfinishLoginLiten;
    }

    @Override
    public void getUrl(String url, Map<String, String> map) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();
        ApiService apiService = retrofit.create(ApiService.class);
        Observable<LoginBean> observable = apiService.PostLogin("user/login", map);
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<LoginBean>() {
                        @Override
                        public void onCompleted() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }

                        @Override
                        public void onNext(LoginBean loginBean) {
                            String code = loginBean.getCode();
                            Log.d("main","======"+code);
                            onfinishLoginLiten.onfinishLogin(code);
                        }
                    });

    }
}

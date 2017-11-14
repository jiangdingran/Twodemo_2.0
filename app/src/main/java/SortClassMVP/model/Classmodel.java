package SortClassMVP.model;

import java.util.List;

import SortClassMVP.ApiService.Apiclassservice;
import SortClassMVP.ClassBean.DataleftBean;
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

public class Classmodel implements ClassImodel{

    private  List<DataleftBean.DatasBean.ClassListBean> class_list;
    private OnclassFinishlister Onclassfish;
    public interface OnclassFinishlister{
        void onclassfinsh(List<DataleftBean.DatasBean.ClassListBean> class_list);
    }

    public void setOnclassfish(OnclassFinishlister onclassfish) {
        Onclassfish = onclassfish;
    }

    @Override
    public void getUrl(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create()).build();

        Apiclassservice apiService = retrofit.create(Apiclassservice.class);
        Observable<DataleftBean> observable = apiService.getData();
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DataleftBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(DataleftBean dataleftBean) {
                       class_list = dataleftBean.getDatas().getClass_list();
                        Onclassfish.onclassfinsh(class_list);
                    }
                });

    }
}

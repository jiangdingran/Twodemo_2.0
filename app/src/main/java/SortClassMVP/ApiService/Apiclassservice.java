package SortClassMVP.ApiService;

import SortClassMVP.ClassBean.DataleftBean;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by 蒋丁然 on 2017/11/14.
 */

public interface Apiclassservice {

    @GET("mobile/index.php?act=goods_class")
    Observable<DataleftBean> getData();
}

package utils;

import HomeMVP.bean.Mysupclass;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by fan on 2017/11/8.
 */

public interface ApiServer {

//    @GET("v1/restserver/ting?method=baidu.ting.billboard.billList&type=1&size=10&offset=0")
//    Observable<> getHome();
//
    @GET("umIPmfS6c83237d9c70c7c9510c9b0f97171a308d13b611?uri=homepage")
    Observable<Mysupclass> getHomes();




}

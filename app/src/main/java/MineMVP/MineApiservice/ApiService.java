package MineMVP.MineApiservice;

import java.util.Map;

import MineMVP.MineBean.LoginBean;
import MineMVP.MineBean.zhuceBean;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by 蒋丁然 on 2017/11/13.
 */

public interface ApiService {

   @POST
    Observable<LoginBean> PostLogin(@Url String url, @QueryMap Map<String,String> map);

    @POST
    Observable<zhuceBean> PostZhuce(@Url String url,@QueryMap Map<String,String> map);
}

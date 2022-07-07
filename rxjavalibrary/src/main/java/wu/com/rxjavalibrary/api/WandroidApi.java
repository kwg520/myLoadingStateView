package wu.com.rxjavalibrary.api;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author:kwg
 * @date:2022/7/6,16:28
 */
public interface WandroidApi {

    @GET("project/tree/json")
    Observable<ProjectBean> getProject();

    @GET("project/list/{pageIndex}/json")
    Observable<ProjectItem> getItem(@Path("pageIndex") int pageIndex , @Query("cid") int cid);


}

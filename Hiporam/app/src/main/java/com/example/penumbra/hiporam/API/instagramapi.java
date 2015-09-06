package com.example.penumbra.hiporam.API;


import com.example.penumbra.hiporam.model.InstagramItem;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by penumbra on 04.09.2015.
 */
public interface instagramapi
{
    @GET("/tags/{tag-name}/media/recent?client_id=21a877c46423488e8596db51bd9ec16f")
    void getTag(@Path("tag-name") String tag_names, Callback<InstagramItem> callback);

    @GET("/tags/{tag-name}/media/recent?client_id=21a877c46423488e8596db51bd9ec16f")
    void loadMore(@Path("tag-name") String tag_names,@Query("max_tag_id") String max_tag_id,  Callback<InstagramItem> callback);

}

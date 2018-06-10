package com.puneet.tunein_navigation.network;

import com.puneet.tunein_navigation.model.childnavmodel.ChildCategories;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ChildrenCategoriesApi {

   /* @GET("Browse.ashx?")
    Observable<ChildCategories> loadChildrenCategories(@Url String id);*/

    @GET
    Observable<ResponseBody> loadSubCategories(@Url String url, @QueryMap Map<String, String> queryMap);

}

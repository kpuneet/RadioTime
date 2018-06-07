package com.puneet.tunein_navigation.network;

import com.puneet.tunein_navigation.model.topnavmodel.Categories;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface TopCategoriesApi {

    @GET("/")
    Observable<Categories> loadCategories(@QueryMap Map<String, String> queryMap);

}

package com.puneet.tunein_navigation.network;

import com.puneet.tunein_navigation.model.topnavmodel.Categories;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface TopCategoriesApi {
    /**
     * TODO add docs
     * @return the observable API for loading categories
     */

    @GET("Browse.ashx?")
    Observable<Categories> loadCategories(@QueryMap Map<String, String> queryMap);

}

package com.puneet.tunein_navigation.network;

import com.puneet.tunein_navigation.model.topnavmodel.Categories;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface TopCategoriesApi {
    /**
     * @return the observable API for loading categories
     */

    @GET("Browse.ashx?")
    Observable<Response<Categories>> loadCategories();

}

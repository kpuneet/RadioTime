package com.puneet.tunein_navigation.network;

import com.puneet.tunein_navigation.model.childnavmodel.ChildCategories;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ChildrenCategoriesApi {
    /**
     * @return the observable API for loading Sub Categories
     */

    @GET
    Observable<Response<ChildCategories>> loadSubCategories(@Url String url);

}

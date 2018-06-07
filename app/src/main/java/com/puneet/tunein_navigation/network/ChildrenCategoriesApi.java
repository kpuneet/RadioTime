package com.puneet.tunein_navigation.network;

import com.puneet.tunein_navigation.model.childnavmodel.ChildCategories;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface ChildrenCategoriesApi {

    @GET("Browse.ashx?")
    Observable<ChildCategories> loadChildrenCategories(@Url String id);

}

package com.puneet.tunein_navigation.apiresponse;

import android.content.Context;
import android.databinding.ObservableInt;
import android.view.View;

import com.google.gson.Gson;
import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.model.childnavmodel.ChildCategories;
import com.puneet.tunein_navigation.model.topnavmodel.Categories;
import com.puneet.tunein_navigation.network.RetrofitManager;
import com.puneet.tunein_navigation.network.ChildrenCategoriesApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChildNavViewModel extends Observable {


    public ObservableInt progressBar;
    public ObservableInt userRecycler;
    public ObservableInt userLabel;
    private Context context;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Categories categories;
    private ChildCategories childCategories;

    public ChildNavViewModel(Context context, String childKey) {
        this.context = context;
        progressBar = new ObservableInt(View.GONE);
        userRecycler = new ObservableInt(View.GONE);
        userLabel = new ObservableInt(View.VISIBLE);
        getChildNavApiResponse(childKey);
    }

    private void getChildNavApiResponse(String id) {

        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("render", "json");
        ChildrenCategoriesApi childrenCategoriesApi = RetrofitManager.sInstance.getClient(context.getString(R.string.TuneIn_Endpoint)).create(ChildrenCategoriesApi.class);
        Disposable disposable = childrenCategoriesApi.loadSubCategories(id, queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    Gson gson = new Gson();
                    try {
                        String responseString = responseBody.string();
                        JSONObject json = new JSONObject(responseString);
                        JSONArray jsonArray = json.getJSONArray("body");

                        JSONObject jsonobject = jsonArray.getJSONObject(0);
                        Iterator<String> iterator = jsonobject.keys();
                        boolean foundChildrenKey = false;
                        while (iterator.hasNext()) {
                            String key = iterator.next();
                            foundChildrenKey = key.equals("children");
                        }
                        if (foundChildrenKey) {
                            updateChildCategories(gson.fromJson(responseString, ChildCategories.class));
                        } else {
                            updateCategories(gson.fromJson(responseString, Categories.class));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void updateChildCategories(ChildCategories childCategories) {
        this.childCategories = childCategories;
        setChanged();
        notifyObservers();
    }

    private void updateCategories(Categories categories) {
        this.categories = categories;
        setChanged();
        notifyObservers();
    }

    public Categories getCategories() {
        return this.categories;
    }

    public ChildCategories getChildCategories() {
        return this.childCategories;
    }

    public void reset() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable = null;
    }
}

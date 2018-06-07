package com.puneet.tunein_navigation.apiresponse;

import android.content.Context;
import android.databinding.ObservableInt;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.model.childnavmodel.Body;
import com.puneet.tunein_navigation.model.childnavmodel.ChildCategories;
import com.puneet.tunein_navigation.model.topnavmodel.Categories;
import com.puneet.tunein_navigation.network.RetrofitManager;
import com.puneet.tunein_navigation.network.ChildrenCategoriesApi;
import com.puneet.tunein_navigation.network.TopCategoriesApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Observable;
import java.util.function.Consumer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChildNavViewModel extends Observable {


    public ObservableInt progressBar;
    public ObservableInt userRecycler;
    public ObservableInt userLabel;
    private Context context;
    private static final String BODY = "body";
    private static final String CHILDREN = "children";

    private boolean isChildren = false;

    private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private Categories categories;
    private ChildCategories childCategories;

    public ChildNavViewModel(Context context) {
        this.context = context;
    }


    public void getChildNavApiResponse(boolean isTuneUrl, String id) {
        HashMap<String,String> queryMap = new HashMap<>();
        queryMap.put("render","json");
        if (isTuneUrl) {
            ChildrenCategoriesApi childrenCategoriesApi = RetrofitManager.sInstance.getClient(context.getString(R.string.TuneIn_Endpoint)).create(ChildrenCategoriesApi.class);
            Disposable disposable = childrenCategoriesApi.loadChildrenCategories(id)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(childCategories -> {
                        updateChildCategories(childCategories);
                        progressBar.set(View.GONE);
                        userLabel.set(View.GONE);
                        userRecycler.set(View.VISIBLE);
                    }, throwable -> {
                        Toast.makeText(context, R.string.Error_Message, Toast.LENGTH_LONG).show();
                        progressBar.set(View.GONE);
                        userLabel.set(View.VISIBLE);
                        userRecycler.set(View.GONE);
                    });
            compositeDisposable.add(disposable);
        } else {
            TopCategoriesApi topCategoriesApi = RetrofitManager.sInstance.getClient(context.getString(R.string.TuneIn_Endpoint)).create(TopCategoriesApi.class);
            Disposable disposable = topCategoriesApi.loadCategories(queryMap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(categories -> {
                        updateCategories(categories);
                        progressBar.set(View.GONE);
                        userLabel.set(View.GONE);
                        userRecycler.set(View.VISIBLE);
                    }, throwable -> {
                        Toast.makeText(context, R.string.Error_Message, Toast.LENGTH_LONG).show();
                        progressBar.set(View.GONE);
                        userLabel.set(View.VISIBLE);
                        userRecycler.set(View.GONE);
                    });
            compositeDisposable.add(disposable);

        }

    }

    private void updateChildCategories(ChildCategories childCategories) {
        this.childCategories.setBodyList(childCategories.getBodyList());
        this.childCategories.setHead(childCategories.getHead());
        setChanged();
        notifyObservers();
    }

    private void updateCategories(Categories categories) {
        this.categories.setBodyList(categories.getBodyList());
        this.categories.setHead(categories.getHead());
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

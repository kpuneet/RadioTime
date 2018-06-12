package com.puneet.tunein_navigation.apiresponse;

import android.content.Context;
import android.databinding.ObservableInt;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Toast;

import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.model.topnavmodel.Categories;
import com.puneet.tunein_navigation.network.RetrofitManager;
import com.puneet.tunein_navigation.network.TopCategoriesApi;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TopNavViewModel extends Observable {

    private final Context context;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Categories categories;

    public TopNavViewModel(@NonNull Context context) {
        this.context = context;
        getTopLevelApiResponse();
    }

    private void getTopLevelApiResponse() {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("render", "json");
        TopCategoriesApi topCategoriesApi = RetrofitManager.sInstance.getClient(context.getString(R.string.TuneIn_Endpoint)).create(TopCategoriesApi.class);
        Disposable disposable = topCategoriesApi.loadCategories(queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(categories -> {
                    updateCategories(categories);
                }, throwable -> {
                    Toast.makeText(context, R.string.Error_Message, Toast.LENGTH_LONG).show();
                });
        compositeDisposable.add(disposable);
    }

    private void updateCategories(Categories categories) {
        this.categories = categories;
        setChanged();
        notifyObservers();
    }

    public Categories getCategories() {
        return this.categories;
    }

    public void reset() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable = null;
    }

}

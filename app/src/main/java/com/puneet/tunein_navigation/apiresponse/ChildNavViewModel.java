package com.puneet.tunein_navigation.apiresponse;

import android.content.Context;
import android.widget.Toast;

import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.model.CombinedList;
import com.puneet.tunein_navigation.model.childnavmodel.Body;
import com.puneet.tunein_navigation.model.childnavmodel.ChildCategories;
import com.puneet.tunein_navigation.model.childnavmodel.Children;
import com.puneet.tunein_navigation.network.RetrofitManager;
import com.puneet.tunein_navigation.network.ChildrenCategoriesApi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChildNavViewModel extends Observable {

    private Context context;
    private final List<CombinedList> combinedLists = new ArrayList<>();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ContentListType contentListType;

    public enum ContentListType {
        MUSIC, GENERE
    }

    public ChildNavViewModel(Context context, String childKey) {
        this.context = context;
        getChildNavApiResponse(childKey);
    }

    private void getChildNavApiResponse(String id) {
        Map<String, String> queryMap = new HashMap<>();
        queryMap.put("render", "json");
        ChildrenCategoriesApi childrenCategoriesApi = RetrofitManager.sInstance.getClient(context.getString(R.string.TuneIn_Endpoint)).create(ChildrenCategoriesApi.class);
        Disposable disposable = childrenCategoriesApi.loadSubCategories(id, queryMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(childCategories -> {
                    updateChildCategories(childCategories);
                }, throwable -> {
                    Toast.makeText(context, R.string.Error_Message, Toast.LENGTH_LONG).show();
                });
        compositeDisposable.add(disposable);
    }

    private void updateChildCategories(ChildCategories childCategories) {
        List<Body> bodyList = childCategories.getBody();
        for (Body body : bodyList) {
            combinedLists.add(new CombinedList(body.getText(),body.getURL(), ContentListType.GENERE.ordinal()));

            List<Children> children = body.getChildren();

            for (Children child : children) {
                combinedLists.add(new CombinedList(child.getText(), child.getSubtext(), child.getImage(),ContentListType.MUSIC.ordinal()));
            }
        }
        contentListType = childCategories.getBody().get(0).hasChildren() ? ContentListType.MUSIC : ContentListType.GENERE;
        setChanged();
        notifyObservers();
    }

    public List<CombinedList> getCombinedLists() {
        return this.combinedLists;
    }

    public ContentListType getContentListType() {
        return contentListType;
    }

    public void reset() {
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.dispose();
        }
        compositeDisposable = null;
    }
}

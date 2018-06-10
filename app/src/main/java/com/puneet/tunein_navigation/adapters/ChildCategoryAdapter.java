package com.puneet.tunein_navigation.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.databinding.SubCategoryLayoutBinding;
import com.puneet.tunein_navigation.model.childnavmodel.ChildCategories;

import io.reactivex.functions.Consumer;

public class ChildCategoryAdapter extends RecyclerView.Adapter<ChildCategoryAdapter.ChildCategoryViewHolder> {

    private final int SUB_HEADER = 1;
    private final int MUSIC_HEADER = 2;
    private OnSelectCategory onSelectCategory;
    private ChildCategories childCategories;

    public ChildCategoryAdapter(OnSelectCategory onSelectCategory, ChildCategories childCategories) {
        this.onSelectCategory = onSelectCategory;
        this.childCategories = childCategories;

        if(childCategories.getBody().)
    }

    @NonNull
    @Override
    public ChildCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SubCategoryLayoutBinding subCategoryLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.sub_category_layout, parent, false);
        return new ChildCategoryViewHolder(onSelectCategory, subCategoryLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildCategoryViewHolder holder, int position) {
        holder.subCategoryLayoutBinding.setChildBody(childCategories.getBody().get(position));
        holder.subCategoryLayoutBinding.subLevelTitle.setText(childCategories.getBody().get(position).getText());
        holder.bind(childCategories);
    }

    @Override
    public int getItemCount() {
        if (childCategories != null || !childCategories.getBody().isEmpty()) {
            return childCategories.getBody().size();
        }
        return 0;
    }

    class ChildCategoryViewHolder extends RecyclerView.ViewHolder {

        private final SubCategoryLayoutBinding subCategoryLayoutBinding;
        private OnSelectCategory onSelectCategory;

        ChildCategoryViewHolder(OnSelectCategory onSelectCategory, SubCategoryLayoutBinding subCategoryLayoutBinding) {
            super(subCategoryLayoutBinding.getRoot());
            this.subCategoryLayoutBinding = subCategoryLayoutBinding;
            this.onSelectCategory = onSelectCategory;
        }

        private void bind(final ChildCategories childCategories) {
            RxView.clicks(subCategoryLayoutBinding.subLevelTitle)
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            onSelectCategory.onSelectCategory(childCategories.getBody().get(getAdapterPosition()).getKey());
                        }
                    });
        }
    }
}

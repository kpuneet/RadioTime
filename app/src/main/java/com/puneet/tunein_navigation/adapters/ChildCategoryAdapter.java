package com.puneet.tunein_navigation.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.databinding.SubCategoryLayoutBinding;
import com.puneet.tunein_navigation.model.childnavmodel.ChildCategories;

public class ChildCategoryAdapter extends RecyclerView.Adapter<ChildCategoryAdapter.ChildCategoryViewHolder> {

    onSelectCategory onSelectCategory;
    ChildCategories childCategories;

    public ChildCategoryAdapter(onSelectCategory onSelectCategory, ChildCategories childCategories) {
        this.onSelectCategory = onSelectCategory;
        this.childCategories = childCategories;
    }

    @NonNull
    @Override
    public ChildCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        SubCategoryLayoutBinding subCategoryLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.sub_category_layout, parent, false);
        return new ChildCategoryViewHolder(subCategoryLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ChildCategoryViewHolder holder, int position) {
        holder.subCategoryLayoutBinding.setChildCategories(childCategories);
        holder.subCategoryLayoutBinding.subLevelTitle.setText(childCategories.getBodyList().get(position).getText());
    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class ChildCategoryViewHolder extends RecyclerView.ViewHolder {

        private final SubCategoryLayoutBinding subCategoryLayoutBinding;

        public ChildCategoryViewHolder(SubCategoryLayoutBinding subCategoryLayoutBinding) {
            super(subCategoryLayoutBinding.getRoot());
            this.subCategoryLayoutBinding = subCategoryLayoutBinding;
        }
    }
}

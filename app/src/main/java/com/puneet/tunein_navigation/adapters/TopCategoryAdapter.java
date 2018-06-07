package com.puneet.tunein_navigation.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.databinding.TopCategoryLayoutBinding;
import com.puneet.tunein_navigation.model.topnavmodel.Categories;

public class TopCategoryAdapter extends RecyclerView.Adapter<TopCategoryAdapter.TopCategoryViewHolder> {

    private Categories categories;
    onSelectCategory onSelectCategory;

    public TopCategoryAdapter(onSelectCategory onSelectCategory, Categories categories) {
        this.onSelectCategory = onSelectCategory;
        this.categories = categories;
    }

    @NonNull
    @Override
    public TopCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TopCategoryLayoutBinding topCategoryLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.top_category_layout, parent, false);
        return new TopCategoryViewHolder(topCategoryLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TopCategoryViewHolder holder, int position) {
        holder.topCategoryLayoutBinding.setCategories(categories);
        holder.topCategoryLayoutBinding.topLevelTitle.setText(categories.getBodyList().get(position).getText());
    }

    @Override
    public int getItemCount() {
        if (categories != null || !categories.getBodyList().isEmpty()) {
            return categories.getBodyList().size();
        }
        return 0;
    }

    public class TopCategoryViewHolder extends RecyclerView.ViewHolder {

        private final TopCategoryLayoutBinding topCategoryLayoutBinding;

        public TopCategoryViewHolder(TopCategoryLayoutBinding topCategoryLayoutBinding) {
            super(topCategoryLayoutBinding.getRoot());
            this.topCategoryLayoutBinding = topCategoryLayoutBinding;
        }

    }
}

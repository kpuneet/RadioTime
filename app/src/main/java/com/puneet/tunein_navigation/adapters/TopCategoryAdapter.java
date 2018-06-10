package com.puneet.tunein_navigation.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jakewharton.rxbinding2.view.RxView;
import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.databinding.TopCategoryLayoutBinding;
import com.puneet.tunein_navigation.model.topnavmodel.Categories;
import com.puneet.tunein_navigation.ui.TopCategoryFragment;

import io.reactivex.functions.Consumer;

public class TopCategoryAdapter extends RecyclerView.Adapter<TopCategoryAdapter.TopCategoryViewHolder> {

    Categories categories;
    OnSelectCategory onSelectCategory;

    public TopCategoryAdapter(OnSelectCategory onSelectCategory, Categories categories) {
        this.onSelectCategory = onSelectCategory;
        this.categories = categories;
    }

    @NonNull
    @Override
    public TopCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        TopCategoryLayoutBinding topCategoryLayoutBinding = DataBindingUtil.inflate(layoutInflater, R.layout.top_category_layout, parent, false);
        return new TopCategoryViewHolder(onSelectCategory, topCategoryLayoutBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull final TopCategoryViewHolder holder, final int position) {
        Log.d(TopCategoryAdapter.class.getSimpleName(), "Coming to bind view holder");
        holder.topCategoryLayoutBinding.setBody(categories.getBody().get(position));
        holder.topCategoryLayoutBinding.topLevelTitle.setText(categories.getBody().get(position).getText());
        holder.bind(categories);
    }

    @Override
    public int getItemCount() {
        if (categories != null || !categories.getBody().isEmpty()) {
            return categories.getBody().size();
        }
        return 0;
    }

    class TopCategoryViewHolder extends RecyclerView.ViewHolder {

        private final TopCategoryLayoutBinding topCategoryLayoutBinding;
        private OnSelectCategory onSelectCategory;

        TopCategoryViewHolder(OnSelectCategory onSelectCategory, TopCategoryLayoutBinding topCategoryLayoutBinding) {
            super(topCategoryLayoutBinding.getRoot());
            this.topCategoryLayoutBinding = topCategoryLayoutBinding;
            this.onSelectCategory = onSelectCategory;
        }

        private void bind(final Categories categories) {
            RxView.clicks(topCategoryLayoutBinding.topLevelTitle)
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(Object o) throws Exception {
                            onSelectCategory.onSelectCategory(categories.getBody().get(getAdapterPosition()).getURL());
                        }
                    });
        }
    }
}

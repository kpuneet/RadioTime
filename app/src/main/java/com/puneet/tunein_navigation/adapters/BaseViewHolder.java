package com.puneet.tunein_navigation.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.puneet.tunein_navigation.apiresponse.ChildNavViewModel;

/**
 * Description: Provides a base implementation that holds onto a binding class.
 */
public class BaseViewHolder<Binding extends ViewDataBinding> extends RecyclerView.ViewHolder {

    public Binding binding;
    public ChildNavViewModel.ContentListType contentListType;

    public BaseViewHolder(View itemView, ChildNavViewModel.ContentListType type) {
        super(itemView);
        this.contentListType = type;
    }

    public BaseViewHolder(View itemView, ChildNavViewModel.ContentListType type, boolean isBindingLayout) {
        super(itemView);
        if (isBindingLayout) {
            binding = DataBindingUtil.bind(itemView);
        }
        this.contentListType = type;
    }

    public void executePendingBindings() {
        if (binding != null) {
            binding.executePendingBindings();
        }
    }

}


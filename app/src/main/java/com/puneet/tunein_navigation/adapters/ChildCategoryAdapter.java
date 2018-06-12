package com.puneet.tunein_navigation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.apiresponse.ChildNavViewModel;
import com.puneet.tunein_navigation.databinding.MusicCategoryLayoutBinding;
import com.puneet.tunein_navigation.databinding.SubCategoryLayoutBinding;
import com.puneet.tunein_navigation.model.CombinedList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChildCategoryAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private OnSelectCategory onSelectCategory;
    private List<CombinedList> combinedLists;
    private ChildNavViewModel.ContentListType contentListType;

    public ChildCategoryAdapter(Context context, OnSelectCategory onSelectCategory, List<CombinedList> combinedLists, ChildNavViewModel.ContentListType contentListType) {
        this.context = context;
        this.onSelectCategory = onSelectCategory;
        this.combinedLists = combinedLists;
        this.contentListType = contentListType;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (contentListType.values()[viewType]) {
            case MUSIC: {
                return new BaseViewHolder(layoutInflater.inflate(R.layout.music_category_layout, parent, false), ChildNavViewModel.ContentListType.MUSIC, true);
            }
            case GENERE: {
                return new BaseViewHolder(layoutInflater.inflate(R.layout.sub_category_layout, parent, false), ChildNavViewModel.ContentListType.GENERE, true);
            }
            default: {
                return new BaseViewHolder(new View(context), null, false);
            }
        }
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        switch (holder.contentListType) {
            case MUSIC: {
                CombinedList musicList = combinedLists.get(position);
                MusicCategoryLayoutBinding musicCategoryLayoutBinding = (MusicCategoryLayoutBinding) holder.binding;
                musicCategoryLayoutBinding.setChildren(musicList);
                Picasso.with(musicCategoryLayoutBinding.childImage.getContext()).load(musicList.getImageUrl()).into(musicCategoryLayoutBinding.childImage);
                musicCategoryLayoutBinding.setClickHandler(view -> Toast.makeText(context, context.getString(R.string.Play_Audio_Message), Toast.LENGTH_SHORT).show());
                musicCategoryLayoutBinding.executePendingBindings();
                break;
            }
            case GENERE: {
                CombinedList genereList = combinedLists.get(position);
                SubCategoryLayoutBinding subCategoryLayoutBinding = (SubCategoryLayoutBinding) holder.binding;
                subCategoryLayoutBinding.setChildBody(genereList);
                subCategoryLayoutBinding.setClickHandler(view -> onSelectCategory.onSelectCategory(genereList.getSubCatUrl()));
                subCategoryLayoutBinding.executePendingBindings();
                break;
            }
        }
    }

    @Override
    public int getItemCount() {
        if ((combinedLists != null) || (combinedLists.size() > 0)) {
            return combinedLists.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        return combinedLists.get(position).getViewType();
    }
}

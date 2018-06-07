package com.puneet.tunein_navigation.ui;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.adapters.TopCategoryAdapter;
import com.puneet.tunein_navigation.apiresponse.TopNavViewModel;
import com.puneet.tunein_navigation.databinding.FragmentTopCategoryBinding;
import com.puneet.tunein_navigation.adapters.onSelectCategory;

import java.util.Observable;
import java.util.Observer;

public class TopCategoryFragment extends Fragment implements onSelectCategory, Observer {

    FragmentTopCategoryBinding fragmentTopCategoryBinding;
    private RecyclerView topCategoryRecyclerView;
    private TopNavViewModel topNavViewModel;

    public static TopCategoryFragment newInstance() {
        return new TopCategoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topNavViewModel = new TopNavViewModel(getActivity());
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragmentTopCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_category, container, false);
        return fragmentTopCategoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //fragmentTopCategoryBinding.setTopNavViewModel(topNavViewModel);
        topCategoryRecyclerView = fragmentTopCategoryBinding.topCategoryRecyclerView;
        topCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       // topCategoryRecyclerView.addItemDecoration(new ItemSpacingDecoration(this.getResources().getDimensionPixelOffset(R.dimen.Padding_Medium)));
       // topCategoryRecyclerView.setAdapter(new TopCategoryAdapter(null, topNavViewModel.getCategories()));
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onSelectCategory(String id) {
        ((TuneInActivity)getActivity()).addSubCategoryFragment(id, false);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof  TopNavViewModel) {
            TopNavViewModel topNavViewModel = (TopNavViewModel) o;
            fragmentTopCategoryBinding.setTopNavViewModel(topNavViewModel);
            topCategoryRecyclerView.setAdapter(new TopCategoryAdapter(null, topNavViewModel.getCategories()));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        topNavViewModel.reset();
    }
}

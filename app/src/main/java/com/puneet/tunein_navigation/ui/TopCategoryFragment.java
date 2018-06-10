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
import com.puneet.tunein_navigation.adapters.OnSelectCategory;

import java.util.Observable;
import java.util.Observer;

public class TopCategoryFragment extends Fragment implements OnSelectCategory, Observer {

    private FragmentTopCategoryBinding fragmentTopCategoryBinding;
    private TopNavViewModel topNavViewModel;

    public static TopCategoryFragment newInstance() {
        return new TopCategoryFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        topNavViewModel = new TopNavViewModel(getActivity().getApplicationContext());
        topNavViewModel.addObserver(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragmentTopCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_top_category, container, false);
        return fragmentTopCategoryBinding.getRoot();
    }

    @Override
    public void onDestroy() {
        topNavViewModel.reset();
        super.onDestroy();
    }

    @Override
    public void onSelectCategory(String id) {
        ((TuneInActivity) getActivity()).addSubCategoryFragment(id);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof TopNavViewModel) {
            TopNavViewModel topNavViewModel = (TopNavViewModel) o;
            RecyclerView topCategoryRecyclerView = fragmentTopCategoryBinding.topCategoryRecyclerView;
            topCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            fragmentTopCategoryBinding.setTopNavViewModel(topNavViewModel);
            topCategoryRecyclerView.setAdapter(new TopCategoryAdapter(this, topNavViewModel.getCategories()));
        }
    }

}

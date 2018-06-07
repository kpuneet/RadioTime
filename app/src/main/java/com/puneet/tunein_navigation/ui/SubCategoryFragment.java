package com.puneet.tunein_navigation.ui;

import android.annotation.SuppressLint;
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
import com.puneet.tunein_navigation.adapters.onSelectCategory;

import com.puneet.tunein_navigation.adapters.ChildCategoryAdapter;
import com.puneet.tunein_navigation.apiresponse.ChildNavViewModel;
import com.puneet.tunein_navigation.databinding.FragmentChildCategoryBinding;

@SuppressLint("ValidFragment")
class SubCategoryFragment extends Fragment implements onSelectCategory {

    private String childKey;
    private boolean isTuneInUrl;
    public static final String TUNE_KEY = "is_tune_url";
    private static final String ID_KEY = "id_key";
    FragmentChildCategoryBinding fragmentChildCategoryBinding;
    private ChildNavViewModel childNavViewModel;


    public static SubCategoryFragment newInstance(String categoryId, boolean isTuneUrl) {
        Bundle args = new Bundle();
        args.putSerializable(SubCategoryFragment.TUNE_KEY, isTuneUrl);
        args.putSerializable(SubCategoryFragment.ID_KEY, categoryId);
        SubCategoryFragment subCategoryFragment = new SubCategoryFragment();
        subCategoryFragment.setArguments(args);
        return subCategoryFragment;
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        getArgs(args);
    }

    private void getArgs(Bundle args) {
        if (args != null) {
            childKey = args.getString(SubCategoryFragment.ID_KEY);
            isTuneInUrl = args.getBoolean(SubCategoryFragment.TUNE_KEY);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ID_KEY, childKey);
        outState.putBoolean(TUNE_KEY, isTuneInUrl);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        getArgs(savedInstanceState);
    }

    @Override
    public void onSelectCategory(String id) {
        ((TuneInActivity) getActivity()).addSubCategoryFragment(id, true);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        childNavViewModel = new ChildNavViewModel(getActivity());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragmentChildCategoryBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_child_category, container, false);
        return fragmentChildCategoryBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        childNavViewModel.getChildNavApiResponse(isTuneInUrl ,childKey);
        RecyclerView childCategoryRecyclerView = fragmentChildCategoryBinding.childCategoryRecyclerView;
        childCategoryRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
       // childCategoryRecyclerView.addItemDecoration(new ItemSpacingDecoration(this.getResources().getDimensionPixelOffset(R.dimen.Padding_Medium)));
        childCategoryRecyclerView.setAdapter(new ChildCategoryAdapter(this, childNavViewModel.getChildCategories()));
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        childNavViewModel.reset();
    }


}

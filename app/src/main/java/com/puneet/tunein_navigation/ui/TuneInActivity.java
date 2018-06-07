package com.puneet.tunein_navigation.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.puneet.tunein_navigation.R;
import com.puneet.tunein_navigation.utils.RxBus;

public class TuneInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DataBindingUtil.setContentView(this, R.layout.activity_tune_in);
        if (savedInstanceState == null) {
            addFragment(TopCategoryFragment.newInstance());
        }
    }

    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 1) {
            super.onBackPressed();
        } else {
            finish();
        }
    }

    public void addSubCategoryFragment(String categoryId, boolean isTuneUrl) {
        addFragment(SubCategoryFragment.newInstance(categoryId, isTuneUrl));
    }

    private void addFragment(Fragment fragment) {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.contentPanel, fragment, fragment.getClass().getSimpleName());
        ft.addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

}

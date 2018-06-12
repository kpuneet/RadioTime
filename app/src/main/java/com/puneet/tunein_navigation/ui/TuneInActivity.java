package com.puneet.tunein_navigation.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.puneet.tunein_navigation.R;

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

    public void addSubCategoryFragment(String categoryId) {
        addFragment(SubCategoryFragment.newInstance(categoryId));
    }

    private void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction()
                .replace(R.id.contentPanel, fragment, fragment.getClass().getSimpleName())
                .addToBackStack(fragment.getClass().getSimpleName());
        ft.commit();
    }

}

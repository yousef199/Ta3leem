package com.yousef.ta3leem.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.yousef.ta3leem.ui.ui.Registration.Fragments.LoginFragment;
import com.yousef.ta3leem.ui.ui.Registration.Fragments.RegistrationFragment;
import com.yousef.ta3leem.ui.ui.Registration.Fragments.SignUpFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private RegistrationFragment context;
    private int totalTabs;

    public ViewPagerAdapter(@NonNull FragmentManager fm , int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LoginFragment loginFragment = new LoginFragment();
                return loginFragment;
            case 1:
                return new SignUpFragment();
            default:
                return null;

        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }

}



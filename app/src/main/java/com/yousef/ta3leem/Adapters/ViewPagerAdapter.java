package com.yousef.ta3leem.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.yousef.ta3leem.ui.ui.Registration.Fragments.LoginFragment;
import com.yousef.ta3leem.ui.ui.Registration.Fragments.RegistrationFragment;
import com.yousef.ta3leem.ui.ui.Registration.Fragments.SignUpFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return new SignUpFragment();
        }
       return new LoginFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}



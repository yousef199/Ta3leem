package com.yousef.ta3leem.ui.UI.Registration.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.yousef.ta3leem.Adapters.ViewPagerAdapter;
import com.yousef.ta3leem.databinding.RegistrationFragmentBinding;

public class RegistrationFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager2 viewPager;
    RegistrationFragmentBinding binding;
    ViewPagerAdapter viewPagerAdapter;

    float v = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         binding = RegistrationFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //Hooks
        viewPager = binding.viewPager;
        tabLayout = binding.tabLayout;

        //Setting up the viewPager
        tabLayout.addTab(tabLayout.newTab().setText("تسجيل دخول"));
        tabLayout.addTab(tabLayout.newTab().setText("تسجيل مستخدم"));

        FragmentManager fm = getChildFragmentManager();

        viewPagerAdapter = new ViewPagerAdapter(fm , getLifecycle());
        viewPager.setAdapter(viewPagerAdapter);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        //Animation
        tabLayout.setTranslationY(300);
        tabLayout.setAlpha(v);
        tabLayout.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();


    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

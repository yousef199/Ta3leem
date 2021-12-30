package com.yousef.ta3leem.ui.ui.Registration.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yousef.ta3leem.Adapters.ViewPagerAdapter;
import com.yousef.ta3leem.databinding.RegistrationFragmentBinding;

public class RegistrationFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPager;
    RegistrationFragmentBinding binding;

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
        viewPager = binding.viewPager;
        tabLayout = binding.tabLayout;
        tabLayout.addTab(tabLayout.newTab().setText("Login"));
        tabLayout.addTab(tabLayout.newTab().setText("SignUp"));

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getParentFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(viewPagerAdapter);
        viewPagerAdapter.notifyDataSetChanged();


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

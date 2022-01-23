package com.yousef.ta3leem.ui.UI.Student.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.material.navigation.NavigationView;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.StudentmainpageFragmentBinding;

public class StudentMainPage extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    StudentmainpageFragmentBinding binding;
    View view2;
    private static final float END_SCALE = 0.7f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = StudentmainpageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        view2 = view;
        navigationDrawer();
    }


    //setting up the sidebar
    private void navigationDrawer() {
        //Navigation Drawer
        binding.studentNavigationView.bringToFront();
        binding.studentNavigationView.setNavigationItemSelectedListener(this);
        binding.studentNavigationView.setCheckedItem(R.id.nav_logout);

        //using the menu icon to open sidebar
        binding.studentSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.studentMainPageDrawer.isDrawerVisible(GravityCompat.END)) {
                    binding.studentMainPageDrawer.closeDrawer(GravityCompat.END);
                } else binding.studentMainPageDrawer.openDrawer(GravityCompat.END);
            }
        });

        animateNavigationDrawer();

    }

    //Side Bar Animation Function
    private void animateNavigationDrawer() {
        binding.studentMainPageDrawer.setScrimColor(getResources().getColor(R.color.blue_primary));
        binding.studentMainPageDrawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 + diffScaledOffset;
                binding.contentView.setScaleX(offsetScale);
                binding.contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = binding.contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                binding.contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                new navigation().navigateToLoginPage(view2);
        }
        return true;
    }

    public class navigation {
        public void navigateToLoginPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_studentMainPage_to_registrationFragment);
        }
    }
}


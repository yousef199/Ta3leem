package com.yousef.ta3leem.Helper;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.yousef.ta3leem.R;

public class navigation {
    public void navigateToAdmin(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_registrationFragment_to_adminMainPageFragment);
    }

    public void navigateToStudent(View view) {
        NavController navController = Navigation.findNavController(view);
        navController.navigate(R.id.action_registrationFragment_to_studentMainPage);
    }
}

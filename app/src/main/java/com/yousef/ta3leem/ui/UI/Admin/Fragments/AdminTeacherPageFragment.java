package com.yousef.ta3leem.ui.UI.Admin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.AdminteacherpageFragmentBinding;

public class AdminTeacherPageFragment extends Fragment {
    AdminteacherpageFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminteacherpageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        //Setting the back icon on the toolbar
        binding.adminTeachersToolBar.setNavigationIcon(R.drawable.back_icon);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        toolbarSetup();
        clicks();
    }

    //Setting the toolbar
    private void toolbarSetup(){
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(binding.adminTeachersToolBar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    //onClicks
    private void clicks() {
        binding.adminTeachersToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             new navigation().teacherToMainPage(view);
            }
        });
    }

    private class navigation {
        public void teacherToMainPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminTeacherPage_to_adminMainPageFragment);
    }
}
}

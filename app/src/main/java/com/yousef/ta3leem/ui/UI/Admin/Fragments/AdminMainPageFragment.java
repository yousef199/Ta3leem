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

import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.AdminmainpageFragmentBinding;

public class AdminMainPageFragment extends Fragment  {
    AdminmainpageFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminmainpageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        toolBarSetup();
        clicks();
    }

    public void clicks(){
        binding.studentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new navigation().adminToStudent(view);
            }
        });
        binding.logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new navigation().adminToRegistration(view);
            }
        });

        binding.teacherCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new navigation().adminToTeacher(view);
            }
        });
    }


    public void toolBarSetup(){
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        //Setting the toolbar
        appCompatActivity.setSupportActionBar(binding.adminToolBar);
//        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        appCompatActivity.getSupportActionBar().setTitle(Constants.STUDENT_PAGE_TITLE);
        binding.circleImageView.setImageResource(R.drawable.chat_icon);
    }

    class navigation {
        private void adminToStudent(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminMainPageFragment_to_adminStudentPageFragment);
        }
        private void adminToTeacher(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminMainPageFragment_to_adminTeacherPage);
        }
        private void adminToRegistration(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminMainPageFragment_to_registrationFragment);
        }
    }
}

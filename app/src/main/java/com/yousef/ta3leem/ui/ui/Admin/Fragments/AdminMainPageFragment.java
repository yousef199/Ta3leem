package com.yousef.ta3leem.ui.ui.Admin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.AdminmainpageFragmentBinding;

public class AdminMainPageFragment extends Fragment {
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

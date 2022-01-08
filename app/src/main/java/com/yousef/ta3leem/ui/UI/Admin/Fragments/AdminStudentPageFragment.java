package com.yousef.ta3leem.ui.UI.Admin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.AdminstudentspageFragmentBinding;
import com.yousef.ta3leem.ui.UI.Admin.Dialogs.AddStudentDialog;

public class AdminStudentPageFragment extends Fragment {
    AdminstudentspageFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminstudentspageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        //Setting up the toolbar with the back icon
        binding.adminStudentsToolBar.setNavigationIcon(R.drawable.back_icon);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        toolbarSetup();
        clicks();
    }

    private void toolbarSetup(){
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        //Setting the toolbar
        appCompatActivity.setSupportActionBar(binding.adminStudentsToolBar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);

    }

    private void clicks(){
        //FloatingActionButton onClick
        binding.adminAddStudentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDialog();
            }
        });

        binding.adminStudentsToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new navigation().studentToMainPage(view);
            }
        });
    }

    // Create an instance from the addStudentDialog and show it
    private void openAddDialog(){
        AddStudentDialog addStudentDialog = new AddStudentDialog();
        addStudentDialog.show(getChildFragmentManager() , "dialog");
    }

    private class navigation{
        public void studentToMainPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminStudentPageFragment_to_adminMainPageFragment);
        }

    }
}

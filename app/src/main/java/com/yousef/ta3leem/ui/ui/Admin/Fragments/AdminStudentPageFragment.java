package com.yousef.ta3leem.ui.ui.Admin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yousef.ta3leem.databinding.AdminstudentspageFragmentBinding;
import com.yousef.ta3leem.ui.ui.Admin.Dialogs.AddStudentDialog;

public class AdminStudentPageFragment extends Fragment {
    AdminstudentspageFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminstudentspageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //FloatingActionButton onClick
        binding.addStudentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openAddDialog();
            }
        });
    }

    // Create an instance from the addStudentDialog and show it
    public void openAddDialog(){
        AddStudentDialog addStudentDialog = new AddStudentDialog();
        addStudentDialog.show(getChildFragmentManager() , "dialog");
    }
}

package com.yousef.ta3leem.ui.UI.Student.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yousef.ta3leem.databinding.StudentsubjectspageBinding;

public class StudentSubjectsPage extends Fragment {
    StudentsubjectspageBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = StudentsubjectspageBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        return view;
    }
}

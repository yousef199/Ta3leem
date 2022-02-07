package com.yousef.ta3leem.ui.UI.Teacher.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yousef.ta3leem.databinding.FragmentClassesTeacherBinding;
import com.yousef.ta3leem.databinding.FragmentExamsTeacherBinding;

public class Exams_Teacher extends Fragment {
    FragmentExamsTeacherBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentExamsTeacherBinding.inflate(inflater , container , false);

        return binding.getRoot();
    }
}

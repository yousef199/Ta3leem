package com.yousef.ta3leem.ui.ui.Admin.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yousef.ta3leem.databinding.AdminteacherpageFragmentBinding;

public class AdminTeacherPage extends Fragment {
    AdminteacherpageFragmentBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminteacherpageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();

        return view;

    }
}

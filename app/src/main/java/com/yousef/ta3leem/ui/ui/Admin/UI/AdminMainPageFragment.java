package com.yousef.ta3leem.ui.ui.Admin.UI;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
}

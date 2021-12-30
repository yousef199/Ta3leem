package com.yousef.ta3leem.ui.ui.Registration.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yousef.ta3leem.databinding.LoginFragmentBinding;

public class LoginFragment extends Fragment {

    float v = 0;
    LoginFragmentBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();

        binding.idTextinput.setTranslationX(800);
        binding.passwordTextinput.setTranslationX(800);
        binding.logInforgotPasswordTextView.setTranslationX(800);
        binding.logInButton.setTranslationX(800);

        binding.idTextinput.setAlpha(v);
        binding.passwordTextinput.setAlpha(v);
        binding.logInforgotPasswordTextView.setAlpha(v);
        binding.logInButton.setAlpha(v);

        binding.idTextinput.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        binding.passwordTextinput.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.logInforgotPasswordTextView.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        binding.logInButton.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return view;
    }
}

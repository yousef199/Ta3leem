package com.yousef.ta3leem.ui.ui.Registration.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.yousef.ta3leem.Data.FireBase.SignUpFireBaseAuth;
import com.yousef.ta3leem.databinding.SignupFragmentBinding;

import lombok.Setter;


@Setter
public class SignUpFragment extends Fragment {
    float v = 0;
    SignupFragmentBinding binding;
    String id , password , reEnterPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SignupFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();

//        binding.idTextinput.setTranslationX(800);
//        binding.passwordTextinput.setTranslationX(800);
//        binding.forgotPasswordTextView.setTranslationX(800);
//        binding.loginButton.setTranslationX(800);
//
//        binding.idTextinput.setAlpha(v);
//        binding.passwordTextinput.setAlpha(v);
//        binding.forgotPasswordTextView.setAlpha(v);
//        binding.loginButton.setAlpha(v);
//
//        binding.idTextinput.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(300).start();
//        binding.passwordTextinput.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
//        binding.forgotPasswordTextView.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(500).start();
//        binding.loginButton.animate().translationY(0).alpha(1).setDuration(800).setStartDelay(700).start();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the signupfirebaseauth class to add values of signup to the firebase
                SignUpFireBaseAuth signUpFireBaseAuth = new SignUpFireBaseAuth();
                signUpFireBaseAuth.Register(binding);
            }
        });

    }

}

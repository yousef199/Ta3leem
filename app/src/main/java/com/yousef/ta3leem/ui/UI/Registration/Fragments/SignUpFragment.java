package com.yousef.ta3leem.ui.UI.Registration.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.gms.common.SignInButton;
import com.yousef.ta3leem.databinding.SignupFragmentBinding;
import com.yousef.ta3leem.ui.UI.Registration.ViewModels.SignUpViewModel;

import lombok.Setter;


@Setter
public class SignUpFragment extends Fragment {
    float v = 0;
    SignupFragmentBinding binding;
    SignUpViewModel viewModel;
    String id, password, reEnterPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = SignupFragmentBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(requireActivity()).get(SignUpViewModel.class);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        clicks();
    }

    public void setUser() {
        Initialize();
        if (id.contains("@teacher")) {
            String extractedID = extractID(id);
            viewModel.setTeacher(extractedID, password, reEnterPassword, binding.idSignuptextinput, binding.passwordSignuptextinput, binding.reEnterpasswordSignuptextinput ,binding.signUpProgressBar);
        } else {
            binding.signUpProgressBar.setVisibility(View.VISIBLE);
           viewModel.checkStudent(id, password, reEnterPassword, binding.idSignuptextinput, binding.passwordSignuptextinput, binding.reEnterpasswordSignuptextinput ,binding.signUpProgressBar);
        }
    }


        public void Initialize () {
            id = binding.idSignuptextinput.getEditText().getText().toString().trim();
            password = binding.passwordSignuptextinput.getEditText().getText().toString().trim();
            reEnterPassword = binding.reEnterpasswordSignuptextinput.getEditText().getText().toString().trim();
        }

        public void clicks () {
            binding.signUpButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setUser();
                }
            });
        }
    public String extractID(String id){
        int index = id.indexOf("@");
        String extractedID = id.substring(0 , index);
        return extractedID;
    }

    }


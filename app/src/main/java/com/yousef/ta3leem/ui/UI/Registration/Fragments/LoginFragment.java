package com.yousef.ta3leem.ui.UI.Registration.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.yousef.ta3leem.Data.Room.Enitities.Admin;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.LoginFragmentBinding;
import com.yousef.ta3leem.ui.UI.Registration.ViewModels.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {
    float v = 0;
    LoginFragmentBinding binding;
    List<Admin> allAdmins = new ArrayList<>();

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

        LoginViewModel loginViewModel  = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        loginViewModel.getAllAdmins().observe(getViewLifecycleOwner(), new Observer<List<Admin>>() {
            @Override
            public void onChanged(List<Admin> admins) {
                for (Admin a : admins) {
                    try {
                        allAdmins.add((Admin) a.clone());
                    } catch (CloneNotSupportedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        binding.logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthenticateUser(view);
            }
        });
    }


    public void  AuthenticateUser(View view) {
//       Repo repo = new Repo(getActivity().getApplication());
       String enteredId = binding.idTextinput.getEditText().getText().toString();
       String enteredPassword = binding.passwordTextinput.getEditText().getText().toString();

       if(enteredId.contains("@admin")){
           int index = enteredId.indexOf("@");
           String extractedID = enteredId.substring(0 , index);
           boolean matchFound = false;
           
           //iterate through the admins in the database and find a match
           for (int i = 0; i < allAdmins.size(); i++) {
               String dataBaseId = allAdmins.get(i).getId();
               String dataBasePassword = allAdmins.get(i).getPassword();
               
               if (extractedID.equals(dataBaseId) && enteredPassword.equals(dataBasePassword) ) {
                   matchFound = true;
                   break;
               }
           }

           if (matchFound) {
               new navigation().navigateToAdmin(view);
           }
           else
               Toast.makeText(getActivity(), "Wrong ID or Password", Toast.LENGTH_SHORT).show();
           
       }

       else if(enteredId.contains("@teacher")){

       }

       else{

        }

    }

    class navigation {
        public void navigateToAdmin(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_registrationFragment_to_adminMainPageFragment);
        }
    }
}

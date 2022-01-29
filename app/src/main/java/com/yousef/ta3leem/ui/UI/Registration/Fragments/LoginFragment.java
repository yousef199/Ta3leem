package com.yousef.ta3leem.ui.UI.Registration.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.Room.Enitities.Admin;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.LoginFragmentBinding;
import com.yousef.ta3leem.ui.UI.Registration.ViewModels.LoginViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginFragment extends Fragment {
    float v = 0;
    String enteredId ,enteredPassword , destination;
    LoginFragmentBinding binding;
    List<Admin> allAdmins = new ArrayList<>();
    LoginViewModel loginViewModel;
    String imageURL;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = LoginFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        loginViewModel  = new ViewModelProvider(requireActivity()).get(LoginViewModel.class);
        animation();
        getAllAdmins();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        clicks();
    }


    public void  AuthenticateUser(View view) {
//       Repo repo = new Repo(getActivity().getApplication());
        getFields();
        setEmptyFieldError();
        destination = "no where";
        if(!enteredId.equals("") && !enteredPassword.equals("")) {
            if (enteredId.contains("@admin")) {
                destination = "admin";
                String name = "";
                String extractedID = extractID(enteredId);
                boolean matchFound = false;

                //iterate through the admins in the database and find a match
                for (int i = 0; i < allAdmins.size(); i++) {
                    String dataBaseId = allAdmins.get(i).getId();
                    String dataBasePassword = allAdmins.get(i).getPassword();

                    if (extractedID.equals(dataBaseId) && enteredPassword.equals(dataBasePassword)) {
                        name = allAdmins.get(i).getName();
                        imageURL = allAdmins.get(i).getImage();
                        matchFound = true;
                        break;
                    }
                }

                if (matchFound) {
                    checkImage();
                    new navigation().navigateToAdmin(view , name , imageURL);
                } else
                    Toast.makeText(getActivity(), Constants.WRONG_USERNAME_PASSWORD, Toast.LENGTH_SHORT).show();

            } else if (enteredId.contains("@teacher")) {
                destination = "teacher";
                String extractedID = extractID(enteredId);
                loginViewModel.checkTeacher(extractedID, enteredPassword, view);
            } else {
                destination = "student";
                loginViewModel.checkStudent(enteredId, enteredPassword, view);
            }

        }
    }


    public void getFields() {
    enteredId =binding.idTextinput.getEditText().getText().toString();
    enteredPassword =binding.passwordTextinput.getEditText().getText().toString();
    }

    public void setEmptyFieldError(){
        if(enteredId.equals("")){
            binding.idTextinput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            binding.idTextinput.setErrorEnabled(true);
        }
        else {
            binding.idTextinput.setError(null);
            binding.idTextinput.setErrorEnabled(false);
        }

        if(enteredPassword.equals("")){
            binding.passwordTextinput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            binding.passwordTextinput.setErrorEnabled(true);
        }
        else {
            binding.passwordTextinput.setError(null);
            binding.passwordTextinput.setErrorEnabled(false);
        }

    }

    //extract the id from the
    public String extractID(String id){
        int index = id.indexOf("@");
        String extractedID = id.substring(0 , index);
        return extractedID;
    }

    //enter animation
    public void animation(){
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
    }

    //observing the live data
    public void getAllAdmins(){
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
    }

    public void clicks(){
        binding.logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthenticateUser(view);
                loginViewModel.clearSharedPrefs(destination);
            }
        });
    }

    public void checkImage(){
        if (imageURL.equals("")){
            imageURL = Integer.toString(R.drawable.user_icon);
        }
    }

    public static class navigation {
        public void navigateToAdmin(View view , String name , String image) {
            RegistrationFragmentDirections.ActionRegistrationFragmentToAdminMainPageFragment action = RegistrationFragmentDirections.actionRegistrationFragmentToAdminMainPageFragment();
            action.setName(name);
            action.setImage(image);
            NavController navController = Navigation.findNavController(view);
            navController.navigate(action);
        }

        public void navigateToStudent(View view , String name, String id , String image , String className) {
            NavController navController = Navigation.findNavController(view);
            RegistrationFragmentDirections.ActionRegistrationFragmentToStudentMainPage action = RegistrationFragmentDirections.actionRegistrationFragmentToStudentMainPage();
            action.setName(name);
            action.setImage(image);
            action.setId(id);
            action.setClassName(className);
            navController.navigate(action);
        }

        public void navigateToTeacher(View view , String name,String id , String image){
            NavController navController = Navigation.findNavController(view);
            RegistrationFragmentDirections.ActionRegistrationFragmentToTeacherMainPageFragment action = RegistrationFragmentDirections.actionRegistrationFragmentToTeacherMainPageFragment();
            action.setName(name);
            action.setImage(image);
            action.setId(id);
            navController.navigate(action);
        }
    }
}

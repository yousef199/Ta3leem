package com.yousef.ta3leem.ui.UI.Admin.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.AdminmainpageFragmentBinding;

import java.util.Map;

public class AdminMainPageFragment extends Fragment  {
    AdminmainpageFragmentBinding binding;
    String passedName , name , imageUrl , passedImage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminmainpageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        saveNamePassedValue();
        saveImagePassedValue();
        getAdminInfo();
        toolBarSetup();
        clicks();
    }

    public void clicks(){
        binding.studentCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new navigation().adminToStudent(view);
            }
        });
        binding.logoutCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new navigation().adminToRegistration(view);
            }
        });

        binding.teacherCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new navigation().adminToTeacher(view);
            }
        });
    }


    public void toolBarSetup(){
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        //Setting the toolbar
        appCompatActivity.setSupportActionBar(binding.adminToolBar);
        appCompatActivity.getSupportActionBar().setDisplayShowTitleEnabled(false);
        binding.adminNameTextView.setText(name);
        setImage();
//        setImage();
    }

    class navigation {
        private void adminToStudent(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminMainPageFragment_to_adminStudentPageFragment);
        }
        private void adminToTeacher(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminMainPageFragment_to_adminTeacherPage);
        }
        private void adminToRegistration(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminMainPageFragment_to_registrationFragment);
        }
    }

//    public void setImage(){
//        passedImage = AdminMainPageFragmentArgs.fromBundle(getArguments()).getImage();
//        Glide.with(this)
//                .load(passedImage)
//                .into(binding.circleImageView);
//    }

    public void saveNamePassedValue(){
            passedName = AdminMainPageFragmentArgs.fromBundle(getArguments()).getName();
            saveName();
    }

    public void saveName(){
        if(!(passedName == null)) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.ADMIN_NAME_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", passedName);
            editor.apply();
        }
    }

    public void saveImagePassedValue(){
        passedImage = AdminMainPageFragmentArgs.fromBundle(getArguments()).getImage();
        saveImage();
    }

    public void saveImage(){
        if(!(passedImage == null)) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.ADMIN_NAME_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("image", passedImage);
            editor.apply();
        }
    }

    public void getAdminInfo(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.ADMIN_NAME_PREF , Context.MODE_PRIVATE);
        Map<String , ?> prefImage = sharedPreferences.getAll();

        for (Map.Entry<String , ?> m : prefImage.entrySet()){
            if(m.getKey() == "image")
                imageUrl = (String) m.getValue();
            if(m.getKey() == "name")
                name = (String) m.getValue();
        }
    }

    public void  setImage(){
        Glide.with(this)
                .load(imageUrl)
                .centerCrop()
                .into(binding.circleImageView);

        Log.i("imageURL" , imageUrl);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        clearSharedPreferences();
    }
}

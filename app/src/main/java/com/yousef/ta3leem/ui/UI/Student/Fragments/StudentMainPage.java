package com.yousef.ta3leem.ui.UI.Student.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.CallBacks.teacherSubjectCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.TeacherSubject;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.StudentmainpageFragmentBinding;
import com.yousef.ta3leem.ui.UI.Student.ViewModels.StudentViewModel;

import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * This class handles the student main page.
 */
public class StudentMainPage extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    StudentmainpageFragmentBinding binding;
    View view2 , header;
    String passedName , passedImage, passedID ,passedClassName, ID , name , imageUrl , className;
    StudentViewModel studentViewModel;
    private static final float END_SCALE = 0.7f;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = StudentmainpageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        studentViewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        view2 = view;
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        saveAllValues();
        getAllStudentInfo();
        navigationDrawer();
        binding.studentNameTextView.setText(name);
        clicks();
    }

    //All methods

    //setting up the sidebar
    private void navigationDrawer() {
        //Navigation Drawer
        binding.studentNavigationView.bringToFront();
        binding.studentNavigationView.setNavigationItemSelectedListener(this);

        //header view
        header = binding.studentNavigationView.getHeaderView(0);
        setStudentImage();
        setStudentName();
        setStudentId();

        //using the menu icon to open sidebar
        binding.studentSideBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.studentMainPageDrawer.isDrawerVisible(GravityCompat.END)) {
                    binding.studentMainPageDrawer.closeDrawer(GravityCompat.END);
                } else binding.studentMainPageDrawer.openDrawer(GravityCompat.END);
            }
        });

        animateNavigationDrawer();

    }

    //Side Bar Animation Function
    private void animateNavigationDrawer() {
        binding.studentMainPageDrawer.setScrimColor(getResources().getColor(R.color.blue_primary));
        binding.studentMainPageDrawer.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // Scale the View based on current slide offset
                final float diffScaledOffset = slideOffset * (1 - END_SCALE);
                final float offsetScale = 1 + diffScaledOffset;
                binding.contentView.setScaleX(offsetScale);
                binding.contentView.setScaleY(offsetScale);

                // Translate the View, accounting for the scaled width
                final float xOffset = drawerView.getWidth() * slideOffset;
                final float xOffsetDiff = binding.contentView.getWidth() * diffScaledOffset / 2;
                final float xTranslation = xOffset - xOffsetDiff;
                binding.contentView.setTranslationX(xTranslation);
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_logout:
                new navigation().navigateToLoginPage(view2);
        }
        return true;
    }

    public void saveAllValues(){
        saveNamePassedValue();
        saveIDPassedValue();
        saveImagePassedValue();
        saveClassNamePassedValue();
    }

    public void saveNamePassedValue(){
        passedName = StudentMainPageArgs.fromBundle(getArguments()).getName();
        saveName();
    }

    public void saveName(){
        if(!(passedName == null)) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.STUDENT_SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", passedName);
            editor.apply();
        }
    }

    public void saveClassNamePassedValue(){
        passedClassName = StudentMainPageArgs.fromBundle(getArguments()).getClassName();
        saveClassName();
    }

    public void saveClassName(){
        if(!(passedClassName == null)) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.STUDENT_SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("className", passedClassName);
            editor.apply();
        }
    }

    public void saveImagePassedValue(){
        passedImage = StudentMainPageArgs.fromBundle(getArguments()).getImage();
        saveImage();
    }

    public void saveImage(){
        if(!(passedImage == null)) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.STUDENT_SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("image", passedImage);
            editor.apply();
        }
    }


    public void saveIDPassedValue(){
        passedID = StudentMainPageArgs.fromBundle(getArguments()).getId();
        saveID();
    }

    public void saveID(){
        if(!(passedID == null)) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.STUDENT_SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("id", passedID);
            editor.apply();
        }
    }

    public void getAllStudentInfo(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.STUDENT_SHARED_PREF , Context.MODE_PRIVATE);
        Map<String , ?> prefValue = sharedPreferences.getAll();

        for (Map.Entry<String , ?> m : prefValue.entrySet()){
            if(m.getKey() == "id")
                ID =(String) m.getValue();
            if(m.getKey() == "image")
                imageUrl = (String) m.getValue();
            if(m.getKey() == "name")
                name = (String) m.getValue();
            if(m.getKey() == "className")
                className = (String) m.getValue();
        }
    }

    public void setStudentImage(){
        CircleImageView studentImage = header.findViewById(R.id.navigationHeaderCircleImage);
        Glide.with(this)
                .load(imageUrl)
                .circleCrop()
                .into(studentImage);
    }

    public void setStudentName(){
        TextView studentName = header.findViewById(R.id.navigationHeaderStudentName);
        studentName.setText(name);
    }

    public void setStudentId(){
        TextView studentId = header.findViewById(R.id.navigationHeaderStudentID);
        studentId.setText(ID);
    }

    public void clicks(){
        binding.messagesImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new navigation().navigateToMessagingPage(view);
            }
        });

        binding.subjectsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //cache the student subjects info
                studentViewModel.getSudentClassTeachers(className, new teacherSubjectCallBack() {
                    @Override
                    public void onCallBack(TeacherSubject teacherSubject) {
                        int counter = 0;
                        Map<String , List<String>> m= teacherSubject.getTeacherSubjects();
                        System.out.println("Size :" + m.size());
                        for(Map.Entry<String , List<String>> m2 : m.entrySet()){
                            System.out.println("Key: " + m2.getKey() + "Value: " + m2.getValue().get(0) + "Value 2: " + m2.getValue().get(1));
                        }
                    }
                });
            }
        });
    }

    //inner classes

    public class navigation {
        public void navigateToLoginPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_studentMainPage_to_registrationFragment);
        }
        public void navigateToMessagingPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_studentMainPage_to_channelFragment);
        }

        public void navigateToSubjectsPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_studentMainPage_to_studentSubjectsPage);
        }
    }
}


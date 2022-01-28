package com.yousef.ta3leem.ui.UI.Teacher.Fragments;

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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.bumptech.glide.Glide;
import com.google.android.material.navigation.NavigationView;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.TeachermainpageFragmentBinding;

import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class TeacherMainPageFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    TeachermainpageFragmentBinding binding;
    View view2;
    String passedName , passedImage,passedID , name , imageUrl , ID;
    View header;
    private static final float END_SCALE = 0.7f;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TeachermainpageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        view2 = view;
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        saveImagePassedValue();
        saveNamePassedValue();
        saveIDPassedValue();
        getAllTeacherInfo();
        binding.teacherNameTextView.setText(name);
        navigationDrawer();
        clicks();
    }

    //setting up the sidebar
    private void navigationDrawer() {
        //Navigation Drawer
        binding.teacherNavigationView.bringToFront();
        binding.teacherNavigationView.setNavigationItemSelectedListener(this);

        //header
        header = binding.teacherNavigationView.getHeaderView(0);
        setTeacherId();
        setTeacherImage();
        setTeacherName();

        //using the menu icon to open sidebar
        binding.teacherSideBar.setOnClickListener(new View.OnClickListener() {
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

    public void saveNamePassedValue(){
        passedName = TeacherMainPageFragmentArgs.fromBundle(getArguments()).getName();
        saveName();
    }

    public void saveName(){
        if(!(passedName == null)) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.TEACHER_SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("name", passedName);
            editor.apply();
        }
    }


    public void saveImagePassedValue(){
        passedImage = TeacherMainPageFragmentArgs.fromBundle(getArguments()).getImage();
        saveImage();
    }

    public void saveImage(){
        if(!(passedImage == null)) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.TEACHER_SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("image", passedImage);
            editor.apply();
        }
    }

    public void saveIDPassedValue(){
        passedID = TeacherMainPageFragmentArgs.fromBundle(getArguments()).getId();
        saveID();
    }

    public void saveID(){
        if(!(passedID == null)) {
            SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.TEACHER_SHARED_PREF, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("id", passedID);
            editor.apply();
        }
    }


    public void getAllTeacherInfo(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.TEACHER_SHARED_PREF , Context.MODE_PRIVATE);
        Map<String , ?> prefValue = sharedPreferences.getAll();

        for (Map.Entry<String , ?> m : prefValue.entrySet()){
            if(m.getKey() == "id")
                ID =(String) m.getValue();
            if(m.getKey() == "image")
                imageUrl = (String) m.getValue();
            if(m.getKey() == "name")
                name = (String) m.getValue();
        }
    }

    public void setTeacherImage(){
        CircleImageView studentImage = header.findViewById(R.id.navigationHeaderCircleImage);
        Glide.with(this)
                .load(imageUrl)
                .circleCrop()
                .into(studentImage);
    }

    public void setTeacherName(){
        TextView studentName = header.findViewById(R.id.navigationHeaderStudentName);
        studentName.setText(name);
    }

    public void setTeacherId(){
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
    }

    //inner classes

    public class navigation {
        public void navigateToLoginPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_teacherMainPageFragment_to_registrationFragment);
        }
        public void navigateToMessagingPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_teacherMainPageFragment_to_nav_graph2);
        }

    }

}

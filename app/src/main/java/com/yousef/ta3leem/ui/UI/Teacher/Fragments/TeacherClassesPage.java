package com.yousef.ta3leem.ui.UI.Teacher.Fragments;

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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.yousef.ta3leem.Adapters.StudentAllSubjectsRecyclerAdapter;
import com.yousef.ta3leem.Adapters.TeacherAllClassesRecyclerAdapter;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.TeacherclassespageBinding;
import com.yousef.ta3leem.ui.UI.Teacher.ViewModel.TeacherViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TeacherClassesPage extends Fragment {
    TeacherclassespageBinding binding;
    TeacherViewModel viewModel;
    String id;
    List<String> classes = new ArrayList<>();
    List<String> subjects = new ArrayList<>();
    TeacherAllClassesRecyclerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = TeacherclassespageBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(TeacherViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getIDFromSharedPref();
        System.out.println("ID: " + id);
        toolbarSetup();
        setRecycler();
        getClassesInfo();
    }


    public void setRecycler(){
        binding.teacherClassesRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.teacherClassesRecyclerView.setHasFixedSize(true);
        adapter = new TeacherAllClassesRecyclerAdapter(getActivity());
        binding.teacherClassesRecyclerView.setAdapter(adapter);
    }

    public void getClassesInfo(){
        viewModel.observeTeacherClassesInfo(id).observe(getViewLifecycleOwner(), new Observer<Map<String, List<String>>>() {
            @Override
            public void onChanged(Map<String, List<String>> stringListMap) {
                Map<String , List<String>> m= stringListMap;
                classes.clear();
                subjects.clear();
                System.out.println("Size :" + m.size());
                for(Map.Entry<String , List<String>> m2 : m.entrySet()){
                    for(String s:m2.getValue()){
                        classes.add(m2.getKey());
                        subjects.add(s);
                        Log.i("map for loop" , "Entered");
                    }
                }
                adapter.setClassesAndSubjectsLists(classes , subjects);
//                subjects.clear();
//                names.clear();
            }
        });
    }

    private void toolbarSetup(){
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        //Setting the toolbar
        appCompatActivity.setSupportActionBar(binding.teacherClassesToolBar);
        binding.teacherClassesToolBar.setTitleTextAppearance(getActivity() , R.style.semi_bold_text);
        setHasOptionsMenu(true);
    }

    public void getIDFromSharedPref(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.TEACHER_SHARED_PREF , Context.MODE_PRIVATE);
        Map<String , ?> prefValue = sharedPreferences.getAll();

        for (Map.Entry<String , ?> m : prefValue.entrySet()){
            if(m.getKey().equals("id"))
                id = (String) m.getValue();
        }
    }

    //inner classes
    public static class navigation{
        public void navigateToClassPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_teacherClassesPage_to_teacherClassMainPage);
        }
    }
}

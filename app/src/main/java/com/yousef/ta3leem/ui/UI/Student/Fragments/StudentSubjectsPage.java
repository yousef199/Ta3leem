package com.yousef.ta3leem.ui.UI.Student.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.StudentsubjectspageBinding;
import com.yousef.ta3leem.ui.UI.Student.ViewModels.StudentViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StudentSubjectsPage extends Fragment {
    StudentsubjectspageBinding binding;
    StudentViewModel viewModel;
    String className;
    List<String>names = new ArrayList<>();
    List<String>subjects = new ArrayList<>();
    StudentAllSubjectsRecyclerAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = StudentsubjectspageBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        getClassName();
        setRecycler();
        toolbarSetup();
        getNamesAndSubjects();

    }

    public void setRecycler(){
        binding.studentSubjectsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.studentSubjectsRecyclerView.setHasFixedSize(true);
        adapter = new StudentAllSubjectsRecyclerAdapter(getActivity());
        binding.studentSubjectsRecyclerView.setAdapter(adapter);
    }

    private void toolbarSetup(){
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        //Setting the toolbar
        appCompatActivity.setSupportActionBar(binding.studentSubjectsToolBar);
        binding.studentSubjectsToolBar.setTitleTextAppearance(getActivity() , R.style.semi_bold_text);
        setHasOptionsMenu(true);
    }

    public void getNamesAndSubjects(){
        viewModel.observeStudentStudentSubjectPageInfo(className).observe(getViewLifecycleOwner(), new Observer<Map<String, List<String>>>() {
            @Override
            public void onChanged(Map<String, List<String>> stringListMap) {
                System.out.println("onChanged Called");
                Map<String , List<String>> m= stringListMap;
                names.clear();
                subjects.clear();
                for(Map.Entry<String , List<String>> m2 : m.entrySet()){
                    for(String s:m2.getValue()){
                        names.add(m2.getKey());
                        subjects.add(s);
                        System.out.println("Teacher Name: " + m2.getKey() + "Subject: " + s);
                    }
                }
                adapter.setSubjectsList(subjects , names);
            }
        });
    }

    public void getClassName(){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(Constants.STUDENT_SHARED_PREF , Context.MODE_PRIVATE);
        Map<String , ?> prefValue = sharedPreferences.getAll();


        for (Map.Entry<String , ?> m : prefValue.entrySet()){
            if(m.getKey().equals("className"))
                className = (String) m.getValue();
        }
    }


    public static class navigation {
        public void navigateToSubjectMainPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_studentSubjectsPage_to_studentClassPage);
        }
    }
}

package com.yousef.ta3leem.ui.UI.Admin.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yousef.ta3leem.Adapters.AdminTeacherRecyclerAdapter;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.CallBacks.allTeachersFirebaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseGet;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Data.Room.Enitities.Admin;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.Repository.Repo;
import com.yousef.ta3leem.databinding.AdminteacherpageFragmentBinding;
import com.yousef.ta3leem.ui.UI.Admin.Dialogs.AddStudentDialog;
import com.yousef.ta3leem.ui.UI.Admin.Dialogs.AddTeacherDialog;
import com.yousef.ta3leem.ui.UI.Admin.ViewModels.AdminTeacherViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class AdminTeacherPageFragment extends Fragment {
    AdminteacherpageFragmentBinding binding;
    AdminTeacherRecyclerAdapter adapter;
    AdminTeacherViewModel viewModel;
    Teacher teachersArray [];
    List<Teacher> teacherList = new ArrayList<>();



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminteacherpageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();

        //Initialize the view model
        viewModel = new ViewModelProvider(this).get(AdminTeacherViewModel.class);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        toolbarSetup();
        setRecyclerView();
        setTeacherList();
        clicks();
    }


    public void setTeacherList(){
        viewModel.getTeachersList().observe(getViewLifecycleOwner(), new Observer<List<Teacher>>() {
            @Override
            public void onChanged(List<Teacher> teachers) {
                adapter.setTeacherList(teachers);
                Log.i("onChanged" , "onChanged Called");
            }
        });
    }

    public void setRecyclerView(){
        binding.adminTeachersRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.adminTeachersRecycler.setHasFixedSize(true);
        adapter = new AdminTeacherRecyclerAdapter(getActivity());
        new ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.adminTeachersRecycler);
        binding.adminTeachersRecycler.setAdapter(adapter);
    }


    //Setting the toolbar
    private void toolbarSetup(){
        //Setting the back icon on the toolbar
        binding.adminTeachersToolBar.setNavigationIcon(R.drawable.back_icon);

        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        appCompatActivity.setSupportActionBar(binding.adminTeachersToolBar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.adminTeachersToolBar.setTitleTextAppearance(getActivity() , R.style.semi_bold_text);
        appCompatActivity.getSupportActionBar().setTitle(Constants.TEACHER_PAGE_TITLE);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu , menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.deleteTeacher(viewHolder.getAbsoluteAdapterPosition());
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            new RecyclerViewSwipeDecorator.Builder(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                    .addSwipeLeftBackgroundColor(ContextCompat.getColor(getActivity() , R.color.red))
                    .addSwipeLeftActionIcon(R.drawable.delete_icon)
                    .create()
                    .decorate();
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

    //onClicks
    private void clicks() {
        binding.adminAddTeacherFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDialog();
            }
        });

        binding.adminTeachersToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             new navigation().teacherToMainPage(view);
            }
        });
    }

    private void openAddDialog(){
        AddTeacherDialog addTeacherDialog = new AddTeacherDialog();
        addTeacherDialog.show(getChildFragmentManager() , "dialog");
    }

    private class navigation {
        public void teacherToMainPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminTeacherPage_to_adminMainPageFragment);
    }
}
}

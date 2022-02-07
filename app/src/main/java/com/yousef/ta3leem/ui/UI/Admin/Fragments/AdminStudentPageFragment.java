package com.yousef.ta3leem.ui.UI.Admin.Fragments;

import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toolbar;

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

import com.yousef.ta3leem.Adapters.AdminStudentRecyclerAdapter;
import com.yousef.ta3leem.Adapters.AdminTeacherRecyclerAdapter;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.databinding.AdminstudentspageFragmentBinding;
import com.yousef.ta3leem.ui.UI.Admin.Dialogs.AddStudentDialog;
import com.yousef.ta3leem.ui.UI.Admin.ViewModels.AdminStudentViewModel;

import java.util.List;

import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator;

public class AdminStudentPageFragment extends Fragment {
    AdminstudentspageFragmentBinding binding;
    AdminStudentRecyclerAdapter adapter;
    AdminStudentViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = AdminstudentspageFragmentBinding.inflate(inflater , container , false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(AdminStudentViewModel.class);
        //Setting up the toolbar with the back icon
        binding.adminStudentsToolBar.setNavigationIcon(R.drawable.back_icon);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        toolbarSetup();
        setRecyclerView();
        setStudentList();
        clicks();
    }

    /**get the changes when there is a change to the student list and passes it to the setting method in the adapter */
    public void setStudentList(){
        viewModel.getStudentList().observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> studentList) {
                adapter.setStudentList(studentList);
            }
        });
    }


    public void setRecyclerView(){
        binding.adminStudentsRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.adminStudentsRecycler.setHasFixedSize(true);
        adapter = new AdminStudentRecyclerAdapter(getActivity());
        new ItemTouchHelper(itemTouchCallback).attachToRecyclerView(binding.adminStudentsRecycler);
        binding.adminStudentsRecycler.setAdapter(adapter);
    }

    private void toolbarSetup(){
        AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
        //Setting the toolbar
        appCompatActivity.setSupportActionBar(binding.adminStudentsToolBar);
        appCompatActivity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appCompatActivity.getSupportActionBar().setDisplayShowHomeEnabled(true);
        binding.adminStudentsToolBar.setTitleTextAppearance(getActivity() , R.style.semi_bold_text);
        appCompatActivity.getSupportActionBar().setTitle(Constants.STUDENT_PAGE_TITLE);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu , menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void clicks(){
        //FloatingActionButton onClick
        binding.adminAddStudentFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAddDialog();
            }
        });

        //Toolbar backward navigation
        binding.adminStudentsToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new navigation().studentToMainPage(view);
            }
        });
    }

    ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            adapter.deleteStudent(viewHolder.getAbsoluteAdapterPosition());
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

    // Create an instance from the addStudentDialog and show it
    private void openAddDialog(){
        AddStudentDialog addStudentDialog = new AddStudentDialog();
        addStudentDialog.show(getChildFragmentManager() , "dialog");
    }

    private class navigation{
        public void studentToMainPage(View view){
            NavController navController = Navigation.findNavController(view);
            navController.navigate(R.id.action_adminStudentPageFragment_to_adminMainPageFragment);
        }
    }
}

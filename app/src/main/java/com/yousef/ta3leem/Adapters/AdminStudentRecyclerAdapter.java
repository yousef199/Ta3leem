package com.yousef.ta3leem.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdminStudentRecyclerAdapter extends RecyclerView.Adapter<AdminStudentRecyclerAdapter.viewHolder>  {
    List<Student> studentList = new ArrayList<>();
    Set<Student> checkList = new HashSet<>();
    Context context;

    public AdminStudentRecyclerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    //inflate the layout
    public AdminStudentRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherrecycler, parent, false);
        return new AdminStudentRecyclerAdapter.viewHolder(itemView);
    }

    @Override
    //set the value for the elements from the list current element
    public void onBindViewHolder(@NonNull AdminStudentRecyclerAdapter.viewHolder holder, int position) {
        Student student = studentList.get(position);
        if(student.getImage().equals("null")){
            Glide.with(context)
                    .load(R.drawable.user_icon)
                    .centerCrop()
                    .into(holder.circleImageView);
        }
        else
            Glide.with(context)
                    .load(student.getImage())
                    .centerCrop()
                    .into(holder.circleImageView);

        holder.textView.setText(student.getName());
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    public void setStudentList(List<Student> studentList){
        this.studentList.clear();
        for (Student student : studentList){
            if(!(checkList.contains(student))){
                this.studentList.add(student);
                checkList.add(student);
            }
        }
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        CircleImageView circleImageView;
        TextView textView;


        public viewHolder(@NonNull View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.recyclerStudentImage);
            textView = itemView.findViewById(R.id.recyclerStudentName);

        }
    }
}

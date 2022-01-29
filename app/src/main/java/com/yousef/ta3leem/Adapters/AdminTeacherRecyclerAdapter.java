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
import com.yousef.ta3leem.Data.FireBase.CallBacks.allTeachersFirebaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseGet;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.R;
import com.yousef.ta3leem.Repository.Repo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;


public class AdminTeacherRecyclerAdapter extends RecyclerView.Adapter<AdminTeacherRecyclerAdapter.viewHolder> {
    List<Teacher> teacherList = new ArrayList<>();
    Set <Teacher> checkTeacherList = new HashSet<>();
    Context context;
    int counter = 1;

    public AdminTeacherRecyclerAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    //inflate the layout
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacherrecycler, parent, false);
        Log.i("Recycler View Inflater" , "Called" + counter);
        counter++;
        return new viewHolder(itemView);
    }

    @Override
    //set the value for the elements from the list current element
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Teacher teacher = teacherList.get(position);
        Glide.with(context)
                .load(teacher.getImage())
                .centerCrop()
                .into(holder.circleImageView);

        holder.textView.setText(teacher.getName());
    }

    @Override
    public int getItemCount() {
        return teacherList.size();
    }

    public void setTeacherList(List<Teacher> teacherList){
        this.teacherList.clear();
        for (Teacher teacher : teacherList){
            if (!checkTeacherList.contains(teacher)){
                this.teacherList.add(teacher);
                this.checkTeacherList.add(teacher);
            }
        }
        Log.i("teacher list size " , Integer.toString(this.teacherList.size()));
        Log.i("teacher set size " , Integer.toString(this.checkTeacherList.size()));
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

package com.yousef.ta3leem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yousef.ta3leem.R;

import java.util.ArrayList;
import java.util.List;

public class TeacherAllClassesRecyclerAdapter extends RecyclerView.Adapter<TeacherAllClassesRecyclerAdapter.viewHolder> {
    List<String> Classes = new ArrayList<>();
    List<String> Subjects = new ArrayList<>();
    Context context;

    public TeacherAllClassesRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    //inflate the layout
    public TeacherAllClassesRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.classescarddesign, parent, false);
        return new TeacherAllClassesRecyclerAdapter.viewHolder(itemView);
    }

    @Override
    //set the value for the elements from the list current element
    public void onBindViewHolder(@NonNull TeacherAllClassesRecyclerAdapter.viewHolder holder, int position) {
        String className = Classes.get(position);
        String subject = Subjects.get(position);

        holder.className.setText(className);
        holder.subjectName.setText(subject);

        switch (subject) {
            case "الرياضيات":
                holder.classImage.setImageResource(R.drawable.mathematics);
                break;
            case "التربيه الفنيه":
                holder.classImage.setImageResource(R.drawable.mathematics);
                break;
            case "المهني":
                holder.classImage.setImageResource(R.drawable.mathematics);
                break;
            case "اللغه الانجليزي":
                holder.classImage.setImageResource(R.drawable.english);
                break;
            case "التاريخ":
                holder.classImage.setImageResource(R.drawable.geography);
                break;
            case "التربيه الوطنيه":
                holder.classImage.setImageResource(R.drawable.geography);
                break;
            case "العلوم":
                holder.classImage.setImageResource(R.drawable.english);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return Classes.size();
    }

    public void setClassesAndSubjectsLists(List<String> classesNames, List<String> subjectsNames) {
//        subjectsNames.clear();
//        teachersNames.clear();
        for (String s : classesNames) {
            Classes.add(s);
        }
        for (String s2 : subjectsNames) {
            Subjects.add(s2);
        }
        notifyDataSetChanged();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView classImage;
        TextView subjectName , className;
        Button enterClass;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            classImage = (ImageView) itemView.findViewById(R.id.teacherClassCardImage);
            subjectName = (TextView) itemView.findViewById(R.id.classCardSubjectName);
            className = (TextView) itemView.findViewById(R.id.classCardName);
            enterClass = (Button) itemView.findViewById(R.id.classEnterButton);
        }
    }
}

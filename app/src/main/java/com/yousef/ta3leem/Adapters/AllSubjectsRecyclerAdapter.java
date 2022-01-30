package com.yousef.ta3leem.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.R;

import java.util.ArrayList;
import java.util.List;

public class AllSubjectsRecyclerAdapter extends RecyclerView.Adapter<AllSubjectsRecyclerAdapter.viewHolder> {
    List<String> Subjects = new ArrayList<>();
    List<String> Names = new ArrayList<>();
    Context context;

    public AllSubjectsRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    //inflate the layout
    public AllSubjectsRecyclerAdapter.viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.subjectcarddesign, parent, false);
        return new AllSubjectsRecyclerAdapter.viewHolder(itemView);
    }

    @Override
    //set the value for the elements from the list current element
    public void onBindViewHolder(@NonNull AllSubjectsRecyclerAdapter.viewHolder holder, int position) {
        String subject = Subjects.get(position);
        String name = Names.get(position);

        holder.teacherName.setText(name);
        holder.subjectName.setText(subject);

        switch (subject) {
            case "الرياضيات":
                holder.subjectImage.setImageResource(R.drawable.mathematics);
                break;
            case "التربيه الفنيه":
                holder.subjectImage.setImageResource(R.drawable.mathematics);
                break;
            case "المهني":
                holder.subjectImage.setImageResource(R.drawable.mathematics);
                break;
            case "اللغه الانجليزي":
                holder.subjectImage.setImageResource(R.drawable.english);
                break;
            case "التاريخ":
                holder.subjectImage.setImageResource(R.drawable.geography);
                break;
            case "التربيه الوطنيه":
                holder.subjectImage.setImageResource(R.drawable.geography);
                break;
            case "العلوم":
                holder.subjectImage.setImageResource(R.drawable.english);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return Names.size();
    }

    public void setSubjectsList(List<String> subjectsNames, List<String> teachersNames) {
//        subjectsNames.clear();
//        teachersNames.clear();
        for (String s : subjectsNames) {
            Subjects.add(s);
        }
        for (String s2 : teachersNames) {
            Names.add(s2);
        }
        notifyDataSetChanged();
    }


    public class viewHolder extends RecyclerView.ViewHolder{
        ImageView subjectImage;
        TextView subjectName , teacherName;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            subjectImage = (ImageView) itemView.findViewById(R.id.subjectCardImage);
            subjectName = (TextView) itemView.findViewById(R.id.subjectCardName);
            teacherName = (TextView) itemView.findViewById(R.id.subjectCardTeacherName);
        }
    }
}

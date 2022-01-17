package com.yousef.ta3leem.Adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.yousef.ta3leem.R;

import de.hdodenhof.circleimageview.CircleImageView;


//Todo: Finisht the Teacher adapter
public class AdminTeacherRecyclerAdapter extends RecyclerView.Adapter<AdminTeacherRecyclerAdapter.viewHolder> {

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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

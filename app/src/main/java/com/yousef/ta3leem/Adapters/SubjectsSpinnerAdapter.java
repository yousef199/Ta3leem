package com.yousef.ta3leem.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Helper.DropDownListObject;
import com.yousef.ta3leem.R;

import java.util.ArrayList;
import java.util.List;

public class SubjectsSpinnerAdapter extends ArrayAdapter<DropDownListObject> {
    private Context mContext;
    private ArrayList<DropDownListObject> listState;
    private SubjectsSpinnerAdapter subjectsSpinnerAdapter;
    private boolean isFromView = false;

    public SubjectsSpinnerAdapter(Context context, int resource, List<DropDownListObject> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.listState = (ArrayList<DropDownListObject>) objects;
        this.subjectsSpinnerAdapter = this;
    }

    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return getCustomView(position, convertView, parent);
    }

    public View getCustomView(final int position, View convertView,
                              ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            LayoutInflater layoutInflator = LayoutInflater.from(mContext);
            convertView = layoutInflator.inflate(R.layout.subjectsdropdownlistdesign, null);
            holder = new ViewHolder();
            holder.mTextView = (TextView) convertView
                    .findViewById(R.id.text);
            holder.mCheckBox = (CheckBox) convertView
                    .findViewById(R.id.checkbox);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.mTextView.setText(listState.get(position).getTitle());

        // To check weather checked event fire from getview() or user input
        isFromView = true;
        holder.mCheckBox.setChecked(listState.get(position).isSelected());
        isFromView = false;

        if ((position == 0)) {
            holder.mCheckBox.setVisibility(View.INVISIBLE);
        } else {
            holder.mCheckBox.setVisibility(View.VISIBLE);
        }
        holder.mCheckBox.setTag(position);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                int getPosition = (Integer) buttonView.getTag();

                if (!isFromView) {
                    listState.get(position).setSelected(isChecked);
                }
                //Placing the checked checkbox in shared preferences
                if(listState.get(position).isSelected()){
                    SharedPreferences sharedPreferences = mContext.getSharedPreferences(Constants.SUBJECTS_PREF,Context.MODE_PRIVATE );
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(listState.get(position).getTitle() , listState.get(position).getTitle());
                    editor.apply();
                }
                else{
                    SharedPreferences sharedPreferences = mContext.getSharedPreferences(Constants.SUBJECTS_PREF, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.remove(listState.get(position).getTitle());
                    editor.apply();
                }


            }
        });
        return convertView;
    }

    public ArrayList<DropDownListObject> getListState() {
        return listState;
    }

    private class ViewHolder {
        private TextView mTextView;
        private CheckBox mCheckBox;
    }
}
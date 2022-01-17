package com.yousef.ta3leem.ui.UI.Admin.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.android.material.textfield.TextInputLayout;
import com.yousef.ta3leem.Adapters.CheckableSpinnerAdapter;
import com.yousef.ta3leem.Data.FireBase.Add;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.Helper.StateVO;
import com.yousef.ta3leem.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddTeacherDialog extends AppCompatDialogFragment {
    TextInputLayout id , name;
    Spinner spinner;
    final String[] Subjects = {"Math" , "Science" , "English" , "History"};
    ArrayList <StateVO> listVo = new ArrayList<>();

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.addteacherdialog , null);

        //Hooks
        id = view.findViewById(R.id.addTeacherDialog_ID);
        name = view.findViewById(R.id.addTeacherDialog_Name);
        spinner = view.findViewById(R.id.teacherSpinner);

        //This makes the dialog have the custom view we created
        builder.setView(view).
                setTitle("Add Teacher").
                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).
                setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        List <String> subjects= new ArrayList<>();
//                        List <String> classes= new ArrayList<>();
//                        subjects.add("Math");
//                        subjects.add("science");
//                        classes.add("6g");
//                        classes.add("7a");
//                        Teacher teacher = new Teacher("yousef" , "2378", "20" , subjects ,classes);
//                        new Add().addTeacher(teacher);
                    }
                });

        for (int i = 0; i < Subjects.length; i++) {
            StateVO stateVO = new StateVO();
            stateVO.setTitle(Subjects[i]);
            stateVO.setSelected(false);
            listVo.add(stateVO);
        }



        return builder.create();
    }
}

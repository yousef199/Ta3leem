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
import com.yousef.ta3leem.R;

public class AddStudentDialog extends AppCompatDialogFragment {
    private TextInputLayout id , name;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder= new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.studentadddialog , null);

        //This makes the dialog have the custom view we created
        builder.setView(view).
                setTitle("Add Student").
                setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).
                setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        id = view.findViewById(R.id.addStudentDialog_ID);
        name = view.findViewById(R.id.addStudentDialog_Name);


        return builder.create();
    }
}

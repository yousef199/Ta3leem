package com.yousef.ta3leem.ui.UI.Registration.ViewModels;

import android.app.Application;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.google.android.material.textfield.TextInputLayout;
import com.yousef.ta3leem.Constants;
import com.yousef.ta3leem.Data.FireBase.CallBacks.studentFireBaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseAdd;
import com.yousef.ta3leem.Data.FireBase.FireBaseGet;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;

public class SignUpViewModel extends AndroidViewModel {

    public SignUpViewModel(@NonNull Application application) {
        super(application);
    }

    public void checkStudent(String id, String password, String reEnterPassword, TextInputLayout idInput, TextInputLayout passwordInput, TextInputLayout reEnterPasswordInput , ProgressBar progressBar) {
        FireBaseGet get = new FireBaseGet();
        FireBaseAdd add = new FireBaseAdd();
        setErrors(id , password , reEnterPassword , idInput , passwordInput , reEnterPasswordInput , progressBar);
        get.getStudent(id, new studentFireBaseCallBack() {
            @Override
            public void onCallback(Student student) {
                System.out.println(student.getPassword());
                if (get.isExits()) {
                    idInput.setError(null);
                    idInput.setErrorEnabled(false);
                    if (student.getPassword().equals("not set")) {
                        if (password.equals(reEnterPassword)) {
                            passwordInput.setError(null);
                            passwordInput.setErrorEnabled(false);
                            reEnterPasswordInput.setError(null);
                            reEnterPasswordInput.setErrorEnabled(false);
                            add.setStudentPassword(id, password);
                            idInput.getEditText().getText().clear();
                            passwordInput.getEditText().getText().clear();
                            reEnterPasswordInput.getEditText().getText().clear();
                        }
                        else {
                            progressBar.setVisibility(View.INVISIBLE);
                            passwordInput.setError("");
                            passwordInput.setErrorEnabled(true);
                            reEnterPasswordInput.setError("كلمات السر غير متطابقه");
                            reEnterPasswordInput.setErrorEnabled(true);
                        }
                    }
                    else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(getApplication(), "المستخدم مسجل", Toast.LENGTH_SHORT).show();
                    }
                } else if (!get.isExits() && id.equals("")){
                    progressBar.setVisibility(View.INVISIBLE);
                    idInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
                    idInput.setErrorEnabled(true);
                }
                else {
                    progressBar.setVisibility(View.INVISIBLE);
                    idInput.setError("اسم المستخدم غير موجود");
                    idInput.setErrorEnabled(true);
                    passwordInput.setError(null);
                    passwordInput.setErrorEnabled(false);
                    reEnterPasswordInput.setError(null);
                    reEnterPasswordInput.setErrorEnabled(false);
                }
            }
        });
    }

    public void setErrors(String id, String password, String reEnterPassword, TextInputLayout idInput, TextInputLayout passwordInput, TextInputLayout reEnterPasswordInput , ProgressBar progressBar) {
        if (id.equals("")) {
            progressBar.setVisibility(View.INVISIBLE);
            idInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            idInput.setErrorEnabled(true);

            if (password.equals("")) {
                progressBar.setVisibility(View.INVISIBLE);
                passwordInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
                passwordInput.setErrorEnabled(true);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                passwordInput.setError(null);
                passwordInput.setErrorEnabled(false);
            }
            if (reEnterPassword.equals("")) {
                progressBar.setVisibility(View.INVISIBLE);
                reEnterPasswordInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
                reEnterPasswordInput.setErrorEnabled(true);
            } else {
                progressBar.setVisibility(View.INVISIBLE);
                reEnterPasswordInput.setError(null);
                reEnterPasswordInput.setErrorEnabled(false);
            }
        } else {
            progressBar.setVisibility(View.INVISIBLE);
            idInput.setError(null);
            idInput.setErrorEnabled(false);
        }
    }
}

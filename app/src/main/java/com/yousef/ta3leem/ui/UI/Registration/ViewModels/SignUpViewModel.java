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
import com.yousef.ta3leem.Data.FireBase.CallBacks.teacherFireBaseCallBack;
import com.yousef.ta3leem.Data.FireBase.FireBaseAdd;
import com.yousef.ta3leem.Data.FireBase.FireBaseGet;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Student;
import com.yousef.ta3leem.Data.FireBase.FireBaseHelper.Teacher;
import com.yousef.ta3leem.ui.UI.Registration.Fragments.SignUpFragment;

import java.util.concurrent.TimeUnit;

public class SignUpViewModel extends AndroidViewModel {

    SignUpFragment signUpFragment;

    public SignUpViewModel(@NonNull Application application ) {
        super(application);
        this.signUpFragment = signUpFragment;
    }


    public void setTeacher(String id, String password, String reEnterPassword, TextInputLayout idInput, TextInputLayout passwordInput, TextInputLayout reEnterPasswordInput , ProgressBar progressBar){
        progressBar.setVisibility(View.VISIBLE);
        FireBaseGet get = new FireBaseGet();
        FireBaseAdd add = new FireBaseAdd();
        setErrors(id , password , reEnterPassword , idInput , passwordInput , reEnterPasswordInput , progressBar , get.isExits());
        if(!id.equals("")) {
            get.getTeacher(id, new teacherFireBaseCallBack() {
                @Override
                public void teacherOnCallBack(Teacher teacher) {
                    if (get.isExits()) {
                        idInput.setError(null);
                        idInput.setErrorEnabled(false);
                        if (teacher.getPassword().equals("not set")){
                            if (password.equals("") || reEnterPassword.equals("")) {
                                setPasswordError(password, reEnterPassword, passwordInput, reEnterPasswordInput, progressBar);
                            } else if (password.equals(reEnterPassword)) {
                                setPasswordError(password, reEnterPassword, passwordInput, reEnterPasswordInput, progressBar);
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplication(), "تمت عملية التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                                add.setTeacherPassword(id , password);
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                passwordInput.setError("");
                                passwordInput.setErrorEnabled(true);
                                reEnterPasswordInput.setError("كلمات السر غير متطابقه");
                                reEnterPasswordInput.setErrorEnabled(true);
                            }
                        } else if (password.equals("") || reEnterPassword.equals("")) {
                            setPasswordError(password, reEnterPassword, passwordInput, reEnterPasswordInput, progressBar);
                        }
                        else{
                            progressBar.setVisibility(View.INVISIBLE);
                            setPasswordError(password, reEnterPassword, passwordInput, reEnterPasswordInput, progressBar);
                            Toast.makeText(getApplication(), "المستخدم مسجل", Toast.LENGTH_SHORT).show();
                        }
                    } else {
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
    }

    public void checkStudent(String id, String password, String reEnterPassword, TextInputLayout idInput, TextInputLayout passwordInput, TextInputLayout reEnterPasswordInput , ProgressBar progressBar)   {
        progressBar.setVisibility(View.VISIBLE);
        FireBaseGet get = new FireBaseGet();
        FireBaseAdd add = new FireBaseAdd();
        setErrors(id , password , reEnterPassword , idInput , passwordInput , reEnterPasswordInput , progressBar , get.isExits());
        if (!id.equals("")) {
            get.getStudent(id, new studentFireBaseCallBack() {
                @Override
                public void onCallback(Student student) {
                    if (get.isExits()) {
                        idInput.setError(null);
                        idInput.setErrorEnabled(false);
                        if (student.getPassword().equals("not set")) {
                            if (password.equals("") || reEnterPassword.equals("")) {
                                setPasswordError(password, reEnterPassword, passwordInput, reEnterPasswordInput, progressBar);
                            } else if (password.equals(reEnterPassword)) {
                                setPasswordError(password, reEnterPassword, passwordInput, reEnterPasswordInput, progressBar);
                                progressBar.setVisibility(View.INVISIBLE);
                                Toast.makeText(getApplication(), "تمت عملية التسجيل بنجاح", Toast.LENGTH_SHORT).show();
                                add.setStudentPassword(id, password);
                            } else {
                                progressBar.setVisibility(View.INVISIBLE);
                                passwordInput.setError("");
                                passwordInput.setErrorEnabled(true);
                                reEnterPasswordInput.setError("كلمات السر غير متطابقه");
                                reEnterPasswordInput.setErrorEnabled(true);
                            }
                        } else if (password.equals("") || reEnterPassword.equals("")) {
                            setPasswordError(password, reEnterPassword, passwordInput, reEnterPasswordInput, progressBar);
                        } else {
                            progressBar.setVisibility(View.INVISIBLE);
                            setPasswordError(password, reEnterPassword, passwordInput, reEnterPasswordInput, progressBar);
                            Toast.makeText(getApplication(), "المستخدم مسجل", Toast.LENGTH_SHORT).show();
                        }
                    } else if (!get.isExits() && id.equals("")) {
                        progressBar.setVisibility(View.INVISIBLE);
                        idInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
                        idInput.setErrorEnabled(true);
                    } else {
                        if (password.equals("") || reEnterPassword.equals("")){
                            setPasswordError(password, reEnterPassword, passwordInput, reEnterPasswordInput, progressBar);
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
                }
            });
        }
    }

    //Display
    public void setErrors(String id, String password, String reEnterPassword, TextInputLayout idInput, TextInputLayout passwordInput, TextInputLayout reEnterPasswordInput , ProgressBar progressBar , boolean status) {
        if (id.equals("")) {
            progressBar.setVisibility(View.INVISIBLE);
            idInput.setError(Constants.EMPTY_FIELD_ERROR_MESSAGE);
            idInput.setErrorEnabled(true);
            passwordInput.setError(null);
            passwordInput.setErrorEnabled(false);
            reEnterPasswordInput.setError(null);
            reEnterPasswordInput.setErrorEnabled(false);
        }
        else if (!id.equals("")) {
            progressBar.setVisibility(View.INVISIBLE);
            idInput.setError(null);
            idInput.setErrorEnabled(false);

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
        }

    }

    //Display error for password fields
    public void setPasswordError(String password , String reEnterPassword , TextInputLayout passwordInput, TextInputLayout reEnterPasswordInput , ProgressBar progressBar){
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
    }
}

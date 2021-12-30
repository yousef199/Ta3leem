package com.yousef.ta3leem.Authenication.FireBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yousef.ta3leem.databinding.SignupFragmentBinding;
import com.yousef.ta3leem.ui.ui.Registration.Fragments.SignUpFragment;

public class SignUpFireBaseAuth extends SignUpFragment{
    private FirebaseDatabase rootNode;
    private DatabaseReference databaseReference;
    private String id , password , reEnterPassword;

    /*will take a binding instance and call assign values to assign values from the edit texts to
    to the vars id , password and reEnterPassword.
    checks if the password and reEnterPassword are equal and check if the id is valid.
    True: add to firebase
    False: ask user to reenter needed values with corresponding message.
     */
    public void Register(SignupFragmentBinding signupFragmentBinding){
        assignValues(signupFragmentBinding);
        //Reference to the root
        rootNode = FirebaseDatabase.getInstance();
        //Reference to the child of the root
        databaseReference = rootNode.getReference("Teacher");
        if (checkPassword()){

        }
        else {

        }

    }

    //Check if password and reEnterPassword are equal
    public boolean checkPassword(){
        return password ==reEnterPassword;
    }

    public boolean checkId(){
        return true;
    }

    //assign to id , password and reEnterPassword inside SignupFragment using the binding instance and setters
    public void assignValues(SignupFragmentBinding signupFragmentBinding){
        try {
            SignUpFragment signUpFragment = new SignUpFragment();
            signUpFragment.setId(signupFragmentBinding.idSignuptextinput.getEditText().getText().toString());
            signUpFragment.setPassword(signupFragmentBinding.passwordSignuptextinput.getEditText().getText().toString());
            signUpFragment.setReEnterPassword(signupFragmentBinding.reEnterpasswordSignuptextinput.getEditText().getText().toString());
        }
        catch (NullPointerException e) {
            System.out.println("Error assigning values in SignUpFireBaseAuth");
        }
    }

}

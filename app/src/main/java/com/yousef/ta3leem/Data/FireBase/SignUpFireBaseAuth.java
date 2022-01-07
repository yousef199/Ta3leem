package com.yousef.ta3leem.Data.FireBase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yousef.ta3leem.databinding.SignupFragmentBinding;

public class SignUpFireBaseAuth  {
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
            //get the values from the edittexts using the binding instance passed from the sign up fragment to the signupauth
            String id = (signupFragmentBinding.idSignuptextinput.getEditText().getText().toString());
            String password = (signupFragmentBinding.passwordSignuptextinput.getEditText().getText().toString());
            String reEnterPassword = (signupFragmentBinding.reEnterpasswordSignuptextinput.getEditText().getText().toString());

            //assign the values locally here in this class so that we can pass them to the firebase
            this.id = id;
            this.password = password;
            this.reEnterPassword = reEnterPassword;
        }
        catch (NullPointerException e) {
            System.out.println("Error assigning values in SignUpFireBaseAuth");
        }
    }

}

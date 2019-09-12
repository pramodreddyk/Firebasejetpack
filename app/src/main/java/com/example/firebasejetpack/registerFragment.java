package com.example.firebasejetpack;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;


public class registerFragment extends Fragment {

    EditText edt_name,edt_email,edt_pass,edt_cpass;
    Button btn_register;

    private FirebaseAuth auth;

    public registerFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        auth = FirebaseAuth.getInstance();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        edt_email = view.findViewById(R.id.edt_remail);
        edt_pass = view.findViewById(R.id.edt_rpass);
        edt_cpass = view.findViewById(R.id.edt_rcpass);
        edt_name = view.findViewById(R.id.edt_rname);

        btn_register = view.findViewById(R.id.btn_reg);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isCheckEmptyField())
                {
                    if(edt_pass.getText().toString().length()<6)
                    {
                        edt_pass.setError("Invalid Password! Password Should be at least 6 Char! ");
                        edt_pass.requestFocus();
                    }else {
                        if(edt_pass.getText().toString().equals(edt_cpass.getText().toString()))
                        {
                            edt_cpass.setError("Password not Match!");
                            edt_cpass.requestFocus();
                        }else {

                        }
                    }

                }



            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }
    public boolean isCheckEmptyField()
    {
        if (TextUtils.isEmpty(edt_name.getText().toString()))
        {
            edt_name.setError("Name cannot be blank!");
            edt_name.requestFocus();
            return true;
        }else if (TextUtils.isEmpty(edt_email.getText().toString()))
        {
            edt_email.setError("Email cannot be blank!");
            edt_email.requestFocus();
            return true;
        }else if (TextUtils.isEmpty(edt_pass.getText().toString()))
        {
            edt_pass.setError("Password cannot be blank!");
            edt_pass.requestFocus();
            return true;
        }else if (TextUtils.isEmpty(edt_cpass.getText().toString()))
        {
            edt_cpass.setError("Confirm Password cannot be blank!");
            edt_cpass.requestFocus();
            return true;
        }

        return false;
    }

    public boolean isCheckEmptyField(EditText edt_txt)
    {
        if (TextUtils.isEmpty(edt_txt.getText().toString()))
        {
            edt_txt.setError("This field cannot be blank!");
            edt_txt.requestFocus();
            return true;
        }
        return false;
    }


    public void createUser(String name,String email,String pass)
    {
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    FirebaseUser user = auth.getCurrentUser();
                    FirebaseFirestore db = FirebaseFirestore.getInstance();

                    

//                    db.collection("users").document(user.getUid())


                }else
                {
                    Log.d("From Register",task.getException().toString());
                    Toast.makeText(getActivity().getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

}

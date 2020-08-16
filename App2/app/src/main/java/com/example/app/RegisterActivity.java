package com.example.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail_Register;
    private EditText etPassword_Register;
    private Button btConfirm_Register;

    private FirebaseAuth mAuth; //m: member

    private String strEmail_Register;
    private String strPassword_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail_Register = (EditText) findViewById(R.id.etEmail_Register);
        etPassword_Register = (EditText) findViewById(R.id.etPassword_Register);
        btConfirm_Register = (Button) findViewById(R.id.btConfirm_Register);
        mAuth = FirebaseAuth.getInstance();


        btConfirm_Register.setOnClickListener(new View.OnClickListener() {
            // if - else 고유한 이메일인지 아닌지 이거는 서버에서 판단
            @Override
            public void onClick(View v) {
                strEmail_Register = etEmail_Register.getText().toString();
                strPassword_Register = etPassword_Register.getText().toString();

                mAuth.createUserWithEmailAndPassword(strEmail_Register, strPassword_Register)
                        .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}

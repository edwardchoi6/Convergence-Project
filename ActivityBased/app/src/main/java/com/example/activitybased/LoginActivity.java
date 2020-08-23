package com.example.activitybased;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btLogin;
    private Button btRegister;
    private FirebaseAuth mAuth;

    //debugging
    public static final String TAG = "test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //Perform initialization of all fragments
        setContentView(R.layout.activity_login); //R: 프로젝트에 관련된 요소들을 구분하기 위한 메모리에 저장된 주소값들을 저장해 놓은 곳

        //선언한 변수들에게 값을 넣어줘야 되는데 java파일과 activity의 view와 연관이 안되어 있어서 연결해주는 역할 (id값을 가지고)
        etEmail = (EditText) findViewById(R.id.etEmail); //id.(여기에 EditText의 id와 같은 것을 넣음)
        etPassword = (EditText) findViewById(R.id.etPassword);
        btLogin = (Button) findViewById(R.id.btLogin);
        btRegister = (Button) findViewById(R.id.btRegister);

        mAuth = FirebaseAuth.getInstance();

        //버튼을 눌렀을 때 어떻게 동작을 시키겠다 (가장 기본적인 화면 전환)
        btLogin.setOnClickListener(new View.OnClickListener() {//사용자가 버튼을 언제 누를 지 모르니깐 화면에서 계속 트래킹을 하고 있는데 setOnClickListener는 한 번 클릭했을 때 <어떻게> 동작을 해라 정의하는 역할
            //인증을 server에서 처리할 것이기 때문에 front-hand에 해당하는 android studio에서는 처리만 하면 된다. (서버에서 정보를 받아 DB에 검색 후 정보가 일치하는지를 알려준 다음 내려준다)
            @Override
            public void onClick(View v) {
                //사용자가 입력한 값 저장 (EditText에서 사용자가 입력한다)
                String strEmail = etEmail.getText().toString(); //EditText는 수정 가능한 텍스트이므로 따른 자료형을 가지고 있기 때문에 형변환 해야됨
                String strPassword = etPassword.getText().toString();
                Log.d(TAG, strEmail);
                Log.d(TAG, strPassword);

                mAuth.signInWithEmailAndPassword(strEmail, strPassword)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(LoginActivity.this, "Login Failed !!!",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                //server에서 있는지 확인
                /*
                if(authenticate(strEmail, strPassword)) {
                    //Intent: 화면의 구성 요소들을 전달하는 <메세지 개체> (A -> B로 전환될 때 각 화면 간에 주고 받는 요소가 있는데 Intent라는 객체를 이용하여 이를 해결함)
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    System.out.println("로그인에 실패하였습니다.");
                }
                */


            }
        });

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }
    /*
    public boolean authenticate (String id, String pwd) {
        String responseID = "qwert";
        String responsePWD = "1234";

        if (id.equals(responseID) && pwd.equals(responsePWD)) {
            return true;
        }
        return false;
    }*/

}

package com.example.asueats.View;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.asueats.Model.User;
import com.example.asueats.R;
import com.example.asueats.ViewModel.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    Button sv_signup_btn;
    EditText sv_email_et, sv_pass_et, sv_confirmpass_et;
    private UserViewModel mUserViewModel;
    Pattern pattern;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_view);
        sv_signup_btn = findViewById(R.id.sv_signup_btn);
        sv_email_et = findViewById(R.id.sv_email_et);
        sv_pass_et = findViewById(R.id.sv_pass_et);
        sv_confirmpass_et = findViewById(R.id.sv_confirmpass_et);
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        pattern = Pattern.compile(regex);

        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sv_signup_btn.setOnClickListener(view -> {
            String email = sv_email_et.getText().toString();
            String pass = sv_pass_et.getText().toString();
            String confirm = sv_confirmpass_et.getText().toString();
            Matcher matcher = pattern.matcher(email);
            if(matcher.matches()){
                if(!pass.isEmpty() && pass.equals(confirm)){
                    mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(SignupActivity.this, "Registered Successfully",Toast.LENGTH_LONG).show();
                            mUserViewModel.insert(new User(email, pass));
                        }else{
                            Log.d("yehiaDebug","Registration error");
                        }
                    });
                    finish();
                }
                else{
                    Toast.makeText(getApplicationContext(),"not matching password\n" + pass + ", " + confirm, Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getApplicationContext(),"invalid Email format", Toast.LENGTH_LONG).show();
            }
        });
    }
}
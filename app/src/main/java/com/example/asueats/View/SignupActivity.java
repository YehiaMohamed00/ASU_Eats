package com.example.asueats.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asueats.Model.User;
import com.example.asueats.R;
import com.example.asueats.ViewModel.UserViewModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {

    Button sv_signup_btn;
    EditText sv_email_et, sv_pass_et, sv_confirmpass_et;
    private UserViewModel mUserViewModel;
    Pattern pattern;

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
//        mUserViewModel.getAllUsers().observe(this, user -> {
//            // Update the cached copy of the words in the adapter.
//            //adapter.submitList(words);
//            if (user == null){
//                //lv_login_btn.callOnClick();
//                Toast.makeText(
//                        getApplicationContext(),
//                        "User not registered",
//                        Toast.LENGTH_LONG).show();
//            }else{
//                Toast.makeText(getApplicationContext(),"tmm", Toast.LENGTH_LONG).show();
//            }
//        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sv_signup_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = sv_email_et.getText().toString();
                String pass = sv_pass_et.getText().toString();
                String confirm = sv_confirmpass_et.getText().toString();
                Matcher matcher = pattern.matcher(email);
                if(matcher.matches()){
                    if(pass.equals(confirm)){
                        mUserViewModel.insert(new User(email, pass));
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"not matching password\n" + pass + ", " + confirm, Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"invalid Email format", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
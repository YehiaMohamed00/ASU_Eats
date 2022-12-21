package com.example.asueats.View;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.asueats.Model.User;
import com.example.asueats.R;
import com.example.asueats.ViewModel.UserViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {

    TextView lv_signup_tv;
    Button lv_login_btn;
    EditText lv_email_et, lv_pass_et;
    private UserViewModel mUserViewModel;
    Pattern pattern;
    FirebaseAuth mAuth;
//    ProfileRoomDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_view);
        lv_signup_tv = findViewById(R.id.lv_signup_tv);
        lv_login_btn = findViewById(R.id.lv_login_btn);
        lv_email_et = findViewById(R.id.lv_email_et);
        lv_pass_et = findViewById(R.id.lv_pass_et);

        mAuth = FirebaseAuth.getInstance();
        new UserViewModel(getApplication());

        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        pattern = Pattern.compile(regex);

        mUserViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        mUserViewModel.getAllUsers().observe(this, user -> {
//            // Update the cached copy of the words in the adapter.
//            //adapter.submitList(words);
//            if (user == null){
//                Toast.makeText(
//                        getApplicationContext(),
//                        "No Users registered",
//                        Toast.LENGTH_LONG).show();
//            }else
//            {
////                String tmp = lv_signup_tv.getText().toString() + "  " + user.size();
////                lv_signup_tv.setText(tmp);
//            }
//        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        lv_signup_tv.setOnClickListener(view -> {
            Intent i = new Intent(getApplicationContext(), SignupActivity.class);
            startActivity(i);
        });

        lv_login_btn.setOnClickListener(view -> {
            String email = lv_email_et.getText().toString();
            String password = lv_pass_et.getText().toString();
            Matcher matcher = pattern.matcher(email);
            if(!password.isEmpty()){
                if(matcher.matches()){
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                        if(task.isSuccessful()){
                            Toast.makeText(LoginActivity.this, "Logged in Successfully",Toast.LENGTH_LONG).show();
                            Intent i = new Intent(getApplicationContext(), RestaurantsActivity.class);
                            i.putExtra("email", email);
                            i.putExtra("password", password);
//                            Log.d("notice", email + "  " + password);
                            startActivity(i);
                        }else{
                            Toast.makeText(LoginActivity.this, "Login error",Toast.LENGTH_LONG).show();
                        }
                    });
//                    new Thread(() -> {
////                        Toast.makeText(getApplicationContext(),"the thread runs", Toast.LENGTH_LONG).show();
//                        User user = mUserViewModel.getUser(email, password);
//                        if(user == null){
//                            runOnUiThread(() -> Toast.makeText(getApplicationContext(),"Invalid Credentials", Toast.LENGTH_LONG).show());
//                        }else{
//                            Intent i = new Intent(getApplicationContext(), RestaurantsActivity.class);
//                            i.putExtra("email", email);
//                            i.putExtra("password", password);
//                            Log.d("notice", email + "  " + password);
//                            startActivity(i);
//                        }
//                    }).start();
                }
                else{
                    Toast.makeText(getApplicationContext(),"invalid Email format", Toast.LENGTH_LONG).show();
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"password can't be empty", Toast.LENGTH_LONG).show();
            }

//                LiveData<User> tmp = mUserViewModel.getUser(email);
//                Toast.makeText(getApplicationContext(),tmp. + "  " + tmp.getPassword(), Toast.LENGTH_LONG).show();

        });

    }
}
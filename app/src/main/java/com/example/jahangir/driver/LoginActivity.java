package com.example.jahangir.driver;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    TextInputEditText usernameEditText;
    TextInputEditText passwordEditText;
    Button loginButton;
    Button signupButton;
    TextInputLayout inputLayoutUsername;
    TextInputLayout inputLayoutPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        usernameEditText = (TextInputEditText) findViewById(R.id.edit_text_username);
        passwordEditText = (TextInputEditText) findViewById(R.id.edit_text_password);
        inputLayoutUsername= (TextInputLayout) findViewById(R.id.input_layout_username);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        loginButton = (Button) findViewById(R.id.button_login);
        signupButton = (Button) findViewById(R.id.button_signup);
        loginButton.setOnClickListener(this);
        signupButton.setOnClickListener(this);
        setTitle("Login");
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login:
                Login();
                break;
            case R.id.button_signup:
                startActivity(this, SignupActivity.class,false);
                break;
        }

    }
    public  void startActivity(Activity context, Class<?> class_, boolean isFinish) {
        Intent intent = new Intent(context, class_);
        context.startActivity(intent);
        if (isFinish)
            context.finish();
    }
    public void Login(){
        if (usernameEditText.getText().toString().length() < 5) {
            inputLayoutUsername.setErrorEnabled(true);
            inputLayoutUsername.setError("Invalid Username");
            return;
        }
        inputLayoutUsername.setError(null);
        inputLayoutUsername.setErrorEnabled(false);
        if (passwordEditText.getText().toString().length() < 5) {
            inputLayoutPassword.setErrorEnabled(true);
            inputLayoutPassword.setError("Invalid Password");
            return;
        }
        inputLayoutPassword.setError(null);
        inputLayoutPassword.setErrorEnabled(false);
        User tempUser = getUser(this);
        if(tempUser.username.equals(usernameEditText.getText().toString()) &&
                tempUser.password.equals(passwordEditText.getText().toString())){
            //login
            startActivity(this, MainActivity.class,true);
            loginDriver(this);
        }
        else
            Toast.makeText(this,"Invalid username or password",Toast.LENGTH_SHORT).show();

    }
    public static User getUser(Context context) {
        return GsonUtils.fromJson(PrefUtils.getString(context, Constants.USER), User.class);
    }
    public static void loginDriver(Context context){
        PrefUtils.persistBoolean(context,Constants.USER_DRIVER_LOGIN,true);
    }
}

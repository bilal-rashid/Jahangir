package com.example.jahangir.driver;

import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener  {

    TextInputEditText usernameEditText;
    TextInputEditText passwordEditText;
    TextInputEditText contactEditText;
    Button signupButton;
    TextInputLayout inputLayoutUsername;
    TextInputLayout inputLayoutPassword;
    TextInputLayout inputLayoutContact;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Signup");
        usernameEditText = (TextInputEditText) findViewById(R.id.edit_text_username);
        passwordEditText = (TextInputEditText) findViewById(R.id.edit_text_password);
        contactEditText= (TextInputEditText) findViewById(R.id.edit_text_emp_code);
        inputLayoutUsername= (TextInputLayout) findViewById(R.id.input_layout_username);
        inputLayoutPassword = (TextInputLayout) findViewById(R.id.input_layout_password);
        inputLayoutContact = (TextInputLayout) findViewById(R.id.input_layout_emp_code);
        signupButton = (Button) findViewById(R.id.button_signup);
        signupButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_signup:
                Signup();
                break;
        }

    }
    public void Signup(){
        if (contactEditText.getText().toString().length() < 1) {
            inputLayoutContact.setErrorEnabled(true);
            inputLayoutContact.setError("Please Enter Employee code");
            return;
        }
        inputLayoutContact.setError(null);
        inputLayoutContact.setErrorEnabled(false);
        if (usernameEditText.getText().toString().length() < 5) {
            inputLayoutUsername.setErrorEnabled(true);
            inputLayoutUsername.setError("Username must be at least 5 characters long");
            return;
        }
        inputLayoutUsername.setError(null);
        inputLayoutUsername.setErrorEnabled(false);
        if (passwordEditText.getText().toString().length() < 5) {
            inputLayoutPassword.setErrorEnabled(true);
            inputLayoutPassword.setError("Password must be at least 5 characters long");
            return;
        }
        inputLayoutPassword.setError(null);
        inputLayoutPassword.setErrorEnabled(false);
        User user= new User(usernameEditText.getText().toString(),
                passwordEditText.getText().toString(),
                contactEditText.getText().toString());
        saveUser(this,user);
        this.finish();

    }
    public static void saveUser(Context context, User user) {
        if (user == null)
            return;
        PrefUtils.persistString(context, Constants.USER, GsonUtils.toJson(user));
    }
}

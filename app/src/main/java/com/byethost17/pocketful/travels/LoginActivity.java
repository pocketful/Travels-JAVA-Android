package com.byethost17.pocketful.travels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {
    private EditText username;
    private EditText password;
    private String username_txt;
    private String password_txt;
    private Button buttonLogin;
    private Button buttonRegister;
    private Button buttonReset;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Authentication();
        RegisterButton();
        ResetButton();
    }

    public void Authentication(){

        buttonLogin = (Button)findViewById(R.id.login);
        username = (EditText)findViewById(R.id.username);
        password = (EditText)findViewById(R.id.password);
        rememberMeCheckBox = (CheckBox)findViewById(R.id.remember_me);

        final Register user = new Register(getApplicationContext());

        rememberMeCheckBox.setChecked(user.isRemembered());

        if (user.isRemembered()) {
            username.setText(user.getUsername(), TextView.BufferType.EDITABLE);
            password.setText(user.getPassword(), TextView.BufferType.EDITABLE);
        }
        else {
            username.setText("", TextView.BufferType.EDITABLE);
            password.setText("", TextView.BufferType.EDITABLE);
        }

        username.setError(null);
        password.setError(null);

        Button buttonLogin = (Button) findViewById(R.id.login);
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
             public void onClick(View view) {
                    username_txt=username.getText().toString();
                    password_txt=password.getText().toString();

                    boolean cancel = false;
                    View focusView = null;
                    if (!isValid(username_txt)) {
                        username.setError(getString(R.string.login_username_error_wrong));
                        focusView=username;
                        cancel=true;
                    }
                    if (!isValid(password_txt)) {
                        password.setError(getString(R.string.login_password_error_wrong));
                        focusView=password;
                        cancel = true;
                    }
                    if (cancel) {
                        focusView.requestFocus();
                    }

                    else {
                        if (rememberMeCheckBox.isChecked()) {
                            user.setUsername(username_txt);
                            user.setPassword(password_txt);
                            user.setRemembered(true);
                        } else {
                            user.setUsername("");
                            user.setPassword("");
                            user.setRemembered(false);
                        }

                        Intent GoToMenuActivity = new Intent(LoginActivity.this, MenuActivity.class);
                        startActivity(GoToMenuActivity);
                    }
             }
        });
    }

    public void RegisterButton() {
        Button buttonRegister = (Button) findViewById(R.id.register);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToRegisterActivity = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(GoToRegisterActivity);
            }
        });
    }

    public void ResetButton() {
        Button buttonReset = (Button) findViewById(R.id.reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToResetActivity = new Intent(LoginActivity.this, ResetActivity.class);
                startActivity(GoToResetActivity);
            }
        });
    }

    private boolean isValid(String credentials) {
        final String CREDENTIALS_PATTERN = "^([0-9a-zA-z]{3,15})+$";
        Pattern pattern = Pattern.compile(CREDENTIALS_PATTERN);

        Matcher matcher = pattern.matcher(credentials);
        return matcher.matches();
    }
}
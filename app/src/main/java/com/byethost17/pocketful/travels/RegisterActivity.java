package com.byethost17.pocketful.travels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends Activity {

    private EditText username;
    private EditText password;
    private EditText password2;
    private EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        password2 = (EditText) findViewById(R.id.password2);
        email = (EditText) findViewById(R.id.email);

        username.setError(null);
        password.setError(null);
        password2.setError(null);
        email.setError(null);

        Button buttonRegisterCreate = (Button) findViewById(R.id.register_submit);
        buttonRegisterCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    boolean cancel = false;
                    View focusView = null;

                if (!password.getText().toString().equals(password2.getText().toString())) {
                    password.setError(getString(R.string.register_password3_error));
                    focusView = password2;
                    cancel = true;
                }

                if (TextUtils.isEmpty(username.getText().toString())) {
                    username.setError(getString(R.string.register_username_error));
                    focusView = username;
                    cancel = true;
                }

                if (TextUtils.isEmpty(password.getText().toString())) {
                    password.setError(getString(R.string.register_password_error));
                    focusView = password;
                    cancel = true;
                }

                if (TextUtils.isEmpty(password2.getText().toString())) {
                    password2.setError(getString(R.string.register_password2_error));
                    focusView = password2;
                    password2.setText("");
                    cancel = true;
                }

                if (!isValid(email.getText().toString())) {
                    email.setError(getString(R.string.register_email_error));
                    focusView = email;
                    cancel = true;
                }

                if (cancel) {
                    focusView.requestFocus();
                }

                else {
                    Register register = new Register(username.getText().toString(),
                            password.getText().toString(),
                            password2.getText().toString(),
                            email.getText().toString());

                            Toast.makeText(RegisterActivity.this,
                                    register.getUsername() + "\n" +
                                    register.getPassword() + "\n" +
                                    register.getPassword2() + "\n" +
                                    register.getEmail(), Toast.LENGTH_SHORT).show();

                    Intent GoToLoginActivity = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(GoToLoginActivity);
                }
            }
        });
    }

    private boolean isValid (String email) {

        final String EMAIL_PATTERN = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);

        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
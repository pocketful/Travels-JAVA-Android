package com.byethost17.pocketful.travels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ResetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        Button buttonResetSubmit = (Button) findViewById(R.id.reset_submit);
        buttonResetSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToLoginActivity = new Intent(ResetActivity.this, LoginActivity.class);
                startActivity(GoToLoginActivity);
            }
        });
    }
}

package com.byethost17.pocketful.travels;

import android.content.Intent;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditUserActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        Button buttonEditUserSubmit = (Button) findViewById(R.id.edituser_submit);
        buttonEditUserSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToMenuActivity = new Intent(EditUserActivity.this, MenuActivity.class);
                startActivity(GoToMenuActivity);
            }
        });

    }
}

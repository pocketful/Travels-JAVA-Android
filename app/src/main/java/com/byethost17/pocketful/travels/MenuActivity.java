package com.byethost17.pocketful.travels;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button buttonNewEntry = (Button) findViewById(R.id.new_entry);
        buttonNewEntry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToNewEntryActivity = new Intent(MenuActivity.this, NewEntryActivity.class);
                startActivity(GoToNewEntryActivity);
            }
        });

        Button buttonSearch = (Button) findViewById(R.id.search);
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToSearchActivity = new Intent(MenuActivity.this, SearchActivity.class);
                startActivity(GoToSearchActivity);
            }
        });

        Button buttonEditUser = (Button) findViewById(R.id.edituser);
        buttonEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GoToEditUserActivity = new Intent(MenuActivity.this, EditUserActivity.class);
                startActivity(GoToEditUserActivity);
            }
        });
    }
}
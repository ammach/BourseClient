package com.example.ammach.bourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActionsAdminActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions_admin);

        getSupportActionBar().setTitle("Liste des Actions");
    }
}

package com.example.ammach.bourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class AddActionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);

        getSupportActionBar().setTitle("AJOUTER UNE ACTION");
    }

    public void ajouter(View view) {
        Toast.makeText(AddActionActivity.this, "ajouter", Toast.LENGTH_SHORT).show();
    }
}

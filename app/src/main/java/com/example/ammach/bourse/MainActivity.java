package com.example.ammach.bourse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    Switch aswitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aswitch=(Switch) findViewById(R.id.switch1);

    }


    ///////////////////////////////////////////////////////////////////////////
    // swith entre utilisateur et admin
    ///////////////////////////////////////////////////////////////////////////
    public void entrer(View view) {
        if(aswitch.isChecked())
        {
            startActivity(new Intent(MainActivity.this,AdminActivity.class));
        }
        else
        {
            startActivity(new Intent(MainActivity.this,UserActivity.class));
        }
    }
}

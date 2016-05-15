package com.example.ammach.bourse;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

import helpers.ClientConnecte;

public class AdminActivity extends AppCompatActivity {
    Switch mswitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        getSupportActionBar().setTitle("Réglages");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addAction);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AdminActivity.this,AddActionActivity.class));
            }
        });

        mswitch=(Switch) findViewById(R.id.switchChange);
    }

    public void valider(View view) {
        if(mswitch.isChecked())
        {
            //startActivity(new Intent(AdminActivity.this,ActionsAdminActivity.class));
            ClientConnecte client=new ClientConnecte();
            client.connexion("192.168.1.6", 40000);
            client.envoi("");
        }
        else
        {
            Toast.makeText(AdminActivity.this, "mode aléatoire", Toast.LENGTH_SHORT).show();
        }
    }
}

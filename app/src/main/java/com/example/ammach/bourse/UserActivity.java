package com.example.ammach.bourse;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classes.Client;
import helpers.ClientConnecte;
import helpers.ServeurConnecte;

public class UserActivity extends AppCompatActivity {

    class Task extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            String name =params[0];
            String pass =params[1];
            Client c=new Client(name,pass);
            ClientConnecte client=new ClientConnecte();
            client.connexion("192.168.1.5", 40000);
            client.envoiObject(c);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(UserActivity.this, "verification en cours", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(UserActivity.this,UserDetailActivity.class);
            intent.putExtra("name",editName.getText().toString());
            intent.putExtra("password",editPass.getText().toString());
            startActivity(intent);
        }
    }



    EditText editName;
    EditText editPass;

    public static Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editName= (EditText) findViewById(R.id.editUserName);
        editPass= (EditText) findViewById(R.id.editPass);
    }

    public void connecter(View view) {
        final String name=editName.getText().toString();
        final String pass=editPass.getText().toString();
        if (name.equals("") || pass.equals("") ){
        Toast.makeText(UserActivity.this, "veuillez remplir vos données", Toast.LENGTH_SHORT).show();
        }
        else{
            new Task().execute(name,pass);
            new ServeurConnecte();
            handler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                        Toast.makeText(UserActivity.this,msg.obj.toString(), Toast.LENGTH_SHORT).show();
                }
            };
        }
    }
}

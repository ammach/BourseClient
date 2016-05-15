package com.example.ammach.bourse;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classes.Action;
import helpers.ClientConnecte;

public class AddActionActivity extends AppCompatActivity {

    class Task extends AsyncTask<String,Void,Void> {

        @Override
        protected Void doInBackground(String... params) {
            String nomAction =params[0];
            String valeurAction =params[1];
            Action action=new Action(nomAction,valeurAction);
            if (action!=null){
                ClientConnecte client=new ClientConnecte();
                client.connexion("192.168.3.28", 50000);
                client.envoiObject(action);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(AddActionActivity.this, "envoi en cours", Toast.LENGTH_SHORT).show();
        }
    }




    EditText editNomAction;
    EditText editValeurAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_action);

        getSupportActionBar().setTitle("AJOUTER UNE ACTION");

        editNomAction= (EditText) findViewById(R.id.editNomAction);
        editValeurAction= (EditText) findViewById(R.id.editValeurAction);
    }

    public void ajouter(View view) {
       String nomAction=editNomAction.getText().toString();
        String valeurAction=editValeurAction.getText().toString();
        if (nomAction.equals("") || valeurAction.equals("")){
            Toast.makeText(AddActionActivity.this,"veuillez remplir les données", Toast.LENGTH_SHORT).show();
          }
        else{
            new Task().execute(nomAction,valeurAction);
        }
    }
}

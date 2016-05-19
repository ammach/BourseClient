package com.example.ammach.bourse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import classes.Action;
import helpers.ServeurAction;

public class ActionsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent=new Intent(this,DetailActionActivity.class);
        intent.putExtra("nameAction",actions.get(position).getNom());
        intent.putExtra("valAction",actions.get(position).getValeur());
        intent.putExtra("position",position);
        startActivity(intent);
    }

    ///////////////////////////////////////////////////////////////////////////
    // adapter de la liste des actions
    ///////////////////////////////////////////////////////////////////////////
    class MyAdapter extends BaseAdapter {

        ArrayList<Action> actions;
        public MyAdapter(ArrayList<Action> actionsPasse) {
            this.actions=actionsPasse;
        }

        @Override
        public int getCount() {
            return actions.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null) {
                LayoutInflater inflater=(LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                convertView=inflater.inflate(R.layout.row_actions, null);
            }
            TextView txtNom=(TextView) convertView.findViewById(R.id.txtRowNomAction);
            TextView txtValeur=(TextView) convertView.findViewById(R.id.txtRowValAction);

            txtNom.setText(actions.get(position).getNom());
            txtValeur.setText(actions.get(position).getValeur());

            return convertView;
        }

        public ArrayList<Action> getActions() {
            return actions;
        }

        public void setActions(ArrayList<Action> actions) {
            this.actions = actions;
        }
    }


    ListView listview;
    MyAdapter adapter;
    public static Handler handler;
    ArrayList<Action> actions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //ecoute de reponse du serveur liste des actions
        new ServeurAction();
        actions=new ArrayList<>();
        actions.add(new Action("MAZI","attente en cours..."));
        actions.add(new Action("ATLANTA","attente en cours..."));
        actions.add(new Action("AUTO HALL","attente en cours..."));
        actions.add(new Action("AXA","attente en cours..."));

        listview=(ListView) findViewById(R.id.listview_actions);
        adapter=new MyAdapter(actions);

        //attente de la liste des actions pour l'afficher
        handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                ArrayList<String> valeurs= (ArrayList<String>) msg.obj;
                if (valeurs.get(0).equals("action")){
                for (int i = 0; i <actions.size() ; i++) {
                    actions.get(i).setValeur(valeurs.get(i+1)+" %");
                   }
                }
                adapter.setActions(actions);
                getAdapter().notifyDataSetChanged();
            }
        };
        listview.setAdapter(adapter);
        listview.setOnItemClickListener(this);
    }

    public MyAdapter getAdapter() {
        return adapter;
    }
}

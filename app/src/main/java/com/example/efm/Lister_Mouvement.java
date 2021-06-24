package com.example.efm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class Lister_Mouvement extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lister__mouvement);
        listView=findViewById(R.id.listMouvement);
        Database db=new Database(this);
        ArrayList<Mouvement> mouvements= db.getAllMouvements();
        MouvementAdapter adapter=new MouvementAdapter(this,mouvements);
        listView.setAdapter(adapter);
    }
}
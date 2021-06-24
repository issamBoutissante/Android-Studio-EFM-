package com.example.efm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void ajouterMouvement(View view) {
        Intent intentAjout=new Intent(this,Ajouter_Mouvement.class);
        startActivity(intentAjout);
    }

    public void moudifierMouvement(View view) {
        Intent intentMoudification=new Intent(this,Moudifier_Mouvement.class);
        startActivity(intentMoudification);
    }

    public void supprimerMouvement(View view) {
        Intent intentSupprission=new Intent(this,Supprimer_Mouvement.class);
        startActivity(intentSupprission);
    }

    public void listerMouvement(View view) {
        Intent intentList=new Intent(this,Lister_Mouvement.class);
        startActivity(intentList);
    }
}
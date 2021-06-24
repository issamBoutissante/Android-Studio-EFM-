package com.example.efm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Ajouter_Mouvement extends AppCompatActivity {
    EditText idEd,articleEd,quantiteEd;
    CheckBox typeMouvementChx;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter__mouvement);
        idEd=findViewById(R.id.idEd);
        articleEd=findViewById(R.id.articleEd);
        quantiteEd=findViewById(R.id.quantiteEd);
        typeMouvementChx=findViewById(R.id.typeMouvementChx);
    }

    public void ajouterMouvement(View view) {
        if(TextUtils.isEmpty(idEd.getText().toString())){
            showToast("remplir id");
            return;
        }
        if(TextUtils.isEmpty(articleEd.getText().toString())){
            showToast("remplir article");
            return;
        }
        if(TextUtils.isEmpty(quantiteEd.getText().toString())){
            showToast("remplir quantite");
            return;
        }
        Database db=new Database(this);
        Integer id=Integer.parseInt(idEd.getText().toString());
        String ariticle=articleEd.getText().toString();
        Float quantite=Float.parseFloat(quantiteEd.getText().toString());
        Boolean typeMouvement=typeMouvementChx.isChecked();
        Mouvement mouvement=new Mouvement(id,ariticle,quantite,typeMouvement);
        db.ajouter(mouvement);
    }

    public void backToMenu(View view) {
        finish();
    }
    public void  showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void changeCheck(View view) {
        if(typeMouvementChx.isChecked()){
            typeMouvementChx.setText("sortie de stock");
        }else {
            typeMouvementChx.setText("entree de stock");
        }
    }
}
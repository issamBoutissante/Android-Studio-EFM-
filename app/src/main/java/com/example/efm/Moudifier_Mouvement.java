package com.example.efm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Moudifier_Mouvement extends AppCompatActivity {
    EditText idEd,articleEd,quantiteEd;
    CheckBox typeMouvementChx;
    Database db;
    Integer id=-1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moudifier__mouvement);
        db=new Database(this);
        idEd=findViewById(R.id.idEd);
        articleEd=findViewById(R.id.articleEd);
        quantiteEd=findViewById(R.id.quantiteEd);
        typeMouvementChx=findViewById(R.id.typeMouvementChx);
    }

    public void rechercheMouvement(View view) {
        if(TextUtils.isEmpty(idEd.getText().toString())){
            showToast("remplir id");
            return;
        }
        id=Integer.parseInt(idEd.getText().toString());
        Mouvement mouvement= db.rechercher(id);
        if(mouvement==null){
            showToast("id nest pas exist");
            id=-1;
            return;
        }
        articleEd.setText(mouvement.article.toString());
        quantiteEd.setText(mouvement.quatite.toString());
        if(mouvement.typeMouvement){
            typeMouvementChx.setChecked(true);
        }else {
            typeMouvementChx.setChecked(false);
        }
        changeCheck(typeMouvementChx);
    }

    public void moudifierMouvement(View view) {
        if(id==-1){
            showToast("il faut chercher");
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
        String ariticle=articleEd.getText().toString();
        Float quantite=Float.parseFloat(quantiteEd.getText().toString());
        Boolean typeMouvement=typeMouvementChx.isChecked();
        Mouvement mouvement=new Mouvement(id,ariticle,quantite,typeMouvement);
        db.modifier(mouvement);
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
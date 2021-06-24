package com.example.efm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Supprimer_Mouvement extends AppCompatActivity {
    EditText idEd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supprimer__mouvement);
        idEd=findViewById(R.id.idEd);
    }

    public void supprimerMouvement(View view) {
        Database db=new Database(this);
        if(TextUtils.isEmpty(idEd.getText().toString())){
            showToast("remplir id");
            return;
        }
        Integer id=Integer.parseInt(idEd.getText().toString());
        if(!db.isMouvementExists(id)){
            showToast("id nest pas exist");
            return;
        }
        db.supprimer(id);
    }
    public void  showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void backToMenu(View view) {
        finish();
    }
}
package com.example.efm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MouvementAdapter extends BaseAdapter {
    Context context;
    ArrayList<Mouvement> mouvements;
    MouvementAdapter (Context context, ArrayList<Mouvement> m){
        this.context=context;
        this.mouvements=m;
    }
    @Override
    public int getCount() {
        return mouvements.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.mouvement_list_item,parent,false);
        }
        TextView idEd,articleEd,quantiteEd;
        CheckBox typeMouvementChx;
        idEd=convertView.findViewById(R.id.idEd);
        articleEd=convertView.findViewById(R.id.articleEd);
        quantiteEd=convertView.findViewById(R.id.quantiteEd);
        typeMouvementChx=convertView.findViewById(R.id.typeCh);
        Mouvement m=mouvements.get(position);
        idEd.setText(m.id.toString());
        articleEd.setText(m.article.toString());
        quantiteEd.setText(m.quatite.toString());
        if (m.typeMouvement) {
            typeMouvementChx.setChecked(true);
            typeMouvementChx.setText("sortie de stock");
        }else {
            typeMouvementChx.setChecked(false);
            typeMouvementChx.setText("entree de stock");

        }
        return convertView;
    }
}

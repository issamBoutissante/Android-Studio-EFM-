package com.example.efm;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    Context context;
    public Database(Context context) {
        super(context, "GestionMouvement", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table mouvement (id integer primary key," +
                "article text," +
                "quantite real," +
                "typeMouvement int)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void ajouter(Mouvement mouvement){
        SQLiteDatabase db=getWritableDatabase();
        //check if exists
        if(isMouvementExists(mouvement.id)){
            showToast("id mouvement deja exists");
            return;
        }
        ContentValues values=new ContentValues();
        values.put("id",mouvement.id.toString());
        values.put("article",mouvement.article);
        values.put("quantite",mouvement.quatite);
        if(mouvement.typeMouvement){
            values.put("typeMouvement",1);
        }else {
            values.put("typeMouvement",0);
        }
        try{
            db.insert("mouvement",null,values);
            showToast("ajout a ete effectue");
        }catch (Exception e){
            showToast(e.getMessage());
        }

    }
    public ArrayList<Mouvement> getAllMouvements(){
        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from mouvement", null);
        ArrayList<Mouvement> mouvements=new ArrayList<>();
        if(cursor!=null){
            if(cursor.moveToFirst()){
                do{
                    Integer id=cursor.getInt(0);
                    String article=cursor.getString(1);
                    Float quatite=cursor.getFloat(2);
                    boolean typeMouvement;
                    if(cursor.getInt(3)==1){
                        typeMouvement=true;
                    }else {
                        typeMouvement=false;
                    }
                    mouvements.add(new Mouvement(id,article,quatite,typeMouvement));
                }while(cursor.moveToNext());
                cursor.close();
            }
        }
        return mouvements;
    }
    public Mouvement rechercher(Integer idMouvement){
        Mouvement mouvement=new Mouvement();
        SQLiteDatabase db=getWritableDatabase();
        if(!isMouvementExists(idMouvement)){
            showToast("id not exist");
            return null;
        }
        Cursor cursor=db.rawQuery("select * from mouvement where id=?",new String[]{idMouvement.toString()});
        if(cursor!=null){
            if(cursor.moveToFirst()){
                mouvement.id=cursor.getInt(0);
                mouvement.article=cursor.getString(1);
                mouvement.quatite=cursor.getFloat(2);
                if(cursor.getInt(3)==1){
                    mouvement.typeMouvement=true;
                }else {
                    mouvement.typeMouvement=false;
                }
            }
            cursor.close();
        }
        return mouvement;
    }
    public void modifier(Mouvement mouvement){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("article",mouvement.article);
        values.put("quantite",mouvement.quatite);
        if(mouvement.typeMouvement){
            values.put("typeMouvement","1");
        }else {
            values.put("typeMouvement","0");
        }
        try{
            db.update("mouvement",values,"id=?",new String[]{mouvement.id.toString()});
            showToast("modification a ete effectue");
        }catch (Exception e){
            showToast(e.getMessage());
        }
    }
    public void supprimer(Integer id){
        SQLiteDatabase db=getWritableDatabase();
        try{
            db.delete("mouvement","id=?",new String[]{id.toString()});
            showToast("supprission a ete effectue");
        }catch (Exception e){
            showToast(e.getMessage());
        }
    }
    public boolean isMouvementExists(Integer idMouvement){
        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from mouvement where id=?",new String[]{idMouvement.toString()});
        if(cursor!=null){
            if(cursor.moveToFirst()){
                return true;
            }
            cursor.close();
        }
        return false;
    }
    public void  showToast(String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

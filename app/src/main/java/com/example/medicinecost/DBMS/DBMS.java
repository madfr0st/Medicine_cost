package com.example.medicinecost.DBMS;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.medicinecost.MainActivity;
import com.example.medicinecost.Medicine;

import java.util.ArrayList;

public class DBMS extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "medicine_database.db";
    public static final String TABLE = "all_medicine";
    public static final String NAME = "medicine_name";
    public static final String CODE ="medicine_code";
    public static final String DIS = "medicine_dis";
    public static final String EACH = "medicine_each";
    public static final String COST = "medicine_cost";

    public DBMS(Context context) {
        super(context, DATABASE_NAME , null, 1);
    }

    public DBMS(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DBMS(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @RequiresApi(api = Build.VERSION_CODES.P)
    public DBMS(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }



    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists "+ TABLE  +"("+ NAME+ " TEXT ,"+ CODE +" INTEGER ,"+ DIS +" TEXT ," +EACH +" INTEGER ,"+ COST+" TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE);
        onCreate(db);
    }

    public boolean insertMedicine (String name, int code, String dis, int each,String cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(CODE, code);
        contentValues.put(DIS, dis);
        contentValues.put(EACH, each);
        contentValues.put(COST, cost);
        db.insert(TABLE, null, contentValues);
        return true;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ TABLE,null);
        return res;
    }

    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE);
        return numRows;
    }

    public boolean updateContact (String name, int code, String dis, int each,String cost) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME, name);
        contentValues.put(CODE, code);
        contentValues.put(DIS, dis);
        contentValues.put(EACH, each);
        contentValues.put(COST, cost);
        int a = db.update(TABLE, contentValues, "medicine_code LIKE ?", new String[] {String.valueOf(code)} );
        if(a<=0){
            insertMedicine(name,code, dis,each,cost);
        }

        return true;
    }

    public Integer deleteContact (Integer code) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE,
                "code = ? ",
                new String[] { Integer.toString(code) });
    }

    public ArrayList<Medicine> getAllMeDicine() {
        ArrayList<Medicine> array_list = new ArrayList<Medicine>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE, null );
        res.moveToFirst();
        while(res.isAfterLast() == false){
            array_list.add(new Medicine(res.getString(0),res.getInt(1),res.getString(2),res.getInt(3),
                    res.getString(4)));
            res.moveToNext();
        }
        return array_list;
    }
}

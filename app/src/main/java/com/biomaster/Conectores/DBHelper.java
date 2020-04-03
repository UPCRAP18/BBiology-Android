package com.biomaster.Conectores;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.biomaster.Models.User;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "bbiology";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NOMBRE = "nombre";
    private static final String COLUMN_APELLIDO = "apellido";
    private static final String COLUMN_INICIALES = "iniciales";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_TURNO = "turno";
    private static final String COLUMN_PWD = "password";
    private static final String TABLE_User = "usuario";

    private SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String Usr_Prefs = "CREATE TABLE IF NOT EXISTS " + TABLE_User +
                " ( " + COLUMN_ID + " TEXT, " +
                COLUMN_NOMBRE + " TEXT, " +
                COLUMN_APELLIDO + " TEXT, " +
                COLUMN_INICIALES + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_TURNO + " TEXT, " +
                COLUMN_PWD + " TEXT )";

        db.execSQL(Usr_Prefs);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String drop_Usr = "DROP TABLE IF EXISTS " + TABLE_User;
        db.execSQL(drop_Usr);
        onCreate(db);
    }

    private Cursor getData(String TABLE_NAME) {
        db = this.getReadableDatabase();
        Cursor res;
        res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return res;
    }


    public User getData_Usuario() {

        Cursor res = getData(TABLE_User);

        User usuario = new User();

        res.moveToFirst();

        while (!res.isAfterLast()) {
            usuario.setID(res.getString(0));
            usuario.setNombre(res.getString(1));
            usuario.setApellido(res.getString(2));
            usuario.setIniciales(res.getString(3));
            usuario.setCorreo(res.getString(4));
            usuario.setTurno(res.getString(5));
            usuario.setPassword(res.getString(6));

            res.moveToNext();
        }

        db.close();

        return usuario;

    }

    public boolean setDataUser(User user) {
        db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, user.getID());
        values.put(COLUMN_NOMBRE, user.getNombre());
        values.put(COLUMN_APELLIDO, user.getApellido());
        values.put(COLUMN_INICIALES, user.getIniciales());
        values.put(COLUMN_EMAIL, user.getCorreo());
        values.put(COLUMN_TURNO, user.getTurno());
        values.put(COLUMN_PWD, user.getPassword());

        if (db.insert(TABLE_User, null, values) != 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }

    }

    public boolean dropUsr() {
        db = getWritableDatabase();
        if (db.delete(TABLE_User, null, null) != 0) {
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

}

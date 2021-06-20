package data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.lang.reflect.Constructor;

public class MyBaseSQLite extends SQLiteOpenHelper {
    public static final String NOM_BDD = "NestiList2.db";
    public static final int VERSION_BDD = 2;
    /*Constructor
     *
    @param
    context
     *
    @param
    factory
     */

    public MyBaseSQLite(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, NOM_BDD, factory, VERSION_BDD);
    }
    /*
    Create the
    table from
    the query
    written in
    the variable
    CREATE_BDD
     *
    @param
    db
     */

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableCart.create_table(db);
        Log.i("LogNesti.onCreate", "Création de toutes les tables");
    }

    /**
     * This method executed when the database is updated
     * We can do what we want here I decided to delete the table and recreate it
     * like that when I change the version the id start from 0
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TableCart.TABLE_CART + ";");
        onCreate(db);
        Log.i("LogNesti.onUpgrade", "réinitialisation de " + NOM_BDD + "version" + oldVersion + " vers " + newVersion);
    }
}

package data.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.nesti_mes_recettes.entity.Ingredient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TableCart extends ConnexionBdd {

    protected static final String TABLE_CART = "table_cart";
    private static final String COL_ID = "id";
    private static final int NUM_COL_ID = 0;
    private static final String COL_NAME = "name";
    private static final int NUM_COL_TITRE = 1;
    private static final String COL_CHECK = "valid";
    private static final int NUM_COL_CHECK = 2;
    private String[] allColumns = { COL_ID, COL_NAME, COL_CHECK };

    /**
     * Constructor
     * @param context
     */
    public TableCart(Context context){
        super(context);
    }
    /**
     * Create a row item
     * @param item
     * @return
     */
    public long insertItem(Ingredient item){
        //Création d'un ContentValues (fonctionne comme une HashMap)
        ContentValues values = new ContentValues();
        //on lui ajoute une valeur associée à une clé (qui est le nom de la colonne dans laquelle on veut mettre la valeur)
        values.put(COL_ID, item.getId());
        //    Log.i("getname" , item.getName());
        values.put(COL_NAME, item.getName());
        values.put(COL_CHECK, 0);
        //on insère l'objet dans la BDD via le ContentValues
        Log.i("LogNesti.insert", item.toString() );
        return this.getBDD().insert(TABLE_CART, null, values);
    }
    /**
     * Return true if item is in database
     * @param id int
     * @return boolean
     */
    public boolean isFoundById(int id){
        Cursor c = this.getBDD().query(TABLE_CART, allColumns,
                COL_ID + " = " + id , null, null, null, null);
        return (c.getCount() > 0);
    }

    public int updateById(Ingredient item){
        ContentValues values = new ContentValues();
        values.put(COL_CHECK, item.getCheck());
        return this.getBDD().update(TABLE_CART, values, "id=" + item.getId(), null);
    }

    /**
     * get all items and return an array of object
     * @return ArrayList<Ingredient>
     */
    public ArrayList<Ingredient> getAllItems(){
        ArrayList<Ingredient> items = new ArrayList<>();
        Cursor cursor = this.getBDD().query(TABLE_CART,
                allColumns , null, null, null, null, null);

        //si aucun élément n'a été retourné dans la requête, on renvoie null
        if (cursor.getCount() > 0)
            cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Ingredient item = cursorToIngredient(cursor, false);
            items.add(item);
            Log.i("LogNesti.cursor", item.toString() );
            cursor.moveToNext();
        }

        // assurez-vous de la fermeture du curseur
        cursor.close();
        return items;
    }
    /**
     * This method converts a cursor into an ingredient
     * @param c
     * @param close
     * @return
     */
    private Ingredient cursorToIngredient(Cursor c, boolean close){
        //On créé un item
        Ingredient item = new Ingredient();
        try{
            //on lui affecte toutes les infos grâce aux infos contenues dans le Cursor
            item.setId(c.getInt(NUM_COL_ID));
            item.setName(c.getString(NUM_COL_TITRE));
            item.setCheck(c.getInt(NUM_COL_CHECK));
        }catch (Exception e){
            item.setId(-1);
        }
        //On retourne le ingredient
        if(close){
            //On ferme le cursor
            c.close();
        }
        return item;
    }

    /**
     * Create the table
     * @param db
     */
    protected static void create_table(@NotNull SQLiteDatabase db){
        String CREATE_TABLE = "CREATE TABLE "+ TABLE_CART + " ( " +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT NOT NULL, " +
                COL_CHECK + " INTEGER " +
                " );";
        db.execSQL(CREATE_TABLE);
    }
    /**
     * Delete the table
     */
    public void removeAllItem() {
        this.getBDD().execSQL("DELETE FROM " + TableCart.TABLE_CART + ";");
    }
}


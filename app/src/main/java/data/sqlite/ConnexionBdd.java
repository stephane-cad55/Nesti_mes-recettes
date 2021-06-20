package data.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ConnexionBdd {

        protected Context context;
        private static SQLiteDatabase bdd;
        private static MyBaseSQLite maBaseSQLite;
        /**
         * access to the database
         * @return
         */
        public MyBaseSQLite getBda () {
            if (maBaseSQLite == null) {
                maBaseSQLite = new MyBaseSQLite(context, null);
                Log.i("LogNesti.BDD", "Initialisation de ma base SQL lite");
            }
            return maBaseSQLite;

        }
        /**
         * Constructor
         * @param context
         */
        ConnexionBdd(Context context) {
            this.context = context;
        }

        /**
         * Open the database for writing
         */
        public void open () {
            bdd = getBda().getWritableDatabase();
        }
        /**
         * Close access database
         */
        public void close () {
            bdd.close();
        }
        public SQLiteDatabase getBDD () {
            if (bdd == null) {
                this.open();
            }
            return bdd;
        }
    }


package be.ecam.ticketing.ticketing_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by hp on 25/04/2017.
 */

public class SQLiteManager extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME ="app.db";
    private static final int DATABASE_VERSION = 1;
    private SQLiteDatabase db_local;
    private String idUser;
    private String type;

    public  SQLiteManager(Context context)
    {
        super(context,"app_info",null,DATABASE_VERSION);
        this.idUser ="";
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /*final String SQL_CREATE_TABLE ="CREATE TABLE "+AppEntry.TABLE_NAME+
                "("+AppEntry.USER_NAME+" TEXT NOT NULL,"+AppEntry.USER_ID+
                " TEXT NOT NULL,"+AppEntry.USER_SOLD+"FLOAT DEFAULT '0.00');";*/
        final String SQL_CREATE_TABLE ="CREATE TABLE  app_info(name2 DECIMAL,name VARCHAR,id VARCHAR);";
        //final String ALTER_TABLE="ALTER TABLE app_info ADD email2 VARCHAR;";
        //final String ALTER_TABLE2="ALTER TABLE app_info ADD age INT;";

        db.execSQL(SQL_CREATE_TABLE);
        //db.execSQL(ALTER_TABLE);
        //db.execSQL(ALTER_TABLE);
       // this.db_local =db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST app_info");
        onCreate(db);
    }

    /*This method insert the user's informations from the SQLite database*/
    public boolean Insert(User user_in)
    {



        SQLiteDatabase db_local= this.getWritableDatabase();
        double balance = ((BasicUser) user_in).getBalance();
        //this.idUser = user_in.getID();
        String query ="INSERT INTO app_info(name,id,name2) VALUES('"+user_in.getName()+"','"+user_in.getID()+"'," +
                    ""+String.valueOf(balance)+");";
        //String query ="INSERT INTO app_info(name,id,name2) VALUES('test','test_id',110.56)";
        try
        {
            db_local.execSQL(query);
                return true;
        }
        catch(Exception e)
        {
            return false;
        }



    }

    /*This method retrieve the user's informations from the SQLite database*/
    public String[] InfoUser()
    {
        String[] info= new String[6];
        SQLiteDatabase mDb = this.getReadableDatabase();
        String[] column = {"name","id","solde"};
        String where = "id=?";
       // String[] whereArgs ={user.getID()};
        String query ="SELECT * FROM app_info";
        Cursor cursor = mDb.rawQuery(query,null);
        if(cursor != null && cursor.moveToFirst())
        {
            info[0]=cursor.getString(cursor.getColumnIndex("name"));
            info[1]=cursor.getString(cursor.getColumnIndex("id"));
            info[2]=String.valueOf(cursor.getDouble(cursor.getColumnIndex("name2")));
           // info[3]=cursor.getString(cursor.getColumnIndex("email"));
           //info[0]=String.valueOf(cursor.getInt(cursor.getColumnIndex("age")));
            cursor.close();

        }
        else
        {
            info[0]="error";
            info[1]="error";
            info[2]="error";
            info[3]="0";
        }




        return info;
    }

    /*public static final class AppEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "app_info";
        public static final String USER_NAME= "name";
        public static final String USER_ID = "id";
        public  static final String USER_SOLD= "solde";
    }*/
}

package be.ecam.ticketing.ticketing_app;

import android.content.Context;
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
    private final User user;
    private String type;

    public  SQLiteManager(Context context,BasicUser user)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.user = user;
        type = "basic";

    }
    public  SQLiteManager(Context context,Admin user)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        this.user = user;
        type= "Admin";

    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        final String SQL_CREATE_TABLE ="CREATE TABLE "+AppEntry.TABLE_NAME+
                "("+AppEntry.USER_NAME+" TEXT NOT NULL,"+AppEntry.USER_ID+
                " TEXT NOT NULL,"+AppEntry.USER_SOLD+"FLOAT DEFAULT '0');";
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXIST "+AppEntry.TABLE_NAME);
        onCreate(db);
    }

    public static final class AppEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "app_info";
        public static final String USER_NAME= "name";
        public static final String USER_ID = "id";
        public  static final String USER_SOLD= "solde";
    }
}

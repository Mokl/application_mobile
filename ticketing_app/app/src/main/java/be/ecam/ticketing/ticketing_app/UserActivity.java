package be.ecam.ticketing.ticketing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static be.ecam.ticketing.ticketing_app.R.id.button;

/**
 * Created by hp on 29/04/2017.
 */

public class UserActivity extends AppCompatActivity implements View.OnClickListener,
        SharedPreferences.OnSharedPreferenceChangeListener {
    private DatabaseManager db;
    private  SQLiteManager db_local;
    private String userID;
    private Button preference;
    private Button produit;
    private Button refill;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(sharedPreferences.getBoolean("background", false) ? R.style.AppThemeDayNight : R.style.AppThemeLight);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        setContentView(R.layout.user_activity);
        String[] infoDB={"ticketing_app","root",""};
        TextView nom = (TextView)findViewById(R.id.user1);
        TextView id = (TextView)findViewById(R.id.user2);
        TextView solde = (TextView)findViewById(R.id.textView4);
        preference =(Button)findViewById(R.id.button);
        produit = (Button)findViewById(R.id.button4);
        refill = (Button)findViewById(R.id.button2);
        preference.setOnClickListener(this);
        produit.setOnClickListener(this);
        refill.setOnClickListener(this);

        //db = new DatabaseManager(infoDB);
        SQLiteManager db_local = new SQLiteManager(this);
        String[] info = db_local.InfoUser();
        //String[] info = {"test","test2","120","test_row"};

        Toast.makeText(UserActivity.this,info[3],Toast.LENGTH_SHORT).show();

        nom.setText(info[0]);
        id.setText(info[1]);
        solde.setText(info[2]);
    }

    protected void onDestroy(){
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onClick(View v)
    {
        if(v == preference)
        {
            Intent intent = new Intent(this,SettingsActivity.class);
            startActivity(intent);
        }
        else if(v == produit)
        {
            Intent intent = new Intent(this,ProductActivity.class);
            startActivity(intent);
        }
        else if(v == refill)
        {
            Intent intent = new Intent(this,RefillActivity.class);
            startActivity(intent);
        }
    }

    private void changeBackground(boolean value){

    }

    public void onSharedPreferenceChanged(
            SharedPreferences sharedPreferences, String key){
        if(key.equals("background")) {
            finish();
            Intent intent = new Intent(this, SettingsActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        /*if(key.equals("language")) {
            attachBaseContext(this);
        }*/
    }

    protected void attachBaseContext(Context newBase){
        //String value = PreferenceManager.getDefaultSharedPreferences(this).getString("language", "en");
        super.attachBaseContext(MyContextWrapper.wrap(newBase, "en"));
    }
}

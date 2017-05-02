package be.ecam.ticketing.ticketing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hp on 29/04/2017.
 */

public class UserActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseManager db;
    private  SQLiteManager db_local;
    private String userID;
    private Button produit;
    private Button refill;

    private SessionManager session;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        session = new SessionManager(getApplicationContext());
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(sharedPreferences.getBoolean("background", false) ? R.style.AppThemeDark : R.style.AppThemeLight);

        //session.checkLogin();

        setContentView(R.layout.user_activity);
        String[] infoDB={"ticketing_app","root",""};
        TextView nom = (TextView)findViewById(R.id.user1);
        TextView id = (TextView)findViewById(R.id.user2);
        TextView solde = (TextView)findViewById(R.id.textView4);
        produit = (Button)findViewById(R.id.button4);
        refill = (Button)findViewById(R.id.button2);
        produit.setOnClickListener(this);
        refill.setOnClickListener(this);

        //db = new DatabaseManager(infoDB);
        SQLiteManager db_local = new SQLiteManager(this);
        //retrieve of the user information from the SQLite DB
        String[] info = db_local.InfoUser();
        //String[] info = {"test","test2","120","test_row"};

        Toast.makeText(UserActivity.this,info[3],Toast.LENGTH_SHORT).show();

        nom.setText(info[0]);
        id.setText(info[1]);
        solde.setText(info[2]);
    }

    protected void onRestart(){
        super.onRestart();
        finish();
        Intent intent = new Intent(this, UserActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        if(v == produit)
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.action_settings:
                intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                recreate();
                return true;

            case R.id.action_logout:
                intent = new Intent(this,ConnectActivity.class);
                startActivity(intent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    /*protected void attachBaseContext(Context newBase){
        super.attachBaseContext(LocaleHelper.wrap(newBase,
                PreferenceManager.getDefaultSharedPreferences(this).getString("language", "en")));
    }*/
}

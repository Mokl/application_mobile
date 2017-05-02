package be.ecam.ticketing.ticketing_app;

import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

import static android.R.attr.name;

public  class MainActivity extends AppCompatActivity implements View.OnClickListener,
        SharedPreferences.OnSharedPreferenceChangeListener
        {

    private Button btnClk;
    private Button btnCreate;
    private TextView msg;
    private EditText Name, Password;
    private String name;
    private String password;
    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Connexion DB
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_activity);
        btnClk = (Button)findViewById(R.id.buttonConnection);
        btnCreate=(Button)findViewById(R.id.create_user);
        btnClk.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
        Name = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.psswd);
        msg = (TextView)findViewById(R.id.user2);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        changeBackground(sharedPreferences.getBoolean("background", false));
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);

        //Connexion info
    }

    protected void onDestroy(){
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(
            SharedPreferences sharedPreferences, String key){
        if(key.equals("background")) {
            changeBackground(sharedPreferences.getBoolean(key, false));
        }

        /*if(key.equals("language")) {
            attachBaseContext(this);
        }*/
    }

    private void changeBackground(boolean value){
        if(value){
            setTheme(android.R.style.Theme_Black);
        }
        else {
            setTheme(android.R.style.Theme_Light);
        }
        //recreate();
    }

    protected void attachBaseContext(Context newBase){
        //String value = PreferenceManager.getDefaultSharedPreferences(this).getString("language", "en");
        super.attachBaseContext(MyContextWrapper.wrap(newBase, "en"));
    }

    public void onClick (View v) {
        String[] infoUser= new String[2];
        infoUser[0]=name;
        infoUser[1]=password;
        String[] infoDB={"ticketing_app","root","root"};
        this.db = new DatabaseManager(infoDB);

        if (v == btnClk)
        {
            name = Name.getText().toString();
            password = Password.getText().toString();

            //test offlline
            User user = new BasicUser("test","test@tes.com","TestID",25,"test",50);

            SQLiteManager db_local= new SQLiteManager(this);

            if(db_local.Insert(user)== true)
            {
                //setContentView(R.layout.user_activity);
                //Toast.makeText(MainActivity.this, user.getForeName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,UserActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(MainActivity.this,"Error SQLITE",Toast.LENGTH_SHORT);
            }

           /* if(db != null)
            {
                //Connecxion
                if(db.ConnectUser(infoUser)== true)
                {
                    //retrieve info
                    String[] info = new String[7];
                    info = db.getInfo(name);
                    User user = new BasicUser(info[0],info[5],info[3], Integer.parseInt(info[2]),info[4],Double.parseDouble(info[7]));

                    //SQLite
                    if(user.getAccess()> 0)
                    {
                        SQLiteManager db_local= new SQLiteManager(this,(Admin)user);
                        setContentView(R.layout.user_activity);
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                    }
                    if(user.getAccess() == 0)
                    {
                        SQLiteManager db_local= new SQLiteManager(this,(BasicUser)user);
                        setContentView(R.layout.user_activity);
                        Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                    }

                    Toast.makeText(MainActivity.this,"Connection granted",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(MainActivity.this,name,Toast.LENGTH_SHORT).show();
                }
            }
            else
            {
                Toast.makeText(MainActivity.this,"No DataBase",Toast.LENGTH_LONG).show();
            }*/

        }
        else if(v==btnCreate)
        {
            Intent intent = new Intent(this,AddUserActivity.class);
            startActivity(intent);
        }
    }
}

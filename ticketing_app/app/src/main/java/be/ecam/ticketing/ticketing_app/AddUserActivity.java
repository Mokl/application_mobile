package be.ecam.ticketing.ticketing_app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hp on 01/05/2017.
 */

public class AddUserActivity  extends AppCompatActivity implements View.OnClickListener
{
    private Button ok;
    private String[] info;
    EditText nom ;
    EditText prenom;
    EditText age;
    EditText address;
    EditText email;
    EditText id;
    EditText password;
    EditText psw_conf;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(sharedPreferences.getBoolean("background", false) ? R.style.AppThemeDayNight : R.style.AppThemeLight);

        setContentView(R.layout.createuser_activity);

        nom = (EditText)findViewById(R.id.editText11);
        prenom = (EditText)findViewById(R.id.editText12);
        age = (EditText)findViewById(R.id.editText13);
        address = (EditText)findViewById(R.id.editText14);
        email = (EditText)findViewById(R.id.editText15);
        id= (EditText)findViewById(R.id.editText16);
        password = (EditText)findViewById(R.id.editText17);
        psw_conf = (EditText) findViewById(R.id.editText18);

        ok=(Button)findViewById(R.id.button10);
        ok.setOnClickListener(this);
    }


    @Override
    public void onClick(View v)
    {
        boolean state = false;
        if(v == ok)
        {
            Toast.makeText(this,"Invalid or missing information",Toast.LENGTH_SHORT).show();
            info = new String[7];
            info[0] = nom.getText().toString()+" "+prenom.getText().toString();
            info[1] = address.getText().toString();
            info[2] = age.getText().toString();
            info[3] = password.getText().toString();
            info[4] = email.getText().toString();
            info[5] = "0";
            info[6] = psw_conf.getText().toString();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

            /*for(i=0;i<7;i++)
            {
                if(info[i] == "")
                {
                    state =true;
                    break;
                }
            }*/
            /*if(info[3] == "")
            {
                Toast.makeText(this,"User added",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this,"Invalid or missing information",Toast.LENGTH_LONG).show();
            }*/
        }
        else
        {
            Toast.makeText(this,"error",Toast.LENGTH_SHORT).show();
        }
    }
}

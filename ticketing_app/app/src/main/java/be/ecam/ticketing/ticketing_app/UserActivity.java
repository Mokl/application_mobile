package be.ecam.ticketing.ticketing_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hp on 29/04/2017.
 */

public class UserActivity extends AppCompatActivity implements View.OnClickListener
{
    private DatabaseManager db;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);
        String[] infoDB={"ticketing_app","root",""};

        db = new DatabaseManager(infoDB);
        TextView nom = (TextView)findViewById(R.id.user1);
        TextView id = (TextView)findViewById(R.id.user2);
        TextView solde = (TextView)findViewById(R.id.textView4);


    }
    @Override
    public void onClick(View v)
    {

    }
}

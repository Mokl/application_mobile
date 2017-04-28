package be.ecam.ticketing.ticketing_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.name;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnClk;
    private TextView msg;
    private EditText Name, Password;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_activity);
        btnClk = (Button)findViewById(R.id.buttonConnection);
        btnClk.setOnClickListener(this);
        Name = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.psswd);
        msg = (TextView)findViewById(R.id.user2);
        name = Name.getText().toString();

    }
    public void onClick (View v)
    {
        if (v == btnClk)
        {
            setContentView(R.layout.user_activity);
            //msg.setText(name);
            Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();

        }
    }
}

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
    private String password;
    private DatabaseManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //Connexion DB
        String[] infoDB={"ticketing_app","root",""};

        this.db = new DatabaseManager(infoDB);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion_activity);
        btnClk = (Button)findViewById(R.id.buttonConnection);
        btnClk.setOnClickListener(this);
        Name = (EditText)findViewById(R.id.username);
        Password = (EditText)findViewById(R.id.psswd);
        msg = (TextView)findViewById(R.id.user2);

        //Connexion info
        name = Name.getText().toString();
        password = Password.getText().toString();



    }
    public void onClick (View v)
    {
        String[] infoUser= new String[2];
        infoUser[0]=name;
        infoUser[1]=password;

        if (v == btnClk)
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
            }
            else
            {
                Toast.makeText(MainActivity.this,"Invalid information",Toast.LENGTH_SHORT).show();
            }
        }
    }
}

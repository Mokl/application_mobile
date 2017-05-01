package be.ecam.ticketing.ticketing_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by hp on 01/05/2017.
 */

public class RefillActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button confirm;
    private EditText value;
    private SQLiteManager db_local;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refill_activity);
        confirm= (Button) findViewById(R.id.confirm_refill);
        value =(EditText)findViewById(R.id.montant_refill);
        confirm.setOnClickListener(this);
        db_local = new SQLiteManager(this);


    }
    @Override
    public void onClick(View v)
    {
        if(v == confirm)
        {

            String[] info = db_local.InfoUser();

            if(!value.getText().toString().equals(""))
            {
                double montant = Double.parseDouble(value.getText().toString());
                Toast.makeText(this,"Done",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,UserActivity.class);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
            }

        }
    }
}

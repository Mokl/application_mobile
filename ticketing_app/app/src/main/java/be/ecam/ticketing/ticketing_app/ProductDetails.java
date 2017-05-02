package be.ecam.ticketing.ticketing_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hp on 01/05/2017.
 */

public class ProductDetails extends AppCompatActivity implements View.OnClickListener
{
    private Button buy;
    private DatabaseManager db;
    private double price;
    private String name;
    private String user_id;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        TextView nom  = (TextView)findViewById(R.id.nom);
        TextView desc = (TextView)findViewById(R.id.description);
        TextView prix = (TextView)findViewById(R.id.prix);

        Intent intent = getIntent();

        //retrieve product
        Product data = Product.find(intent.getIntExtra(Intent.EXTRA_INDEX,0));
        price = data.Prix();
        name = data.Nom();

        //display
        nom.setText(data.Nom());
        desc.setText(data.Description());
        prix.setText(String.valueOf(data.Prix()));


        buy = (Button)findViewById(R.id.buy);
        buy.setOnClickListener(this);

        //Connection db
       /* String[] info = {"ticketing_app","root","root"};
        db = new DatabaseManager(info);

        //Connection db_local
        SQLiteManager db_local = new SQLiteManager(this);
        //retrieve of the user information from the SQLite DB
        String[] info_user = db_local.InfoUser();
        user_id=info_user[0];*/
    }
    @Override
    public void onClick(View v)
    {
        if(v == buy)
        {
            //online
            /*if(db.Pay(price,user_id,name))
            {
                Toast.makeText(this,"Done..Thank you",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this,"Error...",Toast.LENGTH_SHORT).show();
            }*/
            Intent intent = new Intent(this,ProductActivity.class);
            startActivity(intent);
        }
    }
}

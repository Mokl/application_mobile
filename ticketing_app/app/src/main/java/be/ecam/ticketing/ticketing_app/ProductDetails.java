package be.ecam.ticketing.ticketing_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by hp on 01/05/2017.
 */

public class ProductDetails extends AppCompatActivity implements View.OnClickListener
{
    private Button buy;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        TextView nom  = (TextView)findViewById(R.id.nom);
        TextView desc = (TextView)findViewById(R.id.description);
        TextView prix = (TextView)findViewById(R.id.prix);

        Intent intent = getIntent();
        Product data = Product.find(intent.getIntExtra(Intent.EXTRA_INDEX,0));
        nom.setText(data.Nom());
        desc.setText(data.Description());
        prix.setText(String.valueOf(data.Prix()));

        buy = (Button)findViewById(R.id.buy);
        buy.setOnClickListener(this);

    }
    @Override
    public void onClick(View v)
    {
        if(v == buy)
        {
            ;
        }
    }
}

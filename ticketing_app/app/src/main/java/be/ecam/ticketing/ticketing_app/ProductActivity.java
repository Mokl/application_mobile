package be.ecam.ticketing.ticketing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.NavUtils;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import static be.ecam.ticketing.ticketing_app.R.id.product;
import static be.ecam.ticketing.ticketing_app.R.id.resultView;

/**
 * Created by hp on 01/05/2017.
 */

public class ProductActivity extends AppCompatActivity implements ItemAdapter.ItemAdapterOnClickHandler

{
    private RecyclerView product_name;
    private ItemAdapter itemAdapter;
    private ArrayList<Product> product_list = new ArrayList<>();
    private DatabaseManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        setTheme(sharedPreferences.getBoolean("background", false) ? R.style.AppThemeDayNight : R.style.AppThemeLight);

        setContentView(R.layout.userprice_activity);

        product_name = (RecyclerView) findViewById(R.id.resultView);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        product_name.setLayoutManager(layoutManager);
        product_name.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(this);
        product_name.setAdapter(itemAdapter);


        getProduct();


    }

    public class QueryTask extends AsyncTask< Void ,Product, Void>
    {


        @Override
        protected Void doInBackground(Void... params)
        {
            //offline test
            Product p1= new Product("chimay","biere",1);
            product_list.add(p1);
            Product p2= new Product("kriek","biere",1);
            product_list.add(p2);
            Product p3= new Product("vodka","vodka",5);
            product_list.add(p3);
            Product p4= new Product("coca","soft",1.5);
            product_list.add(p4);
            Product p5= new Product("fanta","soft",1.5);
            product_list.add(p5);
            Product p6= new Product("jack daniels","liqueur",3);
            product_list.add(p6);
            Product p7= new Product("sprite","soft",1.5);
            product_list.add(p7);
            Product p8= new Product("redbull","soft",2);
            product_list.add(p8);
            Product p9= new Product("ice tea","soft",1);
            product_list.add(p9);
            Product p10= new Product("brune","biere",1);
            product_list.add(p10);
            Product p11= new Product("blonde","biere",1);
            product_list.add(p11);

            //online
           /*String[] info = {"ticketing_app","root","root"}
            db = new DatabaseManager(info);
            product_list = db.getProduct();*/


            return null;
        }

        @Override
        protected void onPostExecute(Void queryResults)
        {
            itemAdapter.setData(product_list);
            Product.setList(product_list);
        }
    }

    private void getProduct()
    {
        new QueryTask().execute();
    }

    @Override
    public void onClick(int index)
    {
        Intent intent = new Intent(this,ProductDetails.class);
        intent.putExtra(Intent.EXTRA_INDEX, index);
        startActivity(intent);
    }
}

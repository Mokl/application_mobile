package be.ecam.a11209.am_lab1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONException;

import java.io.IOException;


/**
 * Created by hp on 13/02/2017.
 */

public class CityList extends AppCompatActivity implements ItemAdapter.ItemAdapterOnClickHandler,
        LoaderManager.LoaderCallbacks<String>,SharedPreferences.OnSharedPreferenceChangeListener{

    private RecyclerView resultView;
    private ItemAdapter itemAdapter;
    private String json;
    private static final int QUERY_LOADER = 22;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.city__list);


        resultView = (RecyclerView) findViewById(R.id.resultView);



        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        resultView.setLayoutManager(layoutManager);
        resultView.setHasFixedSize(true);

        itemAdapter = new ItemAdapter(this);
        resultView.setAdapter(itemAdapter);

        Bundle queryURL = new Bundle();
        queryURL.putString("URL","https://andfun-weather.udacity.com/weather");

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.restartLoader(QUERY_LOADER,queryURL,this);

        SharedPreferences sharedPreferences= PreferenceManager.getDefaultSharedPreferences(this);
        //action à faire doUsefulStuffBasedOnMyBooleanPreference(sharedPreferences.getBoolean (key , false ));

        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public Loader<String> onCreateLoader(int id, final Bundle args)
    {
        return new AsyncTaskLoader<String>(this)
        {
            String json = null;
            @Override
            protected void onStartLoading()
            {
                if(json != null)
                {
                    deliverResult(json);
                }
                else
                {
                    forceLoad();
                }
            }

            @Override
            public  String loadInBackground()
            {
                String searchURL = args.getString("URL");
                try
                {
                    return NetCon.getResponseFromHttpUrl(searchURL);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            public void deliverResult(String data)
            {
                json = data;
                super.deliverResult(data);
            }
        };
    }

    @Override
    public void onLoadFinished(android.support.v4.content.Loader<String> loader, String data)
    {
        try {
            Log.i("ASyncTaskLoader", "query loaded");
            itemAdapter.setData(data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(android.support.v4.content.Loader<String> loader)
    {

    }

    /*public class QueryTask extends AsyncTask<String, Void, String>
    {


        @Override
        protected String doInBackground(String... params)
        {
            String searchUrl = params[0];
            String json = null;

            try {
                json = NetCon.getResponseFromHttpUrl(searchUrl);
            } catch (Exception e) {
                e.printStackTrace();

            }
            return json;
        }

        @Override
        protected void onPostExecute(String queryResults)
        {
            if ( queryResults != null && ! queryResults . equals (""))
            {
                try {
                    itemAdapter.setData(queryResults);
                    json = queryResults;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

    /*private void makeQuery()
    {
        new QueryTask().execute("https://andfun-weather.udacity.com/weather");
    }*/

    @Override
    public void onClick(int index) {
        Context context = this;
        Class destinationClass = MainActivity.class;
        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(Intent.EXTRA_INDEX, index);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int itemThatWasClickedId = item . getItemId ();

        if(itemThatWasClickedId == R.id.action)
        {
            Context context = CityList.this;
            String text = "Loading....";
            Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
            //makeQuery();

            Bundle queryURL = new Bundle();
            queryURL.putString("URL","https://andfun-weather.udacity.com/weather");

            LoaderManager loaderManager = getSupportLoaderManager();
            loaderManager.restartLoader(QUERY_LOADER,queryURL,this);
        }
        if(itemThatWasClickedId == R.id.preference)
        {
            Context context = CityList.this;
            String text = "preference";
            Toast.makeText(context,text,Toast.LENGTH_SHORT).show();
            Class destinationClass = SettingsActivity.class;
            Intent intent = new Intent(context, destinationClass);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key)
    {
        if(key.equals("my_boolean"))
        {
            ;
            //doUsefulStuffBasedOnMyBooleanPreference(sharedPreferences.getBoolean (key , false ));
        }
        if(key.equals("color"))
        {

        }
        if(key.equals("name"))
        {

        }
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).
                unregisterOnSharedPreferenceChangeListener(this);
    }

}

package be.ecam.ticketing.ticketing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.MenuItem;

/**
 * Created by Paluche on 27-04-17.
 * Modified by Juan Barrera on 02-05-17.
 */

public class SettingsActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        setTheme(sharedPreferences.getBoolean("background", false) ? R.style.AppThemeDayNight : R.style.AppThemeLight);


        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new SettingsFragment(), "settings")
                .addToBackStack("settings")
                .commit();
    }

    protected void onDestroy(){
        super.onDestroy();
        PreferenceManager.getDefaultSharedPreferences(this).unregisterOnSharedPreferenceChangeListener(this);
    }

    public void onSharedPreferenceChanged(
            SharedPreferences sharedPreferences, String key){
        if(key.equals("background")) {
            recreate();
        }

        /*if(key.equals("language")) {
            attachBaseContext(this);
        }*/
    }

    protected void attachBaseContext(Context newBase){
        //String value = PreferenceManager.getDefaultSharedPreferences(this).getString("language", "en");
        super.attachBaseContext(MyContextWrapper.wrap(newBase, "en"));
    }
}


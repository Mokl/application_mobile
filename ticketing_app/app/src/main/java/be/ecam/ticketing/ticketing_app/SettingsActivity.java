package be.ecam.ticketing.ticketing_app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Created by Paluche on 27-04-17.
 * Modified by Juan Barrera on 02-05-17.
 */

public class SettingsActivity extends AppCompatActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private String lang;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        setTheme(sharedPreferences.getBoolean("background", false) ? R.style.AppThemeDark : R.style.AppThemeLight);

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

        if(key.equals("language")) {
            lang = sharedPreferences.getString("language", "en");
        }
    }

    /*protected void attachBaseContext(Context newBase){
        if (!TextUtils.isEmpty(lang)){
            super.attachBaseContext(LocaleHelper.wrap(newBase,lang));
        }
    }*/
}


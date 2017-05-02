package be.ecam.ticketing.ticketing_app;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by Paluche on 27-04-17.
 */

public class SettingsFragment extends PreferenceFragment/*Compat*/ {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}

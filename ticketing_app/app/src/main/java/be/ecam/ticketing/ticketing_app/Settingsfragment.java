package be.ecam.ticketing.ticketing_app;

import android.os.Bundle;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by Paluche on 27-04-17.
 */

public class SettingsFragment extends PreferenceFragmentCompat
{

    public void onCreatePreferences(Bundle bundle,String s)
    {
        addPreferencesFromResource (R.xml.preferences);
    }
}

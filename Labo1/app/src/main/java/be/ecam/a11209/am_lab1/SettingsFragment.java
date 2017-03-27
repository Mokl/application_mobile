package be.ecam.a11209.am_lab1;

import android.os.Bundle;
import android.support.v7.preference.PreferenceDialogFragmentCompat;
import android.support.v7.preference.PreferenceFragmentCompat;

/**
 * Created by hp on 07/03/2017.
 */

public class SettingsFragment extends PreferenceFragmentCompat
{

    public void onCreatePreferences(Bundle bundle,String s)
    {
        addPreferencesFromResource (R.xml.preferences);
    }
}

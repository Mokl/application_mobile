package be.ecam.ticketing.ticketing_app;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

/**
 * Created by Juan Barrera on 27-04-17.
 * Time: 10:49.
 */

public class SessionManager {
    SharedPreferences pref;
    Editor editor;
    Context context;

    // Sharedpref file name
    private static final String PREF_NAME = "TicketingAppPref";

    // All Shared Preferences Keys
    public static final String IS_LOGIN = "IsLoggedIn";
    public static final String KEY_NAME = "name";
    public static final String KEY_ID = "ID";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_LANGUAGE= "language";


    public SessionManager(Context context){
        this.context = context;
        this.pref = this.context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        this.editor = pref.edit();
    }

    public void createLoginSession(String ID, String password, String name){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_NAME, name);
        editor.putString(KEY_ID, ID);
        editor.putString(KEY_PASSWORD, password);

        editor.commit();
    }

    public void setLanguage(String language){
        editor.putBoolean(IS_LOGIN, true);
        editor.putString(KEY_LANGUAGE, language);

        editor.commit();
    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(KEY_ID, pref.getString(KEY_ID, null));
        user.put(KEY_PASSWORD, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_NAME, pref.getString(KEY_PASSWORD, null));
        user.put(KEY_LANGUAGE, pref.getString(KEY_LANGUAGE, "en"));
        return user;
    }

    public boolean isLoggedIn(){
        return pref.getBoolean(IS_LOGIN, false);
    }

    public void checkLogin(){
        if(!this.isLoggedIn()){
            Intent i = new Intent(this.context, ConnectActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.context.startActivity(i);
        }
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();

        Intent i = new Intent(this.context, ConnectActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.context.startActivity(i);
    }
}

package il.co.amits.proteintracker.settings;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 16/03/2016.
 */
public class SettingsHelper {

    Context context;
    final String SHARED_PREF_FILE_NAME = "Settings";
    final String PREF_NOTIF_ON_NAME = "ShowNotif";
    final String PREF_SHORTCUT_INSTALLED_NAME = "ShortcutInstalled";

    SharedPreferences.Editor sPEditor;
    SharedPreferences sharedpreferences;

    public SettingsHelper(Context context){
        // on initiating a helper:
        this.context = context;
//        sharedpreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
    }
    public Boolean getNotifOn(){
        sharedpreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
        return sharedpreferences.getBoolean(PREF_NOTIF_ON_NAME, false);
    }

    public boolean getIsShortcutInstalled(){
        sharedpreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
        return sharedpreferences.getBoolean(PREF_SHORTCUT_INSTALLED_NAME, true);
    }

    public void setNotifOn(Boolean isNotifOn){
        sharedpreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
        sPEditor = sharedpreferences.edit();
        sPEditor.putBoolean(PREF_NOTIF_ON_NAME, isNotifOn);
        sPEditor.commit();
    }

    public void setShortcutInstalled(Boolean isShortcutInstalled) {
        sharedpreferences = context.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
        sPEditor = sharedpreferences.edit();
        sPEditor.putBoolean(PREF_SHORTCUT_INSTALLED_NAME, isShortcutInstalled);
        sPEditor.commit();
    }
}

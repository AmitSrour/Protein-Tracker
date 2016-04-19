package il.co.amits.proteintracker.tracking;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import il.co.amits.proteintracker.MainActivity;

/**
 * Created by Amit on 16/03/2016.
 */
public class ProteinHelper {

    private static final String SHARED_PREF_FILE_NAME = "ProteinTacking";
    private static final String PROT_TODAY_PREF_NAME = "ProteinToday";
    private static final String PROT_GOAL_PREF_NAME = "ProteinGoal";

    public static SharedPreferences.Editor sPEditor;
    public static SharedPreferences sharedpreferences;

    public ProteinHelper(){
        // on initiating a helper:
        //sPEditor = context.getSharedPreferences(SHARED_PREF_FILE_NAME, 0).edit();

    }
    public static float getProtToday(Context c){
        sharedpreferences = c.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
        float result = sharedpreferences.getFloat(PROT_TODAY_PREF_NAME, 0);
        //Log
        return result;
    }

    public static float getProtGoal(Context c){
        sharedpreferences = c.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
        float result = sharedpreferences.getFloat(PROT_GOAL_PREF_NAME, 9999);//9999(magic number) is error number meaning no goal has been set yet ie: promt the wizard
        return result;
    }

    public static void setProtToday(Context c,float protToday){
        sharedpreferences = c.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
        sPEditor = sharedpreferences.edit();
        sPEditor.putFloat(PROT_TODAY_PREF_NAME, protToday);
        sPEditor.commit();
    }

    public static void setProtGoal(Context c,float protToday){
        sharedpreferences = c.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
        sPEditor = sharedpreferences.edit();
        sPEditor.putFloat(PROT_GOAL_PREF_NAME, protToday);
        sPEditor.commit();
    }
    /**
     * Add protein to today's Protein Counter.
     */
    public static float addToProtToday(Context c,float toAdd){
        sharedpreferences = c.getSharedPreferences(SHARED_PREF_FILE_NAME, 0);
        float result = toAdd+getProtToday(c);
        sPEditor = sharedpreferences.edit();
        sPEditor.putFloat(PROT_TODAY_PREF_NAME, result);
        sPEditor.commit();
        return result;
    }
    public static String getProgress(Context c){
        float ProtToday=getProtToday(c),ProtGoal=getProtGoal(c);
        float perc = ProtToday/ProtGoal*100;
        return (int)ProtToday+"g / "+(int)ProtGoal+"g ("+(int)perc +"%)";
    }
    public static int getProgressBar(Context c){
        float ProtToday=getProtToday(c),ProtGoal=getProtGoal(c);
        float perc = ProtToday/ProtGoal*100;
        return (int)perc;
    }
}

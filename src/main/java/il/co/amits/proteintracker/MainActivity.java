package il.co.amits.proteintracker;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import il.co.amits.proteintracker.food.FoodActivity;
import il.co.amits.proteintracker.goalcalculator.GoalCalculatorActivity;
import il.co.amits.proteintracker.service.BackgroundService;
import il.co.amits.proteintracker.settings.SettingsHelper;

import il.co.amits.proteintracker.tracking.ProteinHelper;
import il.co.amits.proteintracker.tracking.ResetDialogFragment;


public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private EditText editTextIntakeProt;
    private TextView progressText;
    private ProgressBar progressBar;



    //private ProteinHelper myProteinHelper = new ProteinHelper(this);
    private SettingsHelper mySettingsHelper = new SettingsHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize Views
        Switch notifOption = (Switch) findViewById(R.id.switch_keep_notification);
        progressText = (TextView) findViewById(R.id.text_progress);
        editTextIntakeProt = (EditText) findViewById(R.id.edit_protein_intake);
        progressBar = (ProgressBar) findViewById(R.id.progressbar_daily_progress);

        //set Views according to prefs
        notifOption.setChecked(mySettingsHelper.getNotifOn());
        progressText.setText(ProteinHelper.getProgress(this));
        progressBar.setProgress(ProteinHelper.getProgressBar(this));

        //initiate listeners
        if (notifOption != null) {
            notifOption.setOnCheckedChangeListener(this);
        }

        //see if service is running if isn't then start it!
        if (!BackgroundService.isMyServiceRunning(BackgroundService.class, this)) {
            startService(new Intent(this, BackgroundService.class));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_search:
                Toast.makeText(this, "search", Toast.LENGTH_SHORT)
                        .show();
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            case R.id.action_exit:
                finish();
                return true;
            default:
                break;
        }

        return true;
    }

    public void onUserResetDialogResult(Boolean result) {
        if (result) updateProtViews();
    }

    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        //Toast.makeText(this, "The Switch checked=" +isChecked );
        mySettingsHelper.setNotifOn(isChecked);
        if (isChecked) {
            if (!BackgroundService.isMyServiceRunning(BackgroundService.class,this)) {
                startService(new Intent(this, BackgroundService.class));
            }
        } else {
            //do stuff when Switch if OFF
            //TODO kill the service
            this.stopService(new Intent(this, BackgroundService.class));
            //stopService(new Intent(this, BackgroundService.class));
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) this.getSystemService(ns);
            nMgr.cancel(01);
        }
    }

    public void onClickAddProt(View view) {
        if( editTextIntakeProt.getText().toString().length() == 0){
            editTextIntakeProt.setError("A number is required");
            return;
        }
        if(Integer.parseInt(editTextIntakeProt.getText().toString())>0 && Integer.parseInt(editTextIntakeProt.getText().toString()) < 900) {
            ProteinHelper.addToProtToday(this,Float.parseFloat(editTextIntakeProt.getText().toString()));
            updateProtViews();
        }
        View Sview = this.getCurrentFocus();
        if (Sview != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void onResetDailyIntakeButClick(View view) {
        //ProteinHelper.setProtToday(this, 0);

        ResetDialogFragment mDialogFragment = new ResetDialogFragment();
        mDialogFragment.show(getFragmentManager(), "reset");
    }

    public void onRecalculateGoalButClick(View view) {
        Intent intent = new Intent(this, GoalCalculatorActivity.class);
        startActivityForResult(intent, 1);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) { //make new Goal
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String result = data.getExtras().getString("result");
                ProteinHelper.setProtGoal(this, Float.parseFloat(result));
                updateProtViews();
            }
        }

        if (requestCode == 2) { //Protein From food
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                String result = data.getExtras().getString("proteinResult");
                editTextIntakeProt.setText(result);
                //updateProtViews();
            }
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        updateProtViews();
    }

    private void updateProtViews(){
       // editTextIntakeProt.setText("");
        progressText.setText(ProteinHelper.getProgress(this));
        progressBar.setProgress(ProteinHelper.getProgressBar(this));
        //update notification
        BackgroundService.startNotification(this);
    }


    public void onClickOpenFoodActivity(View view) {
        Intent intent = new Intent(this, FoodActivity.class);
        startActivityForResult(intent, 2);
    }
}

package il.co.amits.proteintracker.goalcalculator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.text.Editable;
import android.text.TextWatcher;

import il.co.amits.proteintracker.R;

public class GoalCalculatorActivity extends AppCompatActivity {


    RadioGroup radioGroupIntensity;
    RadioGroup radioGroupGender;
    RadioGroup radioGroupWeightMetric;

    EditText editTextAge;
    EditText editTextWeight;
    EditText resultText;

    public int activityLevel=1;
    public int age = 0;
    public boolean isMan=true;
    public double weightInKilo = 0;
    public double weightInKiloMultiplier=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_calculator);

        editTextAge = (EditText) findViewById(R.id.edit_age);
        editTextWeight = (EditText) findViewById(R.id.edit_weight);
        resultText = (EditText) findViewById(R.id.edit_daily_goal);

        radioGroupIntensity = (RadioGroup) findViewById(R.id.radiogroup_intensity);
        radioGroupGender = (RadioGroup) findViewById(R.id.radiogroup_gender);
        radioGroupWeightMetric = (RadioGroup) findViewById(R.id.radiogroup_metic);

        editTextAge.addTextChangedListener(textWatcherAge);
        editTextWeight.addTextChangedListener(textWatcherWeight);

    }
    public void onSendResultButtonClick(View view) {
        View Sview = this.getCurrentFocus();
        if (Sview != null) {//hide keyboard
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        //validate
        if( editTextAge.getText().toString().length() == 0 || Double.parseDouble(editTextWeight.getText().toString()) ==0){
            editTextAge.setError("How old are you?");
            return;
        }
        if( editTextWeight.getText().toString().length() == 0 || Double.parseDouble(editTextWeight.getText().toString())==0){
            editTextWeight.setError("How much do you weight?");
            return;
        }
        //TODO: add BMI calculator and check for anorexia or obese https://en.wikipedia.org/wiki/Obesity
        if( Double.parseDouble(editTextWeight.getText().toString()) <1*weightInKiloMultiplier || Double.parseDouble(editTextWeight.getText().toString()) >150*weightInKiloMultiplier){//if Obese
            editTextWeight.setError("Please consult with your doctor Before using this app");
            return;
        }
        if( resultText.getText().toString().length() == 0 || resultText.getText().toString()=="0"){
            resultText.setError("Cannot be Empty or 0");
            return;
        }

        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("result", resultText.getText().toString());
        data.putExtra("code", 1); // ints work too
        // Activity finished ok, return the data
        setResult(RESULT_OK, data); // set result code and bundle data for response
        finish(); // closes the activity, pass data to parent
    }
    public void updateResult(){//damand to update the result view;
        double result = CalculatorHelper.calculateProteinGoal(age, isMan, weightInKilo * weightInKiloMultiplier, activityLevel);
        resultText.setText(String.valueOf(Math.round(result)));

    }
    public void onIntensityRadioGroupClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        switch(view.getId()){
            case R.id.radio_intensity1:
                if(checked){
                    activityLevel=1;
                    updateResult();
                }
                break;
            case R.id.radio_intensity2:
                if(checked){
                    activityLevel=2;
                    updateResult();
                }
                break;
            case R.id.radio_intensity3:
                if(checked){
                    activityLevel=3;
                    updateResult();
                }
                break;
            case R.id.radio_intensity4:
                if(checked){
                    activityLevel=4;
                    updateResult();
                }
                break;
            case R.id.radio_intensity5:
                if(checked){
                    activityLevel=5;
                    updateResult();
                }
                break;

        }
    }
    public void onWeightRadioGroupClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_pounds:
                if (checked) {
                    weightInKiloMultiplier = 2.2;
                    updateResult();
                }
                break;
            case R.id.radio_kilograms:
                if (checked) {
                    weightInKiloMultiplier = 1;
                    updateResult();
                }
                break;
        }
    }

    private final TextWatcher textWatcherAge = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.length()==0){

            }
           else if(s.length()<=2){
                age= Integer.parseInt(editTextAge.getText().toString());
                updateResult();
            }
            else{
                //max length reached
            }

        }
    };
    private final TextWatcher textWatcherWeight = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable stringInput) {
            Boolean mValid = false;
            String mInput = stringInput.toString();
            Character mInputFirstDigit = null;

            if(stringInput.length()!=0){
                Character mInputLastDigit = mInput.charAt(mInput.length() - 1);
                mInputFirstDigit = mInput.charAt(0);
                mValid = mInput.length()<=5 && !mInputFirstDigit.equals('.') && !mInputLastDigit.equals('.');

            }

            if(mValid){
                weightInKilo= Double.parseDouble(mInput)*weightInKiloMultiplier;
                updateResult();
            }
            else if(!mInput.equals("")&& mInput.charAt(0) == '.'){
                editTextWeight.setText(mInput.substring(1));

            }
        }
    };

    public void onGenderRadioGroupClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.radio_female:
                if (checked) {
                    isMan = false;
                    updateResult();
                    break;
                }
            case R.id.radio_male:
                if (checked) {
                    isMan = true;
                    updateResult();
                    break;
                }
        }
    }
}

package il.co.amits.proteintracker.food;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import il.co.amits.proteintracker.R;

/**
 * Created by Administrator on 19/03/2016.
 */
public class ResultFragment extends Fragment implements View.OnClickListener{
    private Spinner spinner;
    private EditText editQuantity;
    private EditText editToAdd;
    private Button buttonAdd;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // das Layout fuer dieses Fragment laden


        // listener for button
        View view= inflater.inflate(R.layout.fragment_food_result, container, false);
        return view;   // return inflated view
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        editQuantity = (EditText) getView().findViewById(R.id.edit_quantity);
        editToAdd = (EditText) getView().findViewById(R.id.edit_to_add);
        buttonAdd = (Button) getView().findViewById(R.id.button_add_result);
        spinner = (Spinner) getView().findViewById(R.id.spinner_serving_quantity);
        buttonAdd.setOnClickListener(this);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.quantities_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        // Prepare data intent
        Intent data = new Intent();
        // Pass relevant data back as a result
        data.putExtra("proteinResult",editToAdd.getText().toString().substring(0,editToAdd.getText().toString().length()-1)); // remove g suffix
        data.putExtra("code", 2); // ints work too
        // Activity finished ok, return the data
        getActivity().setResult(Activity.RESULT_OK, data); // set result code and bundle data for response
        getActivity().finish(); // closes the activity, pass data to parent
    }
}

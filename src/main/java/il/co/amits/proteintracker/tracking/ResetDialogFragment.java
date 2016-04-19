package il.co.amits.proteintracker.tracking;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import il.co.amits.proteintracker.MainActivity;

/**
 * Created by Administrator on 19/03/2016.
 */
public class ResetDialogFragment extends DialogFragment{

    Boolean dialogResult;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Reset Daily intake");
        builder.setMessage("You sure you want to reset your daily intake?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ProteinHelper.setProtToday(getActivity().getApplicationContext(),(float)0);
                        dialogResult = true;
                        MainActivity callingActivity = (MainActivity) getActivity();
                        callingActivity.onUserResetDialogResult(dialogResult);
                        //getActivity().findViewById(R.id.)
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        dialogResult = false;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }


}

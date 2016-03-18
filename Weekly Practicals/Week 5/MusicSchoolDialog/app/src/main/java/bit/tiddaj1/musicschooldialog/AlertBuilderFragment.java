package bit.tiddaj1.musicschooldialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

public class AlertBuilderFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //new builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //If I do the extension I need to get the data to here and set the title.
        builder.setTitle("Confirm?");
        builder.setPositiveButton("Yes", new ButtonHandler());
        builder.setNegativeButton("No", new ButtonHandler());

        return builder.create();
    }

    //ButtonHandler for Dialog Fragment buttons
    private class ButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            //if yes is clicked.
            if(which == DialogInterface.BUTTON_POSITIVE)
            {
                MainActivity myActivity = (MainActivity) getActivity();
                myActivity.getDialogData(true);
            }
            //if no is clicked
            else if (which == DialogInterface.BUTTON_NEGATIVE)
            {
                MainActivity myActivity = (MainActivity) getActivity();
                myActivity.getDialogData(false);
            }
            else
            {
                /*
                I want something to happen when the user doesn't click yes or no and they click
                outside of the dialog fragment.
                */
                //Currently not doing anything.....
                Toast.makeText(getActivity(), "Toast Test", Toast.LENGTH_SHORT).show();
            }
        }
    }
}

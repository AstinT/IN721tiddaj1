package bit.tiddaj1.languagetrainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class AlertBuilderFragment extends DialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        //new builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //gets String from the bundle
        String title = getArguments().getString("answer");
        String feedback = getArguments().getString("feedback");
        //sets the string as the title
        builder.setTitle(title);
        builder.setMessage(feedback);
        builder.setPositiveButton("Next Question", new ButtonHandler());

        AlertDialog alert = builder.create();
        //Stops user canceling and clicking outside of DialogFragment
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        return alert;
    }

    //ButtonHandler for Dialog Fragment button
    private class ButtonHandler implements DialogInterface.OnClickListener
    {
        @Override
        public void onClick(DialogInterface dialog, int which)
        {
            //if next question is clicked
            if(which == DialogInterface.BUTTON_POSITIVE)
            {
                QuestionActivity myActivity = (QuestionActivity) getActivity();
                myActivity.getDialogData();
            }
        }
    }
}
package bit.tiddaj1.musicschooldialog;

import android.app.FragmentManager;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    //Global
    Spinner monthsSpinner;
    TextView tvEnrolled;
    AlertBuilderFragment confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting references
        tvEnrolled = (TextView) findViewById(R.id.tvOutput);
        monthsSpinner = (Spinner) findViewById(R.id.spnMonths);
        Button btnEnroll = (Button) findViewById(R.id.btnConfirm);

        setupSpinner();

        //Setting OnClickListener
        btnEnroll.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener
    {
        @Override
        //onClick method.
        //Makes DialogFragment
        public void onClick(View v)
        {
            confirm = new AlertBuilderFragment();
            FragmentManager fm = getFragmentManager();
            confirm.show(fm, "confirm");
        }
    }

    //Setting up spinner
    public void setupSpinner()
    {
        //Layout for spinner
        int layoutID = android.R.layout.simple_spinner_item;

        //Getting string array from array.xml
        Resources resourceMachine = getResources();
        String[] monthsArray = resourceMachine.getStringArray(R.array.months_array);

        //Creating adapter with layout and string array
        ArrayAdapter<String> monthsAdapter = new ArrayAdapter<String>(this, layoutID, monthsArray);

        //setting adapter to spinner
        monthsSpinner.setAdapter(monthsAdapter);
    }

    //Builds confirmation message depending on what the user clicks in the dialog fragment
    public void buildConfirmationMessage(boolean option)
    {
        if(option)
        {
            //getting radio group id
            RadioGroup rgInstruments = (RadioGroup) findViewById(R.id.rgInstruments);

            //gets checked radiobutton that belongs to rgInstruments
            int checkedId = rgInstruments.getCheckedRadioButtonId();

            //Declaring a radioButton with the checkedId
            RadioButton checkedRadiobtn = (RadioButton) findViewById(checkedId);

            //Build message
            String msg = "You have enrolled for " + checkedRadiobtn.getText() + " lessons in "
                    + monthsSpinner.getSelectedItem() + ".";

            //Displaying what they have enrolled in
            tvEnrolled.setText(msg);
        }
        else
            //Click no in dialog fragment
            tvEnrolled.setText("Ohwell.");
    }

    //passes data from AlertBuilderFragment to MainActivity
    public void getDialogData(boolean option)
    {
        //Closes dialog fragment
        confirm.dismiss();

        //Builds a confirmation message
        if (option)
        {
            buildConfirmationMessage(option);
        }
        else
        {
            buildConfirmationMessage(option);
        }
    }
}
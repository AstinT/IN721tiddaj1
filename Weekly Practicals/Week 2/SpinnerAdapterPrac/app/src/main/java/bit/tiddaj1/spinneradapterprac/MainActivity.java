package bit.tiddaj1.spinneradapterprac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Layout for spinner
        int layoutID = android.R.layout.simple_spinner_item;
        //Array of strings for spinner
        String[] months =
                {
                        "January",
                        "February",
                        "March",
                        "April",
                        "May",
                        "June",
                        "July",
                        "August",
                        "September",
                        "October",
                        "November",
                        "December"
                };
        //Creating adapter with layout and strings array
        ArrayAdapter<String> monthsAdapter = new ArrayAdapter<String>(this, layoutID, months);

        //Declaring spinner
        Spinner monthsSpinner = (Spinner) findViewById(R.id.spnMonths);
        //setting adapter to spinner
        monthsSpinner.setAdapter(monthsAdapter);

        Button btnEnroll = (Button) findViewById(R.id.btnEnroll);
        btnEnroll.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener {

        @Override
        //onClick method.
        //Executes when button is clicked
        public void onClick(View v) {
            //getting radio group id
            RadioGroup rgInstruments = (RadioGroup) findViewById(R.id.rgInstruments);
            //gets checked radiobutton that belongs to rgInstruments
            int checkedId = rgInstruments.getCheckedRadioButtonId();
            //Declaring a radioButton with the checkedId
            RadioButton checkedRadiobtn = (RadioButton) findViewById(checkedId);

            Spinner monthsSpinner = (Spinner) findViewById(R.id.spnMonths);

            String msg = "You have enrolled for " + checkedRadiobtn.getText() + " lessons in "
                    + monthsSpinner.getSelectedItem();

            //Displaying what they have enrolled in
            TextView tvEnrolled = (TextView) findViewById(R.id.tvOutput);
            tvEnrolled.setText(msg);
        }
    }
}


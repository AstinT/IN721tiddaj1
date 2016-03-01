package bit.tiddaj1.spinneradapterprac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

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
        Spinner monthtsSpinner = (Spinner) findViewById(R.id.spnMonths);
        //setting adapter to spinner
        monthtsSpinner.setAdapter(monthsAdapter);
    }
}

package bit.tiddaj1.musicschoolradiobtn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting button id
        Button btnConfirm = (Button) findViewById(R.id.btnConfirm);
        //setting onClickListener to btnConfirm
        btnConfirm.setOnClickListener(new buttonListener());
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

            //Displaying what they have enrolled in
            TextView tvEnrolled = (TextView) findViewById(R.id.tvEnrolled);
            tvEnrolled.setText("You have enrolled for " + checkedRadiobtn.getText() + " lessons.");
        }
    }
}
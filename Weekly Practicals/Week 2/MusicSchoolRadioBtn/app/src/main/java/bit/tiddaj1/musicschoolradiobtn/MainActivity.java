package bit.tiddaj1.musicschoolradiobtn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RadioGroup instrumentGroup = (RadioGroup) findViewById(R.id.rgInstruments);
        instrumentGroup.setOnCheckedChangeListener(new radioGroupListener());

        Button confirmButton = (Button) findViewById(R.id.btnConfirm);
        confirmButton.setOnClickListener(new buttonListener());
    }

    public class radioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            RadioButton chosenButton = (RadioButton) findViewById(checkedId);
            String chosenInstrument = chosenButton.getText().toString();
        }
    }

    public class buttonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {

        }
    }
}

package bit.tiddaj1.requestingdatatextcolour;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ChangeTextColour extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_text_colour);

        TextView tvColourBlue = (TextView) findViewById(R.id.tvBlue);
        //Grabs textview text colour
        int colour = tvColourBlue.getCurrentTextColor();

        //New intent
        Intent returnIntent = new Intent();

        //put data in intent
        returnIntent.putExtra("colour", colour);

        //Set the return code
        setResult(Activity.RESULT_OK, returnIntent);

        //pop off stack
        finish();
    }
}

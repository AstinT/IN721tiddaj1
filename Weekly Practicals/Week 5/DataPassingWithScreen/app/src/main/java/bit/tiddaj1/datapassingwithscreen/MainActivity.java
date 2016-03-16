package bit.tiddaj1.datapassingwithscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Getting a refernce to the button
        Button btnGoToSettings = (Button) findViewById(R.id.btnGoToSettings);
        //Setting onClickListener
        btnGoToSettings.setOnClickListener(new ChangeIntent());

        //Fetch the intent
        Intent launchIntent = getIntent();

        //Grab all the extra data
        Bundle allExtraData = launchIntent.getExtras();

        //Checks to see if there is any data in allExtraData
        /*
        If this code is not here app will crash because it will try grab data even if there is
        none there. The reason there will be no data there is when the app initial starts it doesn't
        come from my SettingsActivity activity.
         */
        if(allExtraData != null)
        {
            //Retrieve the data via its key
            String username = allExtraData.getString("username");

            //Use the data
            TextView tvUsername = (TextView) findViewById(R.id.tvUsername);
            tvUsername.setText(username);
        }
    }

    //Button listener
    public class ChangeIntent implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Setting up activity to change to
            Intent changeActivityIntent = new Intent(MainActivity.this, SettingsActivity.class);

            //Starts intent
            startActivity(changeActivityIntent);
        }
    }
}

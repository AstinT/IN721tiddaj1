package bit.tiddaj1.datapassingwithscreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        //Getting a refernce to the button
        Button btnReturnMain = (Button) findViewById(R.id.btnReturnMain);
        //Setting onClickListener
        btnReturnMain.setOnClickListener(new PassDataButtonClickHandler());
    }

    public class PassDataButtonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Setting up activity to change to
            Intent changeActivityIntent = new Intent(SettingsActivity.this, MainActivity.class);

            //Grabbing username from EditText
            EditText etEnterUsername = (EditText) findViewById(R.id.etEnterUsername);
            String username = etEnterUsername.getText().toString();

            if(username.length() < 5)
            {
                Toast.makeText(SettingsActivity.this,
                                "Please enter a username that is atleast 5 characters long",
                                Toast.LENGTH_LONG).show();
            }
            else
            {
                //Passing data to the intent
                changeActivityIntent.putExtra("username", username);

                //Starting activity
                startActivity(changeActivityIntent);
            }
        }
    }
}

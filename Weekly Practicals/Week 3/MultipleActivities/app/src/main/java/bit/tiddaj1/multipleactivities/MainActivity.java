package bit.tiddaj1.multipleactivities;

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

        //Declaring TextView title
        TextView title = (TextView) findViewById(R.id.tvScreenTitle);
        //Declaring Button changeActivity
        Button changeActivity = (Button) findViewById(R.id.btnChangeActivity);

        //Setting text of title
        title.setText(R.string.activityOneTitle);
        //Setting text of changeActivity
        changeActivity.setText(R.string.changeActivityButtonTitle);
        //Setting the onClickListener on changeActivity
        changeActivity.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            //Declaring intent
            Intent changeActivityIntent = new Intent(MainActivity.this, Activity2.class);
            //Starting intent
            startActivity(changeActivityIntent);
        }
    }
}
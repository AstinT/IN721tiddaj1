package bit.tiddaj1.multipleactivities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Declaring TextView title
        TextView title = (TextView) findViewById(R.id.tvScreenTitle);
        //Declaring Button changeActivity
        Button changeActivity = (Button) findViewById(R.id.btnChangeActivity);

        //Setting text of title
        title.setText(R.string.activityThreeTitle);
        //Setting text of changeActivity
        changeActivity.setText(R.string.openWebPageButtonTitle);
        //Setting the onClickListener on changeActivity
        changeActivity.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            //url address
            Uri goToOceOpGg = Uri.parse(getString(R.string.websiteAddress));
            //Implicit intent
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, goToOceOpGg);
            //Start intent
            startActivity(websiteIntent);
        }
    }
}

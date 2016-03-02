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

        TextView title = (TextView) findViewById(R.id.tvScreenTitle);
        Button changeActivity = (Button) findViewById(R.id.btnChangeActivity);

        title.setText(R.string.activityThreeTitle);
        changeActivity.setText(R.string.openWebPageButtonTitle);

        changeActivity.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            Uri goToOceOpGg = Uri.parse(getString(R.string.websiteAddress));
            Intent websiteIntent = new Intent(Intent.ACTION_VIEW, goToOceOpGg);
            startActivity(websiteIntent);
        }
    }
}


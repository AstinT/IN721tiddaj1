package bit.tiddaj1.multipleactivities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Activity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = (TextView) findViewById(R.id.tvScreenTitle);
        Button changeActivity = (Button) findViewById(R.id.btnChangeActivity);

        title.setText(R.string.activityTwoTitle);
        changeActivity.setText(R.string.changeActivityButtonTitle);

        changeActivity.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent changeActivityIntent = new Intent(Activity2.this, Activity3.class);
            startActivity(changeActivityIntent);
        }
    }
}

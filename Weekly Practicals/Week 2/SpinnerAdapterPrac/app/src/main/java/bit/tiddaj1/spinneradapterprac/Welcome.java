package bit.tiddaj1.spinneradapterprac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Declaring Button changeActivity
        Button changeActivity = (Button) findViewById(R.id.btnEnrollNow);
        //Setting the onClickListener on changeActivity
        changeActivity.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {
            //Declaring intent
            Intent changeActivityIntent = new Intent(Welcome.this, MainActivity.class);
            //Starting intent
            startActivity(changeActivityIntent);
        }
    }
}

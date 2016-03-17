package bit.tiddaj1.requestingdatatextcolour;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Global textview
    TextView tvContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting reference to a textview
        tvContent = (TextView) findViewById(R.id.tvContent);

        //getting reference to button
        Button btnStartForResult = (Button) findViewById(R.id.btnChangeColour);
        //setting onClickListener
        btnStartForResult.setOnClickListener(new StartForResultHandler());
    }

    //onClickListener
    public class StartForResultHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //intent to do work
            Intent changeActivityIntent = new Intent(MainActivity.this, ChangeTextColour.class);

            //start intent with code
            startActivityForResult(changeActivityIntent, 0);
        }
    }

    //Retrieving the requested data
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if ((requestCode == 0) && (resultCode == Activity.RESULT_OK))
        {
            //Set colour of text
            int colour = data.getIntExtra("colour", 0);
            tvContent.setTextColor(colour);
        }
    }
}

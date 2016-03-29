package bit.tiddaj1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        displayScore();

        //Reference to btnStartOver
        Button btnStartOver = (Button) findViewById(R.id.btnStartOver);
        btnStartOver.setOnClickListener(new buttonListener());
    }

    public void displayScore()
    {
        TextView tvResult = (TextView) findViewById(R.id.tvResult);
        //Gets score from Bundle
        Bundle extras = getIntent().getExtras();
        int score = extras.getInt("score");
        //Set score
        tvResult.setText(score + " / 11");
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener
    {
        @Override
        //onClick method
        public void onClick(View v)
        {
            //Go back to Welcome screen to start quiz again
            Intent goToWelcome = new Intent(ResultActivity.this, WelcomeActivity.class);
            startActivity(goToWelcome);
        }
    }
}

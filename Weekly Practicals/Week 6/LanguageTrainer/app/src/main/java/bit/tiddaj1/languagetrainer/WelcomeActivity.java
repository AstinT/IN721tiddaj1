package bit.tiddaj1.languagetrainer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //reference to btnStart
        Button btnStart = (Button) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener
    {
        @Override
        //onClick method
        public void onClick(View v)
        {
            //Go to QuestionActivity
            Intent goToQuestions = new Intent(WelcomeActivity.this, QuestionActivity.class);
            startActivity(goToQuestions);
        }
    }
}

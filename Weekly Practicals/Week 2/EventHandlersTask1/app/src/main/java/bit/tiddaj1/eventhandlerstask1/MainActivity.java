package bit.tiddaj1.eventhandlerstask1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Binding button to the handler
        Button button = (Button) findViewById(R.id.btn);

        buttonClickHandler clickHandler = new buttonClickHandler();
        buttonLongClickHandler longClickHandler = new buttonLongClickHandler();

        button.setOnClickListener(clickHandler);
        button.setOnLongClickListener(longClickHandler);
    }

    public class buttonClickHandler implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(MainActivity.this, "Not full click", Toast.LENGTH_LONG).show();
        }
    }

    public class buttonLongClickHandler implements View.OnLongClickListener {

        @Override
        public boolean onLongClick(View v) {
            Toast.makeText(MainActivity.this, "Full click", Toast.LENGTH_LONG).show();
            return false;
        }
    }
}

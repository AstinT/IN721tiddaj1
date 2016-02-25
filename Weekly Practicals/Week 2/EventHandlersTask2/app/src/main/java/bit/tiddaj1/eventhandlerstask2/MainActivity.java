package bit.tiddaj1.eventhandlerstask2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText editText = (EditText) findViewById(R.id.editText);
        keyListener listener = new keyListener();

        editText.setOnKeyListener(listener);
    }

    public class keyListener implements View.OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if ((keyCode == KeyEvent.KEYCODE_AT) && (event.getAction() == KeyEvent.ACTION_DOWN)) {
                Toast.makeText(MainActivity.this, "Don't type @", Toast.LENGTH_LONG).show();
            }
            return false;
        }
    }
}

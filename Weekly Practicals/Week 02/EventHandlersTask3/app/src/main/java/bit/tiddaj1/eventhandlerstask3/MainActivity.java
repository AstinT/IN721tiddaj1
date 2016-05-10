package bit.tiddaj1.eventhandlerstask3;

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

        EditText username = (EditText) findViewById(R.id.username);
        keyListener listener = new keyListener();

        username.setOnKeyListener(listener);
    }

    public class keyListener implements View.OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            int viewID = v.getId();
            EditText etUsernameInput = (EditText) findViewById(viewID);
            String username = etUsernameInput.getText().toString();

            if ((keyCode == KeyEvent.KEYCODE_ENTER) && (event.getAction() == KeyEvent.ACTION_DOWN)) {
                if(username.length() == 8) {
                    Toast.makeText(MainActivity.this, "Thank you " + username, Toast.LENGTH_LONG).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Usernames must be 8 characters, " + username, Toast.LENGTH_LONG).show();
                }
            }
            return false;
        }
    }
}

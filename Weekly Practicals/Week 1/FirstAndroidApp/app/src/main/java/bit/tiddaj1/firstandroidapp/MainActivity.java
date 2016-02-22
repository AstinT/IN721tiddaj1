package bit.tiddaj1.firstandroidapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView txtRandomString = (TextView)findViewById(R.id.dogBreed);

        String dogBreed = "";

        Random rand = new Random();
        int dogNum = rand.nextInt(4);

        switch(dogNum)
        {
            case 0:
                dogBreed = "Pug";
                break;

            case 1:
                dogBreed = "German Shepard";
                break;

            case 2:
                dogBreed = "Malamute";
                break;

            case 3:
                dogBreed = "Boxer";
                break;
        }

        txtRandomString.setText(dogBreed);
    }
}

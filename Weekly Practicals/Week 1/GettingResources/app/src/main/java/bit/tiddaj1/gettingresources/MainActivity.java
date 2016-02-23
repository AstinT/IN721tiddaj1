package bit.tiddaj1.gettingresources;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView title = (TextView)findViewById(R.id.title);

        //Create a Resources object
        Resources resourceResolver = getResources();
        int datesArray[] = resourceResolver.getIntArray(R.array.FebFridays);

        String febFridays = "February Fridays are on: ";

        for (int i = 0; i < datesArray.length; i++)
        {
            febFridays += datesArray[i] + " ";
        }

        title.setText(febFridays);
    }
}

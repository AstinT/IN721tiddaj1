package bit.tiddaj1.firstjson;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Fields
    private String JSONInput;
    private ArrayList<String> eventTitleList;
    ListView lvEventTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Load JSON
        loadJSON();
        eventTitleList = new ArrayList<>();

        //Get button reference
        Button btnFillList = (Button) findViewById(R.id.btnFillList);
        //Set on click listener
        btnFillList.setOnClickListener(new FillListViewClickHandler());

        //Get listview reference
        lvEventTitle = (ListView) findViewById(R.id.lvJsonItems);
        //Set on item click listener
        lvEventTitle.setOnItemClickListener(new SelectListViewItemHandler());
    }

    //Button listener
    public class FillListViewClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Loads arraylist with events title
            loadArrayListEventTitle();
            //Displays all event titles in eventTitleList
            loadListView();
        }
    }

    public void loadJSON()
    {
        //JSON file name in assets folder
        String jsonFileName = "dunedin_events.json";

        try
        {
            //Get an asset manager and create an input stream from the JSON file
            AssetManager am = getAssets();
            InputStream inputStream = am.open(jsonFileName);

            //Determine the number of bytes in file, and prepare buffer array for read
            int fileSizeInBytes = inputStream.available();
            byte[] JSONBuffer = new byte[fileSizeInBytes];

            //Read the stream into the buffer, and close it
            inputStream.read(JSONBuffer);
            inputStream.close();

            //Create a string from the byte[]
            JSONInput = new String(JSONBuffer);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void loadArrayListEventTitle()
    {
        try
        {
            //Navigate the JSON file to the event array
            JSONObject data = new JSONObject(JSONInput);
            JSONObject events = data.getJSONObject("events");
            JSONArray eventArray = events.getJSONArray("event");

            //Grabs the title of each event in event array and adds to eventTitleList
            for(int i = 0; i < eventArray.length(); i++)
            {
                JSONObject event = eventArray.getJSONObject(i);
                String title = event.getString("title");
                eventTitleList.add(title);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    //Creates an array adapter with the eventTitleList and sets it.
    public void loadListView()
    {
        ArrayAdapter lvEventTitleAdapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item, eventTitleList);

        lvEventTitle.setAdapter(lvEventTitleAdapter);
    }

    //List View onItemClickListener
    public class SelectListViewItemHandler implements AdapterView.OnItemClickListener
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            //passes int position
            displayToastEventDescription(position);
        }
    }

    public void displayToastEventDescription(int position)
    {
        try
        {
            //Navigate the JSON file to the event array
            JSONObject data = new JSONObject(JSONInput);
            JSONObject events = data.getJSONObject("events");
            JSONArray eventArray = events.getJSONArray("event");

            //Gets selected event
            JSONObject event = eventArray.getJSONObject(position);

            //Gets description
            String desc = event.getString("description");

            //Displays description
            Toast.makeText(this, desc, Toast.LENGTH_LONG).show();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}

package bit.tiddaj1.carlistview;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    //Global
    ArrayList<String> carMakeList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadFile();
        loadListView();
    }

    //Loads carMakeList with Strings from car_make_names.txt
    public void loadFile()
    {
        carMakeList = new ArrayList<String>();
        String fileName = "car_make_names.txt";

        //To use files from assets folder
        AssetManager am = getAssets();

        try
        {
            InputStream is = am.open(fileName);
            //Creating InputStreamReader
            InputStreamReader ir = new InputStreamReader(is);
            //Creating BufferedReader
            BufferedReader br = new BufferedReader(ir);

            String newCarMake;
            //Loops through car_makes_names.txt
            while ((newCarMake = br.readLine()) != null)
            {
                //Adds each string to carMakeList
                carMakeList.add(newCarMake);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    //Loads up listview with carMakeList
    public void loadListView()
    {
        //Reference to list view
        ListView lvCarMakes = (ListView) findViewById(R.id.lvCarMakeNames);

        //Creating adapter
        ArrayAdapter lvCarMakesadapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item, carMakeList);

        //setting adapter
        lvCarMakes.setAdapter(lvCarMakesadapter);
    }
}

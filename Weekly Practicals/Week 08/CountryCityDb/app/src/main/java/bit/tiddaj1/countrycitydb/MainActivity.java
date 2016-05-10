package bit.tiddaj1.countrycitydb;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    //Global
    SQLiteDatabase countryCityDb;
    Spinner spnCountry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference to btnSearch
        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        //Set on click listener
        btnSearch.setOnClickListener(new SearchDbClickHandler());

        createDatabase();
        loadSpinner();
    }

    //onClickListener
    public class SearchDbClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            loadListView();
        }
    }

    //Creates database
    public void createDatabase()
    {
        //Create database
        countryCityDb = openOrCreateDatabase("countryCityDb", MODE_PRIVATE, null);

        //Drop table
        String dropQuery = "DROP TABLE tblCity";
        countryCityDb.execSQL(dropQuery);

        //Create table
        String createQuery = "CREATE TABLE IF NOT EXISTS tblCity(" +
                             "cityID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                             "cityName TEXT NOT NULL, " +
                             "countryName TEXT NOT NULL);";

        countryCityDb.execSQL(createQuery);

        //Insert records
        //New Zealand Records
        countryCityDb.execSQL("INSERT INTO tblCity VALUES(null, 'Auckland', 'New Zealand')");
        countryCityDb.execSQL("INSERT INTO tblCity VALUES(null, 'Wellington', 'New Zealand')");
        countryCityDb.execSQL("INSERT INTO tblCity VALUES(null, 'Christchurch', 'New Zealand')");
        countryCityDb.execSQL("INSERT INTO tblCity VALUES(null, 'Dunedin', 'New Zealand')");

        //United States records
        countryCityDb.execSQL("INSERT INTO tblCity VALUES(null, 'Las Vegas', 'United States of America')");
        countryCityDb.execSQL("INSERT INTO tblCity VALUES(null, 'New York', 'United States of America')");
    }

    public void loadSpinner()
    {
        //Select query to get one of each country in tblCity
        String selectQuery = "SELECT DISTINCT countryName FROM tblCity";
        Cursor recordSet = countryCityDb.rawQuery(selectQuery, null);

        //Gets record count
        int recordCount = recordSet.getCount();
        String[] spinnerStringArray = new String[recordCount];

        int countryNameIndex = recordSet.getColumnIndex("countryName");

        recordSet.moveToFirst();

        for (int r = 0; r < recordCount; r++)
        {
            //get string
            String countryName = recordSet.getString(countryNameIndex);
            //put string in array
            spinnerStringArray[r] = countryName;

            recordSet.moveToNext();
        }

        //Reference to spinner spnCountry
        spnCountry = (Spinner) findViewById(R.id.spnCountry);

        //create adapter
        ArrayAdapter spinnerAdapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item, spinnerStringArray);

        //set adapter
        spnCountry.setAdapter(spinnerAdapter);
    }

    public String[] getCities(String selectedDropDown)
    {
        //Gets all cities where selectedDropDown
        String selectQuery = "SELECT cityName FROM tblCity WHERE countryName ='" + selectedDropDown +"';" ;
        Cursor recordSet = countryCityDb.rawQuery(selectQuery, null);

        int recordCount = recordSet.getCount();
        String[] citiesArray = new String[recordCount];

        int cityNameIndex = recordSet.getColumnIndex("cityName");

        recordSet.moveToFirst();

        for (int r = 0; r < recordCount; r++)
        {
            //Get city name from record set
            String cityName = recordSet.getString(cityNameIndex);
            //Add city to array
            citiesArray[r] = cityName;

            recordSet.moveToNext();
        }

        return citiesArray;
    }

    public void loadListView()
    {
        //Get selected drop down option
        String selectedDropDown = spnCountry.getSelectedItem().toString();

        //Search data base
        String[] citiesArray = getCities(selectedDropDown);

        //Create adapter
        ArrayAdapter listViewAdapter = new ArrayAdapter(this,
                R.layout.support_simple_spinner_dropdown_item, citiesArray);

        ListView lvCities = (ListView) findViewById(R.id.lvCities);

        //Set adapter
        lvCities.setAdapter(listViewAdapter);
    }
}

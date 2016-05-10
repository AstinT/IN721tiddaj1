package bit.tiddaj1.teleporterapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Constants
    private final int MAX_LATITUDE = 90;
    private final int MAX_LONGITUDE = 180;

    //Fields
    private double latitude;
    private double longitude;
    private String currCity;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        latitude = 0;
        longitude = 0;
        rand = new Random();

        Button btnTeleport = (Button) findViewById(R.id.btnTeleport);
        btnTeleport.setOnClickListener(new buttonTeleportHandler());
    }

    public class buttonTeleportHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            AsyncAPI APIThread = new AsyncAPI();
            APIThread.execute();
        }
    }

    public double CalLatOrLon(int bound)
    {
        double result = rand.nextDouble() * bound;

        int value = rand.nextInt(2);

        if (value == 1)
        {
            return result * -1;
        }
        else
        {
            return result;
        }
    }

    public void Output()
    {
        DecimalFormat df = new DecimalFormat("#.###");

        TextView tvLat = (TextView) findViewById(R.id.tvLatVal);
        tvLat.setText(df.format(latitude));

        TextView tvLon = (TextView) findViewById(R.id.tvLonVal);
        tvLon.setText(df.format(longitude));

        TextView tvCityVal = (TextView) findViewById(R.id.tvCityVal);
        tvCityVal.setText(currCity);
    }

    public class AsyncAPI extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params)
        {
            String cityData = "[[]]";

            while(cityData.equals("[[]]"))
            {
                latitude = CalLatOrLon(MAX_LATITUDE);
                longitude = CalLatOrLon(MAX_LONGITUDE);

                String urlString = "http://www.geoplugin.net/extras/location.gp" +
                        "?lat=" + latitude +
                        "&long=" + longitude +
                        "&format=json";

                cityData = getJSONFromUrl(urlString);
            }

            return cityData;
        }

        protected void onPostExecute(String fetchedString)
        {
            currCity = getCity(fetchedString);
            Output();
        }
    }

    public String getJSONFromUrl(String urlString)
    {
        String JSONData = null;

        try
        {
            URL URLObject = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

            connection.connect();

            //int responseCode = connection.getResponseCode();

            InputStream inputStream = connection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String responseString;
            StringBuilder stringBuilder = new StringBuilder();
            while ((responseString = bufferedReader.readLine()) != null)
            {
                stringBuilder = stringBuilder.append(responseString);
            }

            JSONData = stringBuilder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return JSONData;
    }

    public String getCity(String fetchedString)
    {
        String cityName = "";

        try
        {
            JSONObject data = new JSONObject(fetchedString);
            cityName = data.getString("geoplugin_place");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return cityName;
    }
}

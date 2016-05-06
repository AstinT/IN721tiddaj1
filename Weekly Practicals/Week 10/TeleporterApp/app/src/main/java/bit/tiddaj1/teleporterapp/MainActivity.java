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

    private final int MAX_LONGITUDE = 180;
    private final int MAX_LATITUDE = 90;
    private Random rand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rand = new Random();

        Button btnTeleport = (Button) findViewById(R.id.btnTeleport);
        btnTeleport.setOnClickListener(new buttonTeleportHandler());
    }

    public class buttonTeleportHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            double longitude = CalLonOrLat(MAX_LONGITUDE);
            double latitude = CalLonOrLat(MAX_LATITUDE);

            Output(longitude, latitude);
        }
    }

    public double CalLonOrLat(int bound)
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

    public void Output(double longitude, double latitude)
    {
        DecimalFormat df = new DecimalFormat("#.###");

        TextView tvLon = (TextView) findViewById(R.id.tvLonVal);
        tvLon.setText(df.format(longitude));

        TextView tvLat = (TextView) findViewById(R.id.tvLatVal);
        tvLat.setText(df.format(latitude));
    }

    public class AsyncAPI extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params)
        {
            String JSONString = null;
            try
            {
                String urlString = "";

                URL URLObject = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                connection.connect();

                int responseCode = connection.getResponseCode();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String responseString;
                StringBuilder stringBuilder = new StringBuilder();
                while ((responseString = bufferedReader.readLine()) != null)
                {
                    stringBuilder = stringBuilder.append(responseString);
                }

                JSONString = stringBuilder.toString();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return JSONString;
        }

        protected void onPostExecute(String fetchedString)
        {
        }
    }
}

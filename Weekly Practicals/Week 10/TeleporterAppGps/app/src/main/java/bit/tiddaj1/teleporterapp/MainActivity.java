package bit.tiddaj1.teleporterapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private final String PD_MESSAGE = "Loading...";
    private final String NO_RESULT = "[[]]";

    //Fields
    private double latitude;
    private double longitude;
    private String currCity;
    private Bitmap cityImg;
    private Random rand;

    //Screen components
    private TextView tvLat;
    private TextView tvLon;
    private TextView tvCityVal;
    private ImageView ivCityPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing
        latitude = 0;
        longitude = 0;
        cityImg = null;
        rand = new Random();

        //Getting references to screen components
        tvLat = (TextView) findViewById(R.id.tvLatVal);
        tvLon = (TextView) findViewById(R.id.tvLonVal);
        tvCityVal = (TextView) findViewById(R.id.tvCityVal);
        ivCityPic = (ImageView) findViewById(R.id.ivCityPic);

        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria defaultCri = new Criteria();
        String provider = lm.getBestProvider(defaultCri, false);
        lm.requestLocationUpdates(provider, 400, 1, new CustomLocationListener());
    }

    private class CustomLocationListener implements LocationListener
    {
        @Override
        public void onLocationChanged(Location location)
        {
            latitude = location.getLatitude();
            longitude = location.getLongitude();

            //New AsyncAPI
            AsyncAPI APIThread = new AsyncAPI();
            //Call execute method
            APIThread.execute();
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }
        @Override
        public void onProviderEnabled(String provider) {

        }
        @Override
        public void onProviderDisabled(String provider) {

        }
    }

    //Outputs all information to screen components
    public void Output()
    {
        DecimalFormat df = new DecimalFormat("#.###");

        tvLat.setText(df.format(latitude));
        tvLon.setText(df.format(longitude));
        tvCityVal.setText(currCity);
        ivCityPic.setImageBitmap(cityImg);
    }

    //Async class. Returns String to onPostExecute
    public class AsyncAPI extends AsyncTask<Void, Void, String>
    {
        @Override
        protected String doInBackground(Void... params)
        {
            String cityData = "";

            //Gets the geoplugin url
            String geoUrlString = getGeoPluginUrl();

            //Converts JSON to string
            cityData = getJSONFromUrl(geoUrlString);

            if(!cityData.equals(NO_RESULT))
            {
                //Gets the flickr url
                String flickrUrlString = getFlickrUrl(getCity(cityData));

                //Converts JSON to string
                String flickrData = getJSONFromUrl(flickrUrlString);

                //Gets img url
                String imgUrl = getBitmapUrl(flickrData);

                //Gets img from url
                cityImg = getBitmap(imgUrl);
            }
            else
            {
                String imgUrl = "http://tourbillontrailers.co/wp-content/uploads/2016/02/no-image-found.jpg";
                cityImg = getBitmap(imgUrl);
            }

            //Returns lump data
            return cityData;
        }

        @Override
        protected void onPostExecute(String fetchedString)
        {
            if(!fetchedString.equals(NO_RESULT))
            {
                //Sets currCity
                currCity = getCity(fetchedString);
            }
            else
            {
                currCity = "No city found";
            }

            //Calls output
            Output();
        }
    }

    //Gets JSON from a specified url and retuns it
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

    //Gets first city name in JSON file and returns it
    public String getCity(String fetchedString)
    {
        String cityName = "";

        try
        {
            //Walks through JSON
            JSONObject data = new JSONObject(fetchedString);
            cityName = data.getString("geoplugin_place");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        return cityName;
    }

    //Returns the geoplugin url
    public String getGeoPluginUrl()
    {
        String url = "http://www.geoplugin.net/extras/location.gp" +
                     "?lat=" + latitude +
                     "&long=" + longitude +
                     "&format=json";

        return url;
    }

    //Returns the flickr url
    public String getFlickrUrl(String tag)
    {
        String url =  "https://api.flickr.com/services/rest/?" +
                      "method=flickr.photos.search" +
                      "&api_key=fb44e9c23a523fc8dbe82dafb3e900b5" +
                      "&tags=" + tag +
                      "&privacy_filter=1" +
                      "&format=json" +
                      "&nojsoncallback=1";

        return url;
    }

    //Builds the image url and returns it
    public String getBitmapUrl(String flickrData)
    {
        //Empty string
        String imgUrl = "";

        try
        {
            //Walks through JSON
            JSONObject data = new JSONObject(flickrData);
            JSONObject photos = data.getJSONObject("photos");
            JSONArray photo = photos.getJSONArray("photo");

            //Checks there is a photo to grab
            if (photo.length() != 0)
            {
                JSONObject image = photo.getJSONObject(0);

                String farmId = image.getString("farm"); //Fard id
                String serverId = image.getString("server"); //Server id
                String imageId = image.getString("id"); //image id
                String secretId = image.getString("secret"); //secret id

                //builds image url
                imgUrl = "https://farm" + farmId +
                        ".staticflickr.com/" + serverId +
                        "/" + imageId +
                        "_" + secretId +
                        ".jpg";
            }
            else
            {
                imgUrl = "http://tourbillontrailers.co/wp-content/uploads/2016/02/no-image-found.jpg";
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        //return built image url
        return imgUrl;
    }

    //Gets a bitmap with the passed in URL and returns a bitmap
    public Bitmap getBitmap(String imgUrl)
    {
        Bitmap img = null;

        try
        {
            URL URLObjectBitmap = new URL(imgUrl);
            HttpURLConnection connectionBitmap = (HttpURLConnection) URLObjectBitmap.openConnection();
            //Makes a connection
            connectionBitmap.connect();
            InputStream inputStreamBitmap = connectionBitmap.getInputStream();
            //Builds bitmap
            img = BitmapFactory.decodeStream(inputStreamBitmap);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //returning bitmap
        return img;
    }
}

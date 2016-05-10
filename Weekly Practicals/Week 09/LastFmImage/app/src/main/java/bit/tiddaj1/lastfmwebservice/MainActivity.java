package bit.tiddaj1.lastfmwebservice;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> artistList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        artistList = new ArrayList<String>();

        Button btnFilList = (Button) findViewById(R.id.btnFillList);
        btnFilList.setOnClickListener(new buttonFillListHandler());
    }


    public class buttonFillListHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            AsyncAPI APIThread = new AsyncAPI();
            APIThread.execute();
        }
    }

    public class AsyncAPI extends AsyncTask<Void, Void, Bitmap>
    {
        @Override
        protected Bitmap doInBackground(Void... params)
        {
            String JSONString;
            Bitmap bitmapImage = null;

            try
            {
                String urlString = "http://ws.audioscrobbler.com/2.0/" +
                                   "?method=chart.gettopartists" +
                                   "&api_key=58384a2141a4b9737eacb9d0989b8a8c" +
                                   "&limit=20" +
                                   "&format=json";

                URL URLObject = new URL(urlString);

                HttpURLConnection connection = (HttpURLConnection) URLObject.openConnection();

                connection.connect();

                int responseCode = connection.getResponseCode();

                InputStream inputStream = connection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String responseString;
                StringBuilder stringBuilder = new StringBuilder();
                while((responseString = bufferedReader.readLine()) != null)
                {
                    stringBuilder = stringBuilder.append(responseString);
                }

                JSONString = stringBuilder.toString();

                JSONObject data = new JSONObject(JSONString);
                JSONObject artists = data.getJSONObject("artists");
                JSONArray artist = artists.getJSONArray("artist");
                JSONObject firstArtist = artist.getJSONObject(0);
                JSONArray image = firstArtist.getJSONArray("image");
                JSONObject imageMed = image.getJSONObject(1);
                String imgUrl = imageMed.getString("#text");

                URL URLObjectBitmap = new URL(imgUrl);
                HttpURLConnection connectionBitmap = (HttpURLConnection) URLObjectBitmap.openConnection();
                connectionBitmap.connect();
                InputStream inputStreamBitmap = connectionBitmap.getInputStream();
                bitmapImage = BitmapFactory.decodeStream(inputStreamBitmap);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            return bitmapImage;
        }

        protected void onPostExecute(Bitmap image)
        {
            ImageView imageView = (ImageView) findViewById(R.id.imageView);
            imageView.setImageBitmap(image);
        }
    }
}

package bit.tiddaj1.lastfmwebservice;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                while((responseString = bufferedReader.readLine()) != null)
                {
                    stringBuilder = stringBuilder.append(responseString);
                }

                JSONString = stringBuilder.toString();
            }
            catch(Exception e)
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

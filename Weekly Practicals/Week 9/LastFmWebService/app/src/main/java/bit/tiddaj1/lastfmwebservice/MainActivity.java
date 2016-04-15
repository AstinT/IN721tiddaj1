package bit.tiddaj1.lastfmwebservice;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

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

    List<Artist> artistList = new ArrayList<Artist>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFilList = (Button) findViewById(R.id.btnFillList);
        btnFilList.setOnClickListener(new buttonFillListHandler());
    }

    public void loadListView()
    {
        ArtistArrayAdapter artistArrayAdapter = new ArtistArrayAdapter(this,
                R.layout.custom_listview_item, artistList);

        ListView listView = (ListView) findViewById(R.id.lvListView);
        listView.setAdapter(artistArrayAdapter);
    }

    public class buttonFillListHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            loadListView();

            AsyncAPI APIThread = new AsyncAPI();

            EditText etSearch = (EditText) findViewById(R.id.etSearch);
            String searchedArtist = etSearch.getText().toString();
            APIThread.execute(searchedArtist);
        }
    }

    public class ArtistArrayAdapter extends ArrayAdapter<Artist>
    {
        public ArtistArrayAdapter(Context context, int resource, List<Artist> objects)
        {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container)
        {
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);

            View customView = inflater.inflate(R.layout.custom_listview_item, container, false);

            TextView tvName = (TextView) customView.findViewById(R.id.tvName);
            TextView tvListenerCount = (TextView) customView.findViewById(R.id.tvListnerCount);

            Artist currItem = getItem(position);

            tvName.setText(currItem.getName());
            tvListenerCount.setText(currItem.getListenerCount() + "");

            return customView;
        }
    }

    public class AsyncAPI extends AsyncTask<String, Void, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            String JSONString = null;
            String searchArtist = params[0];
            try
            {
                String urlString = "http://ws.audioscrobbler.com/2.0/" +
                                   "?method=artist.getsimilar" +
                                   "&artist=" + searchArtist +
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
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return JSONString;
        }

        protected void onPostExecute(String fetchedString)
        {
            try
            {
                JSONObject data = new JSONObject(fetchedString);
                JSONObject artists = data.getJSONObject("artists");
                JSONArray artist = artists.getJSONArray("artist");

                for (int i = 0; i < artist.length(); i++)
                {
                    JSONObject currJSONArtist = artist.getJSONObject(i);
                    Artist currArtist = new Artist(currJSONArtist.getString("name"), currJSONArtist.getInt("listeners"));
                    artistList.add(currArtist);
                }
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        }
    }
}

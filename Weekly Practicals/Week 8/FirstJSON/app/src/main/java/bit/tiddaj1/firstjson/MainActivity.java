package bit.tiddaj1.firstjson;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFillList = (Button) findViewById(R.id.btnFillList);
        btnFillList.setOnClickListener(new FillListViewClickHandler());
    }

    //Button listener
    public class FillListViewClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            fillList();
        }
    }

    public void fillList()
    {
        String jsonFileName = "dunedin_events.json";

        //TRY CATCH!!!!!!!!!!!!!!!

        AssetManager am = getAssets();
        InputStream inputStream = am.open(jsonFileName);

        int fileSizeInBytes = inputStream.available();
        byte[] JSONBuffer = new byte[fileSizeInBytes];

        inputStream.read(JSONBuffer);
        inputStream.close();

        String JSONInput = new String(JSONBuffer);


    }
}

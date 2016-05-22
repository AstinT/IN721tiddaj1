package bit.tiddaj1.toolbarapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView ivPic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference to imageview
        ivPic = (ImageView) findViewById(R.id.ivPic);
    }

    @Override
    //Create tool bars
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        //inflates from the xml I created
        menuInflater.inflate(R.menu.menu_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //Gets selected item title
        String itemTitle = (String) item.getTitle();

        //Checks on the selected item title
        switch (itemTitle)
        {
            case "Up":
                //Moves image up
                ivPic.setY(ivPic.getY() - 10);
                break;
            case "Down":
                //Moves image down
                ivPic.setY(ivPic.getY() + 10);
                break;
            case "Left":
                //Moves image left
                ivPic.setX(ivPic.getX() - 10);
                break;
            case "Right":
                //Moves image right
                ivPic.setX(ivPic.getX() + 10);
                break;
        }

        return true;
    }
}

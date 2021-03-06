package bit.tiddaj1.allaboutdunedinnavdrawer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Services extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscreen);

        //Declaring a TextView and ImageView. Getting there ids
        TextView subScreenTitle = (TextView) findViewById(R.id.tvSubScreenTitle);
        ImageView subScreenImage = (ImageView) findViewById(R.id.ivSubScreenImage);

        //Setting text and image for the TextView and ImageView
        subScreenTitle.setText(R.string.subScreenTitleServices);
        subScreenImage.setImageResource(R.drawable.library);
    }
}

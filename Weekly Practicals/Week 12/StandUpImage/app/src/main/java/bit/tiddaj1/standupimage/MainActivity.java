package bit.tiddaj1.standupimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    //Global
    private ImageView zonda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference to imageview
        zonda = (ImageView) findViewById(R.id.ivZonda);

        //Reference to button
        Button btnAnimate = (Button) findViewById(R.id.btnAnimate);
        //Set onClickListener
        btnAnimate.setOnClickListener(new buttonClickHandler());
    }

    public class buttonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Animate
            YoYo.with(Techniques.StandUp)
                    .duration(700)
                    .playOn(zonda);
        }
    }
}

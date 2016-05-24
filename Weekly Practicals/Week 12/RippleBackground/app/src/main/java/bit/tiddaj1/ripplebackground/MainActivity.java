package bit.tiddaj1.ripplebackground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.skyfishjy.library.RippleBackground;

public class MainActivity extends AppCompatActivity {

    //Fields
    private RippleBackground rippleBackground;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Sets running to flase because it is not running yet
        running = false;

        //Reference to ripplebackground container
        rippleBackground = (RippleBackground)findViewById(R.id.content);
        //Reference to the image view
        ImageView imageView = (ImageView)findViewById(R.id.centerImage);

        //Set onclick listener
        imageView.setOnClickListener(new RippleHandler());
    }

    public class RippleHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //if not running, start animation
            if(running == false)
            {
                rippleBackground.startRippleAnimation();
                running = true;
            }
            else
            {
                //If already running turn off
                rippleBackground.stopRippleAnimation();
                running = false;
            }
        }
    }
}

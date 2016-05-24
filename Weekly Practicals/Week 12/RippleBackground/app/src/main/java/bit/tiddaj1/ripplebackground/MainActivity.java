package bit.tiddaj1.ripplebackground;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        //Reference to button
        Button btnRipple = (Button) findViewById(R.id.btnRipple);
        //Setting onClickListener
        btnRipple.setOnClickListener(new buttonClickHandler());
    }

    public class buttonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //if not running, start animation
            if(!running)
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

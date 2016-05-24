package bit.tiddaj1.explodingimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {

    //Global
    private ExplodeAnimation explodeAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference to zonda image
        ImageView zonda = (ImageView) findViewById(R.id.ivZonda);

        //New exploding animation, passed image
        explodeAnimation = new ExplodeAnimation(zonda);
        //Set listener. Code is ran after animation
        explodeAnimation.setListener(new animationHandler());

        //Reference to button
        Button btnExplode = (Button) findViewById(R.id.btnExplode);
        //Set onClickListener
        btnExplode.setOnClickListener(new buttonClickHandler());
    }

    public class buttonClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //Animate
            explodeAnimation.animate();
        }
    }

    public class animationHandler implements AnimationListener
    {
        @Override
        public void onAnimationEnd(Animation animation)
        {
            //In here I wanted to reset the image
        }
    }
}

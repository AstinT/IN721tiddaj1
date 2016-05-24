package bit.tiddaj1.explodingimage;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.easyandroidanimations.library.Animation;
import com.easyandroidanimations.library.AnimationListener;
import com.easyandroidanimations.library.ExplodeAnimation;

public class MainActivity extends AppCompatActivity {

    //Global
    ImageView zonda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Reference to zonda image
        zonda = (ImageView) findViewById(R.id.ivZonda);
        //Set image onClickListener
        zonda.setOnClickListener(new imageClickHandler());
    }

    public class imageClickHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            //New exploding animation, passed View v which is my image
            ExplodeAnimation explodeAnimation = new ExplodeAnimation(v);
            //Set listener. Code is ran after animation
            explodeAnimation.setListener(new animationHandler());
            //Animate the view
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

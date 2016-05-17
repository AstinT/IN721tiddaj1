package bit.tiddaj1.accelanimation;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity
{
    //Speed picture will move
    final int VELOCITY = 5;

    //Globals
    SensorManager sm;
    Sensor accelSensor;
    AccelSensorOnChangeHandler accelHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get sensor service
        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //Gets a refernce to accelerometer
        accelSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //New inner class instance of AccelSensorOnChangeHandler
        accelHandler = new AccelSensorOnChangeHandler();
    }

    //Accelerometere handler
    public class AccelSensorOnChangeHandler implements SensorEventListener
    {
        @Override
        //Gets called when sensor values have been changed
        public void onSensorChanged(SensorEvent event)
        {
            //Grab x and y
            float x = event.values[0];
            float y = event.values[1];

            //Passes x an y to MoveImage
            MoveImage(x, y);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
            //Nothing here...
        }
    }

    //Moves the ImageView
    public void MoveImage(float x, float y)
    {
        //Refernce to Image view
        ImageView ivSmiley = (ImageView) findViewById(R.id.ivSmiley);

        //x is greater than 0, move image to left
        if (x > 0)
        {
            ivSmiley.setX(ivSmiley.getX() - VELOCITY);
        }
        //x is less than 0, move image to left
        else
        {
            ivSmiley.setX(ivSmiley.getX() + VELOCITY);
        }

        //y is greater than 0, move image to down
        if (y > 0)
        {
            ivSmiley.setY(ivSmiley.getY() + VELOCITY);
        }
        //y is less than 0, move image to up
        else
        {
            ivSmiley.setY(ivSmiley.getY() - VELOCITY);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sm.registerListener(accelHandler, accelSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sm.unregisterListener(accelHandler);
    }
}

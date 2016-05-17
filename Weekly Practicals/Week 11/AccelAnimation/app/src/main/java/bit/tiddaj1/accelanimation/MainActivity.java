package bit.tiddaj1.accelanimation;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
    SensorManager sm;
    Sensor accelSensor;
    AccelSensorOnChangeHandler accelHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accelHandler = new AccelSensorOnChangeHandler();
    }

    public class AccelSensorOnChangeHandler implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        sm.registerListener(accelHandler, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sm.unregisterListener(accelHandler);
    }
}

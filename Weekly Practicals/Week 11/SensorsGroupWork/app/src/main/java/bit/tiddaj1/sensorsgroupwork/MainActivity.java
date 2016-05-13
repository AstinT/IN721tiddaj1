package bit.tiddaj1.sensorsgroupwork;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tvSensorList;
    TextView tvLuxOutput;
    TextView tvX;
    TextView tvY;
    TextView tvZ;
    List<Sensor> sensorsList;
    SensorManager sm;
    Sensor lightSensor;
    Sensor accelSensor;
    LightSensorOnChangeHandler lightHandler;
    AccelSensorOnChangeHandler accelHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lightHandler = new LightSensorOnChangeHandler();
        accelHandler = new AccelSensorOnChangeHandler();

        sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lightSensor = sm.getDefaultSensor(Sensor.TYPE_LIGHT);
        accelSensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        tvSensorList = (TextView) findViewById(R.id.tvSensorList);
        tvLuxOutput = (TextView) findViewById(R.id.tvLuxOutput);
        tvX = (TextView) findViewById(R.id.tvX);
        tvY = (TextView) findViewById(R.id.tvY);
        tvZ = (TextView) findViewById(R.id.tvZ);

        sensorsList = getSensorList();

        OutputSensorList();
    }

    public List<Sensor> getSensorList()
    {
        return sm.getSensorList(Sensor.TYPE_ALL);
    }

    public void OutputSensorList()
    {
        String output = "";

        for (Sensor sensor : sensorsList)
        {
            output += sensor.getName() + "\n";
        }

        tvSensorList.setText(output);
    }

    public class LightSensorOnChangeHandler implements SensorEventListener
    {
        @Override
        public void onSensorChanged(SensorEvent event)
        {
            float lux = event.values[0];

            tvLuxOutput.setText(lux + "");
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy)
        {
        }
    }

    public class AccelSensorOnChangeHandler implements SensorEventListener
    {

        @Override
        public void onSensorChanged(SensorEvent event)
        {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            tvX.setText("X: " + x);
            tvY.setText("Y: " + y);
            tvZ.setText("Z: " + z);
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
        sm.registerListener(lightHandler, lightSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sm.registerListener(accelHandler, accelSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        sm.unregisterListener(lightHandler);
        sm.unregisterListener(accelHandler);
    }
}

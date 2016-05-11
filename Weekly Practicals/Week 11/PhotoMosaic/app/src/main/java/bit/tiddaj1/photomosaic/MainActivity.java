package bit.tiddaj1.photomosaic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    private File imgFile;
    private Bitmap img;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgFile = null;

        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new CameraButtonHandler());
    }

    public class CameraButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            imgFile = CreateUniqueFile();
            StartCameraApp(imgFile);
        }
    }

    //Creates a unique file with a timestamp. File type is jpg.
    public File CreateUniqueFile()
    {
        //Fetch system image folder
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //Make subdirectory
        File imgStroageDir = new File(imageRootPath, "PhotoMosaic");
        if (!imgStroageDir.exists())
        {
            imgStroageDir.mkdirs(); //mkdirs creates parent directories as required
        }

        //Get timestamp
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        //Make file name
        String imgFileName = "IMG_" + timeStamp + ".jpg";

        //Make file object from directory and file name
        File file = new File(imgStroageDir.getPath() + File.separator + imgFileName);

        //Return file
        return file;
    }

    //Passed in a time stamped file to hold image
    public void StartCameraApp(File imgFile)
    {
        //Generate Uri from passed in file
        Uri imgFileUri = Uri.fromFile(imgFile);

        Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgFileUri);

        startActivityForResult(imageCaptureIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                String realFilePath = imgFile.getPath();

                img = BitmapFactory.decodeFile(realFilePath);

                ImageView iv = (ImageView) findViewById(R.id.ivOne);
                iv.setImageBitmap(img);
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "No photo saved.", Toast.LENGTH_LONG).show();
        }
    }
}

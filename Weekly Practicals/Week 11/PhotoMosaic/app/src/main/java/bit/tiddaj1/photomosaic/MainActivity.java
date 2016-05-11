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
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity
{
    //Fields
    File imgFile;
    ArrayList<ImageView> imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LoadImageViewList();

        //Sets  OnClickListener
        Button btnCamera = (Button) findViewById(R.id.btnCamera);
        btnCamera.setOnClickListener(new CameraButtonHandler());
    }

    //Loads array list with all image views
    public void LoadImageViewList()
    {
        //new array list
        imageViews = new ArrayList<>();

        //Get reference to all image views
        ImageView ivOne = (ImageView) findViewById(R.id.ivOne);
        ImageView ivTwo = (ImageView) findViewById(R.id.ivTwo);
        ImageView ivThree = (ImageView) findViewById(R.id.ivThree);
        ImageView ivFour = (ImageView) findViewById(R.id.ivFour);

        //Adds them to the list
        imageViews.add(ivOne);
        imageViews.add(ivTwo);
        imageViews.add(ivThree);
        imageViews.add(ivFour);
    }

    //Loads all image views with the same bitmap
    public void LoadBitmapToImageviews(Bitmap img)
    {
        for ( ImageView iv : imageViews )
        {
            iv.setImageBitmap(img);
        }
    }

    //OnClickListener
    public class CameraButtonHandler implements View.OnClickListener
    {
        @Override
        public void onClick(View v)
        {
            StartCameraApp();
        }
    }

    //Creates a unique file with a timestamp. File type is jpg.
    public File CreateUniqueFile()
    {
        //Fetch system image folder
        File imageRootPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

        //Make subdirectory
        File imgStroageDir = new File(imageRootPath, "CameraDemo1");
        if (!imgStroageDir.exists())
        {
            imgStroageDir.mkdirs(); //mkdirs creates parent directories as required
        }

        //Get timestamp
        SimpleDateFormat timeStampFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        Date currentTime = new Date();
        String timeStamp = timeStampFormat.format(currentTime);

        //Make file name
        String imgName = "IMG_" + timeStamp + ".jpg";

        //Make file object from directory and file name
        //Return it
        return new File(imgStroageDir.getPath() + File.separator + imgName);
    }

    //Passed in a time stamped file to hold image
    public void StartCameraApp()
    {
        //Creating file
        imgFile = CreateUniqueFile();

        //Generate Uri from passed in file
        Uri imgFileUri = Uri.fromFile(imgFile);

        //Create an intent for the image capture content provider
        Intent imageCaptureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Attach your uri to the intent
        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT, imgFileUri);

        //Launch the intent
        startActivityForResult(imageCaptureIntent, 1);
    }

    //Callback method
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        //Request code set when we launched intent
        if (requestCode == 1)
        {
            //Checking if success
            if (resultCode == RESULT_OK)
            {
                //File path to img
                String realFilePath = imgFile.getPath();

                //Makes bitmap
                Bitmap img = BitmapFactory.decodeFile(realFilePath);

                //Passes bitmap to the image views to be set
                LoadBitmapToImageviews(img);
            }
        }
        else
        {
            Toast.makeText(MainActivity.this, "No photo saved.", Toast.LENGTH_LONG).show();
        }
    }
}
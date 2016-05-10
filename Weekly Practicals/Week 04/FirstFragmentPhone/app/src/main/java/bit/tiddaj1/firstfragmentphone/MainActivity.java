package bit.tiddaj1.firstfragmentphone;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting refernces to the buttons
        Button btnImageFragment = (Button) findViewById(R.id.btnShowImage);
        Button btnListFragment = (Button) findViewById(R.id.btnShowList);

        //setting onClicklistener for buttons
        btnImageFragment.setOnClickListener(new buttonListener());
        btnListFragment.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {

            //Checks what button is being clicked
            if(v.getId() == R.id.btnShowImage)
            {
                //Makes new ShowImageFragment
                Fragment dynamicFragment = new ShowImageFragment();
                //Builds the fragment
                buildFragment(dynamicFragment);
            }
            else
            {
                //Makes new ShowListFragment
                Fragment dynamicFragment = new ShowListFragment();
                //Builds the fragment
                buildFragment(dynamicFragment);
            }
        }
    }

    //Builds fragment
    public void buildFragment(Fragment dynamicFragment) {

        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, dynamicFragment);
        ft.commit();
    }
}

package bit.tiddaj1.firstfragmenttablet;

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
                //container to set
                int container = R.id.image_fragment_container;
                //Builds the fragment
                buildFragment(dynamicFragment, container);
            }
            else
            {
                //Makes new ShowListFragment
                Fragment dynamicFragment = new ShowListFragment();
                //container to set
                int container = R.id.list_fragment_container;
                //Builds the fragment
                buildFragment(dynamicFragment, container);
            }
        }
    }

    //Builds fragment
    public void buildFragment(Fragment dynamicFragment, int container ) {

        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(container, dynamicFragment);
        ft.commit();
    }
}

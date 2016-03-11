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

        Button btnImageFragment = (Button) findViewById(R.id.btnShowImage);
        Button btnListFragment = (Button) findViewById(R.id.btnShowList);

        btnImageFragment.setOnClickListener(new buttonListener());
        btnListFragment.setOnClickListener(new buttonListener());
    }

    //Button listener class
    public class buttonListener implements Button.OnClickListener {

        @Override
        public void onClick(View v) {

            if(v.getId() == R.id.btnShowImage)
            {
                Fragment dynamicFragment = new ShowImageFragment();
                buildFragment(dynamicFragment);
            }
            else
            {
                Fragment dynamicFragment = new ShowListFragment();
                buildFragment(dynamicFragment);
            }
        }
    }

    public void buildFragment(Fragment dynamicFragment) {

        FragmentManager fm = getFragmentManager();

        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment_container, dynamicFragment);
        ft.commit();
    }
}

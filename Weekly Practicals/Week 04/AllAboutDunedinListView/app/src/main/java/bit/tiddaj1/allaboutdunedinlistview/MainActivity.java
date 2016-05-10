package bit.tiddaj1.allaboutdunedinlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    //declaring a listview
    ListView activitiesGroupListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting listview id
        activitiesGroupListView = (ListView) findViewById(R.id.lvActivities);

        //setting up listview with items
        setUpActivitiesGroupList();

        //setting onItemClickListener for listview
        activitiesGroupListView.setOnItemClickListener(new ActivitiesGroupNavListClickHandler());
    }

    public void setUpActivitiesGroupList() {

        //ListView options
        String[] groups = {"Services", "Fun Things To Do", "Dining", "Shopping"};

        //Creating adapter
        ArrayAdapter<String> activitiesGroupAdapter = new ArrayAdapter<String>(this, R.layout.activities_group_list_item, groups);

        //setting adapter to listview
        activitiesGroupListView.setAdapter(activitiesGroupAdapter);
    }

    public class ActivitiesGroupNavListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> list, View view, int position, long id)
        {
            //gets clicked item
            String clickedItem = (String) list.getItemAtPosition(position);
            Intent goToIntent;

            //checks what item is clicked and sends user to the appropriate screen
            switch (clickedItem) {
                case "Services":
                    goToIntent = new Intent(MainActivity.this, Services.class);
                    break;
                case "Fun Things To Do":
                    goToIntent = new Intent(MainActivity.this, FunThingsToDo.class);
                    break;
                case "Dining":
                    goToIntent = new Intent(MainActivity.this, Dining.class);
                    break;
                case "Shopping":
                    goToIntent = new Intent(MainActivity.this, Shopping.class);
                    break;
                default:
                    goToIntent = null;
            }

            if (goToIntent != null)
                startActivity(goToIntent);
        }
    }
}

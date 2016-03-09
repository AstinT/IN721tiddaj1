package bit.tiddaj1.allaboutdunedinlistview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView activitiesGroupListView = (ListView) findViewById(R.id.lvActivities);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setUpActivitiesGroupList() {

        String[] groups = {"Services", "Fun Things To Do", "Dining", "Shopping"};

        ArrayAdapter<String> activitiesGroupAdapter = new ArrayAdapter<String>(this, R.layout.activities_group_list_item, groups);

        activitiesGroupListView.setAdapter(activitiesGroupAdapter);
    }

    public class ActivitiesGroupNavListClickHandler implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> list, View view, int position, long id)
        {
            String clickedItem = (String) list.getItemAtPosition(position);
            Intent goToIntent;

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

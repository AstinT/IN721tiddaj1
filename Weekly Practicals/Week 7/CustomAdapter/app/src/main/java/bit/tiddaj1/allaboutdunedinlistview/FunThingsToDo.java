package bit.tiddaj1.allaboutdunedinlistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class FunThingsToDo extends AppCompatActivity {

    ThingsToDo[] thingsToDoArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscreen_listview);

        //Declaring a TextView. Getting there ids
        TextView subScreenTitle = (TextView) findViewById(R.id.tvSubScreenTitle);
        //Setting text and image for the TextView and ImageView
        subScreenTitle.setText(R.string.subScreenTitleFunThingsToDo);

        initialiseDataArray();
        setUpServicesGroupList();
    }

    public void initialiseDataArray()
    {
        Resources resourceMachine = getResources();

        Drawable larnachCastle = resourceMachine.getDrawable(R.drawable.larnach_castle, null);
        Drawable moanaPool = resourceMachine.getDrawable(R.drawable.moana_pool, null);
        Drawable saltWaterPool = resourceMachine.getDrawable(R.drawable.salt_water_pool, null);
        Drawable speightsBrewery = resourceMachine.getDrawable(R.drawable.speights_brewery, null);
        Drawable olveston = resourceMachine.getDrawable(R.drawable.olveston, null);
        Drawable peninsula = resourceMachine.getDrawable(R.drawable.peninsula, null);
        Drawable taeriGorgeRailway = resourceMachine.getDrawable(R.drawable.taeri_gorge_railway, null);
        Drawable stKildaBeach = resourceMachine.getDrawable(R.drawable.st_kilda_beach, null);

        thingsToDoArray = new ThingsToDo[8];
        thingsToDoArray[0] = new ThingsToDo("Larnach Castle", larnachCastle);
        thingsToDoArray[1] = new ThingsToDo("Moana Pool", moanaPool);
        thingsToDoArray[2] = new ThingsToDo("Salt Water Pool", saltWaterPool);
        thingsToDoArray[3] = new ThingsToDo("Speights Brewery", speightsBrewery);
        thingsToDoArray[4] = new ThingsToDo("Olveston", olveston);
        thingsToDoArray[5] = new ThingsToDo("Peninsula", peninsula);
        thingsToDoArray[6] = new ThingsToDo("Taeri Gorge Railway", taeriGorgeRailway);
        thingsToDoArray[7] = new ThingsToDo("St Kilda Beach", stKildaBeach);
    }

    public void setUpServicesGroupList() {

        ListView servicesGroupListView = (ListView) findViewById(R.id.lvThingsToDo);


        //Creating adapter
        ArrayAdapter<String> activitiesGroupAdapter = new ArrayAdapter<String>(this,
                R.layout.activities_group_list_item, thingsToDoArray);

        //setting adapter to listview
        servicesGroupListView.setAdapter(activitiesGroupAdapter);
    }

    public class ThingsToDoAdapter extends ArrayAdapter<ThingsToDo>
    {
        public ThingsToDoAdapter(Context context, int resource, ThingsToDo[] objects)
        {
            super(context, resource, objects);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container)
        {
            LayoutInflater inflater = LayoutInflater.from(FunThingsToDo.this);

            View customView = inflater.inflate(R.layout.custom_listview_item, container, false);

            ImageView itemImageView = (ImageView) findViewById(R.id.ivItemImage);
            TextView itemTextView = (TextView) findViewById(R.id.tvItemName);

            ThingsToDo currentItem = getItem(position);

            itemImageView.setImageDrawable(currentItem.thingsToDoImage);
            itemTextView.setText(currentItem.thingsToDoName);

            return customView;
        }
    }
}

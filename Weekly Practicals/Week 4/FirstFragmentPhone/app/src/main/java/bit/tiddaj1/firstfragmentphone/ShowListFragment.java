package bit.tiddaj1.firstfragmentphone;


import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ShowListFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View fragmentView = inflater.inflate(R.layout.show_list_fragment, container, false);

        ListView lvCities = (ListView) fragmentView.findViewById(R.id.lvCities);

        Resources resourceMachine = getResources();
        String[] cityNamesArray = resourceMachine.getStringArray(R.array.city_names_array);

        ArrayAdapter<String> cityNamesAdapter = new ArrayAdapter<String>(   getActivity(),
                                                                            android.R.layout.simple_list_item_1,
                                                                            cityNamesArray);

        lvCities.setAdapter(cityNamesAdapter);

        return fragmentView;
    }

}

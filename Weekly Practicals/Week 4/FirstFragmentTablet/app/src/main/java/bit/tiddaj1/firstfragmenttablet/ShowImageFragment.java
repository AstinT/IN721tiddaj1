package bit.tiddaj1.firstfragmenttablet;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShowImageFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        //Creates view for the fragment
        View fragmentView = inflater.inflate(R.layout.show_image_fragment, container, false);

        //Returns the fragment
        return fragmentView;
    }
}
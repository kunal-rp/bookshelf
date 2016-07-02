package kunal.third.com.app2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * The Fragment used when the HOME button is pressed; this
 * is the launcher fragment
 *
 * NEEDS WORK
 */
public class home_fragment extends Fragment {


    public home_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.home_fragment, container,false);
    }



}

package kunal.third.com.app2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Class associated with the bottom buttons
 * in the launcher activity
 */
public class bottom_buttons_fragment extends Fragment {


    public bottom_buttons_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {;
       return inflater.inflate(R.layout.bottom_buttons_fragment, container, false);
    }

}

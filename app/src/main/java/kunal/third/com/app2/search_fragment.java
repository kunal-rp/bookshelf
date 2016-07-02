package kunal.third.com.app2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class search_fragment extends Fragment {


    ListView display_other_textbooks;
    View view;
    DB_Textbook_Helper db_textbook_helper;

    public search_fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.search_fragment, container, false);
        db_textbook_helper = new DB_Textbook_Helper(getContext(), null);


        display_other_textbooks = (ListView) view.findViewById(R.id.display_other_textbooks);
        ListAdapter adapter = new textbook_list_other_adapter(getContext(), db_textbook_helper.getAllTextbookPosts());
        display_other_textbooks.setAdapter(adapter);
        return view;
    }

}

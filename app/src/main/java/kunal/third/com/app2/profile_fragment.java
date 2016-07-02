package kunal.third.com.app2;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class profile_fragment extends Fragment {


    DB_Profile_Helper db_profileHelper;
    DB_Textbook_Helper db_textbook_helper;
    TextView display_full_name, display_username, display_current_college, display_email;
    ListView display_user_posts;
    View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        db_profileHelper = new DB_Profile_Helper(getActivity(), null);
        db_textbook_helper = new DB_Textbook_Helper(getActivity(), null);


        view = inflater.inflate(R.layout.profile_fragment, container, false);

        display_user_posts = (ListView) view.findViewById(R.id.display_user_posts);
        display_full_name = (TextView) view.findViewById(R.id.display_full_name);
        display_username = (TextView) view.findViewById(R.id.display_username);
        display_current_college = (TextView) view.findViewById(R.id.display_current_college);
        display_email = (TextView) view.findViewById(R.id.display_email);


        ListAdapter user_post_adapter = new textbook_list_self_adapter(getContext(), db_textbook_helper.getAllTextbookPosts());
        display_user_posts.setAdapter(user_post_adapter);
        setHasOptionsMenu(true);



        display_user_posts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                textbook_object temp = (textbook_object) parent.getAdapter().getItem(position);
                Intent intent = new Intent(getActivity(), display_textbook_details_activity.class);
                intent.putExtra("textbook_id",temp.getTextbook_post_id());
                startActivity(intent);

            }
        });


        return view;





    }

    public void updateTextViews() {

        if (db_profileHelper.getProfilesCount() == 1) {
            profile_object temp = db_profileHelper.getProfile();


            display_full_name.setText(temp.getFull_name().toString());
            display_username.setText("@" + temp.getUsername().toString());
            display_email.setText(temp.getEmail().toString());
            display_current_college.setText(temp.getCurrent_college().toString());
        }

    }


    @Override
    public void onResume() {

        super.onResume();
        updateTextViews();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.menu_display_profile, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(view.getContext(), input_edit_profile_activity.class);
        startActivity(intent);
        return true;
    }


}

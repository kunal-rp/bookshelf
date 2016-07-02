package kunal.third.com.app2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

/**
 * Activity that is called when user wants to update
 * user details.
 */

public class input_edit_profile_activity extends AppCompatActivity {

    final int SELECT_FILE = 1;
    private DB_Profile_Helper db_profileHelper;
    private EditText input_full_name,input_username, input_current_college, input_email;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_edit_profile_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("Edit Profile");
        setSupportActionBar(toolbar);

        db_profileHelper = new DB_Profile_Helper(getBaseContext(), null);

        input_full_name = (EditText) findViewById(R.id.input_full_name);
        input_username = (EditText) findViewById(R.id.input_username);
        input_current_college = (EditText) findViewById(R.id.input_current_college);
        input_email = (EditText) findViewById(R.id.input_email);
        updateEditTexts();

    }


    /**
     * Updates the editviews with ddetails user previously
     * imputted
     */
    public void updateEditTexts() {
        if (db_profileHelper.getProfilesCount() == 1) {//First checks to see if database has a row of user data
            profile_object temp = db_profileHelper.getProfile();

            input_full_name.setText(temp.getFull_name().toString());
            input_username.setText(temp.getUsername().toString());
            input_email.setText(temp.getEmail().toString());
            input_current_college.setText(temp.getCurrent_college().toString());
        }

    }







    /**
     * Method used to update Action Bar
     * @param menu
     * @return
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_profile, menu);
        return true;
    }

    /**
     * Since there is only one option, there can only be one response
     *
     * Method first gets all of the user imputed values, stores them
     * in a temporary profile object, updates the database, and finishes
     * the activity
     *
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {

        profile_object temp = new profile_object();
        temp.setId(90);
        temp.setFull_name(input_full_name.getText().toString());
        temp.setUsername(input_username.getText().toString());
        temp.setCurrent_college(input_current_college.getText().toString());
        temp.setEmail(input_email.getText().toString());


        db_profileHelper.updateProfile(temp);

        this.finish();
        return true;

    }

}

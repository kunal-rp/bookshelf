package kunal.third.com.app2;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class create_new_post_activity extends AppCompatActivity {

    final int SELECT_FILE = 2;
    private DB_Textbook_Helper db_textbook_helper;
    private DB_Profile_Helper db_profile_helper;
    private EditText input_title,input_author,input_isbn,input_subject,input_class_name,input_class_number;
    private ImageView input_textbook_image;
    private profile_object user ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_post_activity);

        db_profile_helper = new DB_Profile_Helper(getBaseContext(),null );
        user = db_profile_helper.getProfile();

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("New Textbook Post");
        setSupportActionBar(toolbar);
        db_textbook_helper = new DB_Textbook_Helper(getBaseContext(), null);

        input_title = (EditText)findViewById(R.id.input_title);
        input_author = (EditText)findViewById(R.id.input_author);
        input_subject = (EditText)findViewById(R.id.input_subject);
        input_class_name = (EditText)findViewById(R.id.input_class_name);
        input_class_number = (EditText)findViewById(R.id.input_class_number);

        input_textbook_image = (ImageView)findViewById(R.id.input_textbook_image);


    }

    /**
     * Method part of process to get image from gallery
     * @param view
     */
    public void importTextbookImageAction(final View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"), SELECT_FILE);
    }


    /**
     * Method part of process to get image from gallery
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
        }
    }

    /**
     * Method part of process to get image from gallery
     * @param data
     */
    private void onSelectFromGalleryResult(Intent data) {
        Bitmap bm = null;
        if (data != null) {
            try {
                bm = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        input_textbook_image.setImageBitmap(bm);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_new_textbook_post, menu);
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


        DateFormat df = new SimpleDateFormat("HH:mm:ss ' on ' dd'/'MM'/'yy");
        String date = df.format(Calendar.getInstance().getTime());

        textbook_object temp = new textbook_object();
        temp.setTextbook_post_id(db_textbook_helper.getPostCount() + 1);
        temp.setTextbook_title(input_title.getText().toString());
        temp.setTextbook_author(input_author.getText().toString());
        temp.setTextbook_subject(input_subject.getText().toString());
        temp.setTextbook_class_name(input_class_name.getText().toString());
        temp.setTextbook_class_number(Integer.parseInt(input_class_number.getText().toString()));
        temp.setTextbook_user_username(user.getUsername().toString());
        temp.setTextbook_college(user.getCurrent_college().toString());
        temp.setTextbook_posting_date_time(date);
        Bitmap image = ((BitmapDrawable) input_textbook_image.getDrawable()).getBitmap();
        temp.setTextbook_image(image);

        db_textbook_helper.addTextbookPost(temp);

        this.finish();
        return true;

    }


}

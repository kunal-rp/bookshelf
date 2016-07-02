package kunal.third.com.app2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class display_textbook_details_activity extends AppCompatActivity {

    int textbookObject_id;
    textbook_object textbookObject;
    TextView display_title,display_author,display_subject,display_college,display_time_date,display_class_name_number,display_user_username;
    DB_Textbook_Helper db_textbook_helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_textbook_details_activity);

        db_textbook_helper = new DB_Textbook_Helper(this, null);


        Intent intent = getIntent();
        textbookObject_id = intent.getIntExtra("textbook_id", 0);
        textbookObject = db_textbook_helper.getTextbook(textbookObject_id);

        display_title = (TextView) findViewById(R.id.display_title);
        display_author = (TextView) findViewById(R.id.display_author);
        display_subject = (TextView) findViewById(R.id.display_subject);
        display_college = (TextView) findViewById(R.id.display_user_college);
        display_time_date = (TextView) findViewById(R.id.display_time_date);
        display_class_name_number = (TextView) findViewById(R.id.display_class_name_number);
        display_user_username= (TextView) findViewById(R.id.display_user_username);

        display_title.setText(textbookObject.getTextbook_title());
        display_author.setText(textbookObject.getTextbook_author());
        display_college.setText(textbookObject.getTextbook_college());
        display_subject.setText(textbookObject.getTextbook_subject());
        display_time_date.setText(String.valueOf(textbookObject.getTextbook_posting_date_time()));
        display_class_name_number.setText(textbookObject.getTextbook_class_name()+ " "+ textbookObject.getTextbook_class_number());
        display_user_username.setText("@"+textbookObject.getTextbook_user_username());
    }

}

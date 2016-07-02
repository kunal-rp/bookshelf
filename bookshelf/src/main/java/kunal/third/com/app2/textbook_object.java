package kunal.third.com.app2;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Class used to define the TEXTBOOK object as
 * a whole across all viewing platforms and
 * perspectives
 */
public class textbook_object {

    private String textbook_title;
    private int textbook_post_id;
    private int textbook_user_id;
    private String textbook_author;
    private String textbook_subject;
    private String textbook_college;
    private String textbook_class_name;
    private int textbook_class_number;
    private String textbook_posting_date_time;
    private String textbook_user_username;
    private interested_user_object[] textbook_interested_users;
    private Bitmap textbook_image;

    public int getTextbook_user_id() {
        return textbook_user_id;
    }

    public void setTextbook_user_id(int textbook_user_id) {
        this.textbook_user_id = textbook_user_id;
    }

    public interested_user_object[] getTextbook_interested_users() {
        return textbook_interested_users;
    }

    public void setTextbook_interested_users(interested_user_object[] textbook_interested_users) {
        this.textbook_interested_users = textbook_interested_users;
    }

    public String getTextbook_college() {
        return textbook_college;
    }

    public void setTextbook_college(String textbook_college) {
        this.textbook_college = textbook_college;
    }


    public void setTextbook_intrested(ArrayList<interested_user_object> textbook_intrested) {
        this.textbook_intrested = textbook_intrested;
    }

    public ArrayList<interested_user_object> getTextbook_intrested() {

        return textbook_intrested;
    }

    private ArrayList<interested_user_object> textbook_intrested = new ArrayList<>();

    public int getTextbook_post_id() {
        return textbook_post_id;
    }

    public void setTextbook_post_id(int textbook_post_id) {
        this.textbook_post_id = textbook_post_id;
    }


    public Bitmap getTextbook_image() {
        return textbook_image;
    }

    public void setTextbook_image(Bitmap textbook_image) {
        this.textbook_image = textbook_image;
    }


    public String getTextbook_user_username() {
        return textbook_user_username;
    }

    public void setTextbook_user_username(String textbook_post_username) {
        this.textbook_user_username = textbook_post_username;
    }

    public String getTextbook_subject() {
        return textbook_subject;
    }


    public String getTextbook_title() {
        return textbook_title;
    }

    public void setTextbook_title(String textbook_title) {
        this.textbook_title = textbook_title;
    }

    public String getTextbook_author() {
        return textbook_author;
    }

    public void setTextbook_author(String textbook_author) {
        this.textbook_author = textbook_author;
    }


    public String getTextbook_class_name() {
        return textbook_class_name;
    }

    public void setTextbook_class_name(String textbook_class_name) {
        this.textbook_class_name = textbook_class_name;
    }

    public int getTextbook_class_number() {
        return textbook_class_number;
    }

    public void setTextbook_class_number(int textbook_class_number) {
        this.textbook_class_number = textbook_class_number;
    }


    public void setTextbook_subject(String textbook_subject) {
        this.textbook_subject = textbook_subject;
    }

    public String getTextbook_posting_date_time() {
        return textbook_posting_date_time;
    }

    public void setTextbook_posting_date_time(String textbook_posting_date_time) {
        this.textbook_posting_date_time = textbook_posting_date_time;
    }

    public void addInterest(interested_user_object interested_user) {
        textbook_intrested.add(interested_user);
    }

    public void removeInterest(String username) {
        textbook_intrested.remove(username);
    }
}


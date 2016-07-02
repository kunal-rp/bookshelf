package kunal.third.com.app2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Kunal on 6/22/2016.
 */
public class DB_Textbook_Helper extends SQLiteOpenHelper {
    /**
     * All constants used in storing the user's textbook posts' details
     */

    private static final int DATABASE_VERSION = 12;
    private static final String BOOKSHELF_TEXTBOOK_TABLE_NAME = "BookShelfTextbookDatabase";
    private static final String COLUMN_ID = "textbook_post_id";
    private static final String COLUMN_TITLE = "textbook_title";
    private static final String COLUMN_USERNAME = "textbook_username";
    private static final String COLUMN_AUTHOR = "textbook_author";
    private static final String COLUMN_SUBJECT = "textbook_subject";
    private static final String COLUMN_CLASS_NAME = "textbook_class_name";
    private static final String COLUMN_CLASS_NUMBER = "textbook_class_number";
    private static final String COLUMN_COLLEGE = "textbook_college";
    private static final String COLUMN_POST_TIME = "textbook_post_time";
    private static final String COLUMN_INTERESTED_USERS = "textbook_interested_users";


    private static final String TEXTBOOK_TABLE_CREATE =
            "CREATE TABLE " + BOOKSHELF_TEXTBOOK_TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_TITLE + " TEXT," +
                    COLUMN_USERNAME + " TEXT," +
                    COLUMN_AUTHOR + " TEXT," +
                    COLUMN_SUBJECT + " TEXT," +
                    COLUMN_CLASS_NAME + " TEXT," +
                    COLUMN_COLLEGE + " TEXT," +
                    COLUMN_CLASS_NUMBER + " INTEGER," +
                    COLUMN_POST_TIME + " TEXT," +
                    COLUMN_INTERESTED_USERS + " TEXT" +
                    " );";

    public DB_Textbook_Helper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, BOOKSHELF_TEXTBOOK_TABLE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TEXTBOOK_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOOKSHELF_TEXTBOOK_TABLE_NAME);
        onCreate(db);
    }

    public void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(BOOKSHELF_TEXTBOOK_TABLE_NAME, null, null);
        db.close();
    }

    /**
     * Method called to update user data; first the table is cleared, then
     * the updated details are passed into a ContentValue variable, and
     * finally the values are inserted into the database
     *
     * @param textbook the updated version of the user's profile information
     */
    public void addTextbookPost(textbook_object textbook) {

        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_ID, textbook.getTextbook_post_id());
        values.put(COLUMN_TITLE, textbook.getTextbook_title());
        values.put(COLUMN_USERNAME, textbook.getTextbook_user_username());
        values.put(COLUMN_AUTHOR, textbook.getTextbook_author());
        values.put(COLUMN_SUBJECT, textbook.getTextbook_subject());
        values.put(COLUMN_CLASS_NAME, textbook.getTextbook_class_name());
        values.put(COLUMN_CLASS_NUMBER, textbook.getTextbook_class_number());
        values.put(COLUMN_COLLEGE, textbook.getTextbook_college());
        values.put(COLUMN_POST_TIME, textbook.getTextbook_posting_date_time());
        //setImage(textbook.getTextbook_image(), textbook);
        values.put(COLUMN_INTERESTED_USERS, interestedToString(textbook.getTextbook_intrested()));
        db.insert(BOOKSHELF_TEXTBOOK_TABLE_NAME, null, values);
        db.close();

    }

    /**
     * Method called to clear the table; only used privately
     */


    /**
     * Method used to get current profile details; first a cursor is
     * set to the top of the table, then the first(and only) row's
     * contents are read and put into a profile object
     *
     * @return profile object with current profile details
     */
    public ArrayList<textbook_object> getAllTextbookPosts() {

        ArrayList<textbook_object> allTextbooks = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + BOOKSHELF_TEXTBOOK_TABLE_NAME;
        Cursor c = db.rawQuery(selectQuery, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_TITLE)) != null) {
                textbook_object temp = new textbook_object();

                temp.setTextbook_post_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
                temp.setTextbook_title(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                temp.setTextbook_user_username(c.getString(c.getColumnIndex(COLUMN_USERNAME)));
                temp.setTextbook_author(c.getString(c.getColumnIndex(COLUMN_AUTHOR)));
                temp.setTextbook_subject(c.getString(c.getColumnIndex(COLUMN_SUBJECT)));
                temp.setTextbook_class_name(c.getString(c.getColumnIndex(COLUMN_CLASS_NAME)));
                temp.setTextbook_class_number(c.getInt(c.getColumnIndex(COLUMN_CLASS_NUMBER)));
                temp.setTextbook_college(c.getString(c.getColumnIndex(COLUMN_COLLEGE)));
                temp.setTextbook_posting_date_time(c.getString(c.getColumnIndex(COLUMN_POST_TIME)));
                //temp.setTextbook_image(getImage(temp));
                temp.setTextbook_intrested(stringToInterested(c.getString(c.getColumnIndex(COLUMN_INTERESTED_USERS))));
                allTextbooks.add(temp);
                c.moveToNext();


            }
        }


        db.close();
        return allTextbooks;
    }

    /**
     * Method used to retreive image stored in the SD card of the phone
     *
     * @return bitmap image for the profile image
     */

    public Bitmap getImage(textbook_object textbook) {

        String completePath = Environment.getExternalStorageDirectory() + "/" + textbook.getTextbook_post_id() + "_BS_Textbook_Image_.png";

        Bitmap image = BitmapFactory.decodeFile(completePath);
        return image;

    }

    /**
     * Stores the profile image in set location
     *
     * @param image Bitmap image that wil be used as the profile image
     */
    public void setImage(Bitmap image, textbook_object textbook) {

        String completePath = Environment.getExternalStorageDirectory() + "/" + textbook.getTextbook_post_id() + "_BS_Textbook_Image_.png";
        try {

            FileOutputStream out = new FileOutputStream(completePath);


            image.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String interestedToString(ArrayList<interested_user_object> interestedUsers) {
        String text = "";
        for (int i = 0; i < interestedUsers.size(); i++) {
            text += (interestedUsers.get(i).getUsername() + "/" + interestedUsers.get(i).getCollege() + "|");
        }
        return text;
    }

    public ArrayList<interested_user_object> stringToInterested(String text) {
        ArrayList<interested_user_object> interestedUsers = new ArrayList<>();
        while (text.length() > 5) {
            String[] partsOne = text.split("|");
            for (int i = 0; i < partsOne.length; i++) {
                String[] partsTwo = partsOne[i].split("/");
                interested_user_object temp = new interested_user_object();
                temp.setUsername(partsTwo[0]);
                temp.setCollege(partsTwo[1]);
                interestedUsers.add(temp);
            }
        }

        return interestedUsers;
    }

    public textbook_object getTextbook(int id){


        textbook_object temp = new textbook_object();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + BOOKSHELF_TEXTBOOK_TABLE_NAME + " WHERE "+COLUMN_ID+ " = "+ id;
        Cursor c = db.rawQuery(selectQuery, null);

        c.moveToFirst();

        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex(COLUMN_TITLE)) != null) {

                temp.setTextbook_post_id(c.getInt(c.getColumnIndex(COLUMN_ID)));
                temp.setTextbook_title(c.getString(c.getColumnIndex(COLUMN_TITLE)));
                temp.setTextbook_user_username(c.getString(c.getColumnIndex(COLUMN_USERNAME)));
                temp.setTextbook_author(c.getString(c.getColumnIndex(COLUMN_AUTHOR)));
                temp.setTextbook_subject(c.getString(c.getColumnIndex(COLUMN_SUBJECT)));
                temp.setTextbook_college(c.getString(c.getColumnIndex(COLUMN_COLLEGE)));
                temp.setTextbook_class_name(c.getString(c.getColumnIndex(COLUMN_CLASS_NAME)));
                temp.setTextbook_class_number(c.getInt(c.getColumnIndex(COLUMN_CLASS_NUMBER)));
                temp.setTextbook_posting_date_time(c.getString(c.getColumnIndex(COLUMN_POST_TIME)));
                //temp.setTextbook_image(getImage(temp));
                temp.setTextbook_intrested(stringToInterested(c.getString(c.getColumnIndex(COLUMN_INTERESTED_USERS))));
                c.moveToNext();


            }
        }


        db.close();
        return temp;

    }

    public int getPostCount() {
        SQLiteDatabase db = getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, BOOKSHELF_TEXTBOOK_TABLE_NAME);
        db.close();
        return (int) cnt;
    }



}

package kunal.third.com.app2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Class used to create and update the database used to store the
 * user's PROFILE information
 */
public class DB_Profile_Helper extends SQLiteOpenHelper {


    /**
     * All constants used in storing the profile details
     */
    private static String fileName = "BS_Profile_Image.png";//The file name where the profile image is stored
    private static final int DATABASE_VERSION = 15;
    private static final String BOOKSHELF_PROFILE_TABLE_NAME = "BookShelfProfileDatabase";
    private static final String COLUMN_ID = "profile_id";
    private static final String COLUMN_FULL = "profile_full";
    private static final String COLUMN_EMAIL = "profile_email";
    private static final String COLUMN_USERNAME = "profile_username";
    private static final String COLUMN_CURRENTCOLLEGE = "profile_currentcollege";


    private static final String PROFILE_TABLE_CREATE =
            "CREATE TABLE " + BOOKSHELF_PROFILE_TABLE_NAME + "(" +
                    COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_FULL + " TEXT," +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_USERNAME + " TEXT," +
                    COLUMN_CURRENTCOLLEGE + " TEXT" +
                    " );";

    public DB_Profile_Helper(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, BOOKSHELF_PROFILE_TABLE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(PROFILE_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BOOKSHELF_PROFILE_TABLE_NAME);
        onCreate(db);
    }

    /**
     * Method called to update user data; first the table is cleared, then
     * the updated details are passed into a ContentValue variable, and
     * finally the values are inserted into the database
     *
     * @param profile the updated version of the user's profile information
     */
    public void updateProfile(profile_object profile) {

        clearTable();
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, profile.getProfile_id());
        values.put(COLUMN_FULL, profile.getFull_name());
        values.put(COLUMN_USERNAME, profile.getUsername());
        values.put(COLUMN_EMAIL, profile.getEmail());
        values.put(COLUMN_CURRENTCOLLEGE, profile.getCurrent_college());
        db.insert(BOOKSHELF_PROFILE_TABLE_NAME, null, values);
        db.close();

    }

    /**
     * Method called to clear the table; only used privately
     */
    private void clearTable() {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(BOOKSHELF_PROFILE_TABLE_NAME, null, null);
        db.close();
    }


    /**
     * @return number of profile rows in table
     */
    public int getProfilesCount() {
        SQLiteDatabase db = getReadableDatabase();
        long cnt = DatabaseUtils.queryNumEntries(db, BOOKSHELF_PROFILE_TABLE_NAME);
        db.close();
        return (int) cnt;
    }


    /**
     * Method used to get current profile details; first a cursor is
     * set to the top of the table, then the first(and only) row's
     * contents are read and put into a profile object
     * @return profile object with current profile details
     */
    public profile_object getProfile() {

        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + BOOKSHELF_PROFILE_TABLE_NAME;
        Cursor c = db.rawQuery(selectQuery, null);

        c.moveToFirst();
        profile_object profile = new profile_object();




            profile.setId(c.getInt(c.getColumnIndex(COLUMN_ID)));
            profile.setFull_name(c.getString(c.getColumnIndex(COLUMN_FULL)));
            profile.setUsername(c.getString(c.getColumnIndex(COLUMN_USERNAME)));

            profile.setCurrent_college(c.getString(c.getColumnIndex(COLUMN_CURRENTCOLLEGE)));
            profile.setEmail(c.getString(c.getColumnIndex(COLUMN_EMAIL)));



        db.close();
        return profile;
    }

    /**
     * Method used to retreive image stored in the SD card of the phone
     * @return bitmap image for the profile image
     */



}

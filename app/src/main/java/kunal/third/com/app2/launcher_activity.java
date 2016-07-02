package kunal.third.com.app2;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;


public class launcher_activity extends AppCompatActivity {

    android.support.v7.widget.Toolbar toolbar;
    profile_fragment profileFragment;
    home_fragment openingFragment;
    search_fragment searchFragment;
    bottom_buttons_fragment bottomButtons = new bottom_buttons_fragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        profileFragment = new profile_fragment();
        openingFragment = new home_fragment();

        searchFragment = new search_fragment();

        getWindow().setFormat(PixelFormat.RGBA_8888);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
        if (savedInstanceState != null) {
            return;
        }

        getSupportFragmentManager().beginTransaction().add(R.id.topFragment, openingFragment).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.bottomFragment, bottomButtons).commit();

        toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.app_bar);
        toolbar.setTitle("BookSHELF");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_top, menu);
        return true;
    }

    public void profileAction(View view) {


        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.topFragment, profileFragment);
        transaction.commit();


    }

    public void searchAction(View view){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.topFragment,searchFragment );
        transaction.commit();
    }

    public void homeAction(View view) {

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.topFragment, openingFragment);
        transaction.commit();
    }
    public void createNewPostAction(View view){
        Intent intent = new Intent(this, create_new_post_activity.class);
        startActivity(intent);
    }




}

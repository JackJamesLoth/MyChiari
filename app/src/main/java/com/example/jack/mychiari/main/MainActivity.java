package com.example.jack.mychiari.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jack.mychiari.R;
import com.example.jack.mychiari.classes.viewpagers.NoSwipeViewPager;
import com.example.jack.mychiari.mainFragments.MainFragment;
import com.example.jack.mychiari.mainFragments.SettingsFragment;

public class MainActivity extends AppCompatActivity implements MainFragment.Main {

    //ViewPager
    private NoSwipeViewPager pager;
    private PagerAdapter adapter;
    private static final int NUM_PAGES = 2;

    //User info
    String user;
    int numReports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate pager and adapter
        pager = (NoSwipeViewPager) findViewById(R.id.pager);
        adapter = new MainActivity.ScreenSlideAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);

        //Get user's name
        //TODO get actual user's name
        user = "Jack"; //TEMPORARY VALUE

        //Get number of reports
        //TODO get actual number of reports
        numReports = 5; //TEMPORARY VALUE
    }

    //Adapter class to return the current fragments
    private class ScreenSlideAdapter extends FragmentStatePagerAdapter {

        public ScreenSlideAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new MainFragment();
                case 1:
                    return new SettingsFragment();
                default:
                    return null;
                //TODO make it return an error message or something
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }

    //Back button logic
    @Override
    public void onBackPressed(){
        //Go to main fragment if user is in settings fragment
        if (pager.getCurrentItem() == 1){
            pager.setCurrentItem(0);
        } else {
            finish();
        }
    }

    /*
     *  Fragment callback methods
     */

    @Override
    public void settings() {
        pager.setCurrentItem(1);
    }

    @Override
    public void report() {

    }

    @Override
    public String getUser() {
        return user;
    }

    @Override
    public int getNumReports() {
        return numReports;
    }
}

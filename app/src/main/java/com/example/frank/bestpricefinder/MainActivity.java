package com.example.frank.bestpricefinder;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import java.io.Serializable;

public class MainActivity extends AppCompatActivity {


    Context m_context = this;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {


            Fragment fragment = null;


            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new HomeFragment();
                    break;

                case R.id.navigation_lists:
                    fragment = new ListsFragment();
                    break;
                case R.id.navigation_scan:
                    fragment = new ScanFragment();
                    Bundle bundl;
                    bundl = new Bundle();
                    ScanResultsSerialized bundleObj;
                    bundleObj = new ScanResultsSerialized(m_context,"");

                    bundl.putSerializable("theMainContext", bundleObj);
                    fragment.setArguments(bundl);
                    break;
                case R.id.navigation_specials:
                    fragment = new SpecialsFragment();
                    break;

                case R.id.navigation_settings:
                    fragment = new SettingsFragment();
                    break;
            }

            return loadFragment(fragment);
            // return false;
        }
    };


    /*public static void openFragmentFromClass(Fragment fragment) {

        loadFragment(fragment);
    }*/

    /*_____________________________________loadFragment_________________________________________*/
   /* Method to load fragments in the MainActivity fragments container.*/
    public boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    /*__________________________________End of loadFragment___________________________________*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        loadFragment(new HomeFragment());

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

    }

}

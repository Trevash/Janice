package com.janus.UI;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.janus.R;

public class MainActivity extends AppCompatActivity {
    private FragmentManager fm = getSupportFragmentManager();
    private LoginFragment lFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Iconify.with(new FontAwesomeModule());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null) {
            lFragment = new LoginFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, lFragment)
                    .commit();
        }
    }

    /*protected void onLogIn(){
        mFragment = new MapFragment();
        fm.beginTransaction()
                .replace(R.id.fragment_container, mFragment)
                .commit();
    }*/

    /*protected void onSyncComplete(){
        mFragment.onSyncComplete();
    }*/
}

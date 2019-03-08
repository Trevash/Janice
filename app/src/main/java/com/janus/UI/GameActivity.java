package com.janus.UI;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.janus.R;

public class GameActivity extends AppCompatActivity
        implements MapFragment.Context, RouteFragment.Context, DeckFragment.Context,
        DestinationSelectFragment.Context, StatusFragment.Context{
    private FragmentManager fm = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if(fragment == null) {
            MapFragment gameFragment = new MapFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, gameFragment)
                    .commit();
        }

        Toast.makeText(this, "You started the game!", Toast.LENGTH_LONG).show();
    }

    public void onClickDrawCard() {
        DeckFragment deckFragment = new DeckFragment();
        fm.beginTransaction()
                .replace(R.id.fragment_container, deckFragment)
                .commit();
    }

    public void onFinishAction() {
        MapFragment gameFragment = new MapFragment();
        fm.beginTransaction()
                .replace(R.id.fragment_container, gameFragment)
                .commit();
    }

    public void onClickClaimRoute() {
        RouteFragment routeFragment = new RouteFragment();
        fm.beginTransaction()
                .replace(R.id.fragment_container, routeFragment)
                .commit();
    }

    public void onClickDestinationSelect() {
        DestinationSelectFragment destinationFragment = new DestinationSelectFragment();
        fm.beginTransaction()
                .replace(R.id.fragment_container, destinationFragment)
                .commit();
    }

    public void onClickGameStatus() {
        StatusFragment statusFragment = new StatusFragment();
        fm.beginTransaction()
                .replace(R.id.fragment_container, statusFragment)
                .commit();
    }
}

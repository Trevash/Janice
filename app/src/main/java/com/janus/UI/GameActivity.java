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

        Fragment fragment = fm.findFragmentById(R.id.game_layout);

        if(fragment == null) {
            MapFragment mapFragment = new MapFragment();
            fm.beginTransaction()
                    .add(R.id.game_layout, mapFragment)
                    .commit();
            /*DestinationSelectFragment destinationFragment = new DestinationSelectFragment();
            fm.beginTransaction()
                    .add(R.id.game_layout, mapFragment)
                    .commit();*/
        }

        Toast.makeText(this, "You started the game!", Toast.LENGTH_LONG).show();
    }

    public void onClickDrawCard() {
        DeckFragment deckFragment = new DeckFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, deckFragment)
                .commit();
    }

    public void onFinishAction() {
        MapFragment gameFragment = new MapFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, gameFragment)
                .commit();
    }

    public void onClickClaimRoute() {
        RouteFragment routeFragment = new RouteFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, routeFragment)
                .commit();
    }

    public void onClickDestinationSelect() {
        DestinationSelectFragment destinationFragment = new DestinationSelectFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, destinationFragment)
                .commit();
    }

    public void onClickGameStatus() {
        StatusFragment statusFragment = new StatusFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, statusFragment)
                .commit();
    }

    public void onMapFragmentSelected() {
        MapFragment mapFragment = new MapFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, mapFragment)
                .commit();
    }

    public void onClickRunDemo(){
        try {
            MapFragment mapFragment = new MapFragment();
            ChatFragment chatFragment = new ChatFragment();
            StatusFragment statusFragment = new StatusFragment();
            fm.beginTransaction()
                    .replace(R.id.game_layout, mapFragment)
                    .commit();
            //Demonstrate the following with pauses so the human eyes can read toasts and follow along
            flashStatusFragment(statusFragment, fm);
            makeToast("Here's the Starting Status");

            //● Add train cards for this player
            makeToast("Drawing train cards");
            waitForSomeSeconds();
            //● Update the number of invisible (face down) cards in train card deck and the visible (face up) cards in the train card deck

            makeToast("Updated number of train cards in deck and face up cards");
            waitForSomeSeconds();
            //● Update the number of train cards for opponent players
            flashStatusFragment(statusFragment, fm);
            makeToast("Updated number of train cards for opponents");

            //● Add claimed route (for any player). Show this on the map.
            //● Update player points
            //● Update the number of train cars and cards for opponent players
            flashStatusFragment(statusFragment, fm);
            makeToast("Updated number of train cards and cars for opponents");

            //● Add player destination cards for this player
            //● Update the number of cards in destination card deck
            //● Update the number of destination cards for opponent players
            flashStatusFragment(statusFragment, fm);
            makeToast("Updated number of dest. cards for opponents");
            //● Remove player destination cards for this player
            //● Update the number of destination cards for opponent players
            flashStatusFragment(statusFragment, fm);
            makeToast("Updated number of dest. cards for opponents");

            //● Add chat message from any player
            fm.beginTransaction()
                    .replace(R.id.game_layout, chatFragment)
                    .commit();
            chatFragment.sendDemoChatMessage("We are sending a chat message!");
            makeToast("Sending chat message!");
            waitForSomeSeconds();

            //● Advance player turn (change the turn indicator so it indicates another player)

            makeToast("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void flashStatusFragment(StatusFragment statusFragment, FragmentManager fm) throws InterruptedException {
        fm.beginTransaction()
                .replace(R.id.game_layout, statusFragment)
                .commit();
        waitForSomeSeconds();
    }

    private void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG);
    }

    private void waitForSomeSeconds() throws InterruptedException {
        wait(5000);
    }
}

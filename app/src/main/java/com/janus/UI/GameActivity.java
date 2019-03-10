package com.janus.UI;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.janus.ClientFacade;
import com.janus.Communication.WaitTask;
import com.janus.R;

public class GameActivity extends AppCompatActivity
        implements MapFragment.Context, RouteFragment.Context, DeckFragment.Context,
        DestinationSelectFragment.Context, StatusFragment.Context, WaitTask.Caller {
    private FragmentManager fm = getSupportFragmentManager();
    private int demoState = 0;
    private WaitTask task;


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

        makeToast("You started the game!");
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

    public void onFinishWait(Integer demoState) {
        this.demoState = demoState;
        switch (demoState) {
            case 1:
                showDeckFragment();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 2:
                flashStatusFragment();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 3:
                showRouteFragment();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 4:
                flashStatusFragment();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 5:
                showMapFragment();
                task = new WaitTask(this);
                task.execute(demoState);
        }
    }

    public void showDeckFragment() {
        DeckFragment fragment = new DeckFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, fragment)
                .commit();
    }

    public void showRouteFragment() {
        RouteFragment fragment = new RouteFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, fragment)
                .commit();
    }

    public void showMapFragment() {
        MapFragment fragment = new MapFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, fragment)
                .commit();
    }

    public void onClickRunDemo() {
        //makeToast("demo toast works!!");
        ChatFragment chatFragment = new ChatFragment();
        StatusFragment statusFragment = new StatusFragment();
        gameModel curGame = ClientFacade.getInstance().getGame();
        playerModel curPlayer = curGame.getHostPlayer();
        //waitForSomeSeconds();
        //Demonstrate the following with pauses so the human eyes can read toasts and follow along
        ////Toast.makeText(this,"work!!!!!!",Toast.LENGTH_LONG).show();
        makeToast("Here's the Starting Status");

        //waitForSomeSeconds();
        flashStatusFragment();
        task = new WaitTask(this);
        task.execute(0);
        //● Add train cards for this player
        /*
        fm.beginTransaction()
                .replace(R.id.game_layout, deckFragment)
                .commit();

        ////makeToast("Drawing train cards");
        curPlayer.addTrainCardToHand(curGame.drawTrainCardFromDeck());
        try {
            curPlayer.addTrainCardToHand(curGame.drawFaceUpTrainCard(0));
            curPlayer.addTrainCardToHand(curGame.drawFaceUpTrainCard(1));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        //● Update the number of invisible (face down) cards in train card deck and the visible (face up) cards in the train card deck
        ////makeToast("Updated number of train cards in deck and face up cards");
        //waitForSomeSeconds();
        //● Update the number of train cards for opponent players
        ////makeToast("Updated number of train cards for opponents");
        flashStatusFragment(statusFragment);

        //● Add claimed route (for any player). Show this on the map.
        fm.beginTransaction()
                .replace(R.id.game_layout, routeFragment)
                .commit();
        ////makeToast("Claiming a route");
        //waitForSomeSeconds();
        //● Show this on the map.
        fm.beginTransaction()
                .replace(R.id.game_layout, mapFragment)
                .commit();
        ////makeToast("Showing claimed route on map");
        //waitForSomeSeconds();
        //● Update player points, the number of train cars, and cards for opponent players
        ////makeToast("Updated score, number of train cards, cars for opponents");
        flashStatusFragment(statusFragment);

        //● Add player destination cards for this player
        //● Update the number of cards in destination card deck
        fm.beginTransaction()
                .replace(R.id.game_layout, deckFragment)
                .commit();
        ////makeToast("Drawing train cards");
        //waitForSomeSeconds();
        //● Update the number of destination cards for opponent players
        ////makeToast("Updated number of dest. cards for opponents");
        flashStatusFragment(statusFragment);
        //● Remove player destination cards for this player
        //● Update the number of destination cards for opponent players
        ////makeToast("Updated number of dest. cards for opponents");
        flashStatusFragment(statusFragment);

        //● Add chat message from any player
        fm.beginTransaction()
                .replace(R.id.game_layout, chatFragment)
                .commit();
        ////makeToast("Sending chat message!");
        //waitForSomeSeconds();

        //● Advance player turn (change the turn indicator so it indicates another player)
        */
        ////makeToast("Done!");
    }

    private void flashStatusFragment() {
        StatusFragment fragment = new StatusFragment();
        fm.beginTransaction()
                .replace(R.id.game_layout, fragment)
                .commit();
    }

    private void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }

    private void waitForSomeSeconds() {
        try {
            Thread.sleep(1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

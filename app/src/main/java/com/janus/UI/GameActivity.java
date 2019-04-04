package com.janus.UI;

import android.content.pm.ActivityInfo;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.models.chatMessageModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.janus.ClientFacade;
import com.janus.Communication.ServerProxy;
import com.janus.Communication.WaitTask;
import com.janus.R;

import java.util.List;

public class GameActivity extends AppCompatActivity
        implements MapFragment.Context, RouteFragment.Context, DeckFragment.Context,
        DestinationSelectFragment.Context, StatusFragment.Context, WaitTask.Caller {
    private FragmentManager fm = getSupportFragmentManager();
    private int demoState = 0;
    private WaitTask task;

    // TODO figure out bug: sometimes a back button press returns player to MainActivity


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Fragment fragment = fm.findFragmentById(R.id.game_layout);

        if(fragment == null) {
            /*MapFragment mapFragment = new MapFragment(); //For testing
            fm.beginTransaction()
                    .add(R.id.game_layout, mapFragment)
                    .commit();*/
            DestinationSelectFragment destinationFragment = new DestinationSelectFragment();
            fm.beginTransaction()
                    .add(R.id.game_layout, destinationFragment)
                    .commit();
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


    // This stuff is all related to the demo. I couldn't bring myself to delete it, but you probably shouldn't use it.
    public void onFinishWait(Integer demoState) {
        this.demoState = demoState;
        gameModel curGame = ClientFacade.getInstance().getGame();
        playerModel curPlayer = curGame.getHostPlayer();
        ClientFacade client = ClientFacade.getInstance();
        switch (demoState) {
            case 1:
                //● Add train cards for this player
                //● Update the number of invisible (face down) cards in train card deck and the visible (face up) cards in the train card deck
                makeToast("Drawing train cards, updating deck size and face-up trains");
                showDeckFragment();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 2:
                curPlayer.addTrainCardToHand(curGame.drawTrainCardFromDeck());
                try {
                    curPlayer.addTrainCardToHand(curGame.drawFaceUpTrainCard(0));
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                client.update();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 3:
                //● Update the number of train cards for opponent players
                makeToast("Updated number of train cards for opponents");
                showStatusFragment(0);
                client.update();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 4:
                //● Add claimed route (for any player). Show this on the map.
                makeToast("Claiming a route");
                showRouteFragment();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 5:
                List<abstractRoute> curRoutes = curGame.getRoutes();
                client.update();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 6:
                //● Show this on the map.
                makeToast("Showing claimed route on map");
                showMapFragment();
                client.update();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 7:
                //● Update player points, the number of train cars, and cards for opponent players
                makeToast("Updated score, number of train cards and train cars for opponents");
                showStatusFragment(0);
                client.update();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 8:
                //● Add player destination cards for this player
                makeToast("Claiming Destination Cards");
                showStatusFragment(1);
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 9:
                makeToast("Updated number of destination cards for opponents");
                DestinationCardModel card1 = Constants.DestinationCards.ATLANTA_MONTREAL;
                DestinationCardModel card2 = Constants.DestinationCards.ATLANTA_NEW_YORK;
                curPlayer.DEMO_addDestinationCardToHand(card1);
                curPlayer.DEMO_addDestinationCardToHand(card2);
                client.update();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 10:
                makeToast("Removing Destination cards from player");
                showStatusFragment(1);
                curPlayer.DEMO_removeDestinationCardToHand(0);
                curPlayer.DEMO_removeDestinationCardToHand(0);
                client.update();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 11:
                makeToast("Sending Chat message");
                showStatusFragment(0);
                client.update();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 12:
                chatMessageModel message = new chatMessageModel(curPlayer.getUserName(), "Hey! I'm a ghost that hacked your chat function!");
                try {
                    ServerProxy.getInstance().updateChatbox(new UpdateChatboxRequest(curGame.getGameID(), client.getUser().getAuthToken(), message));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 13:
                makeToast("Incrementing turn order to iWillLose");
                showMapFragment();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            case 14:
                curGame.incrementTurnCounter();
                client.update();
                task = new WaitTask(this);
                task.execute(demoState);
                break;
            default:
                makeToast("Demo done!");
                break;
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

    private void showStatusFragment(int i) {
        StatusFragment fragment = new StatusFragment();
        fragment.setWhichFragmentToShow(i);
        fm.beginTransaction()
                .replace(R.id.game_layout, fragment)
                .commit();
    }

    public void onClickRunDemo() {
        //Demonstrate the following with pauses so the human eyes can read toasts and follow along
        gameModel curGame = ClientFacade.getInstance().getGame();
        //playerModel fakePlayer = new playerModel(new usernameModel("iWillLose"), true,true,playerColorEnum.YELLOW);
        //fakePlayer.DEMO_addDestinationCardToHand(Constants.DestinationCards.CHICAGO_LOS_ANGELES);
        //fakePlayer.DEMO_addDestinationCardToHand(Constants.DestinationCards.CHICAGO_SANTA_FE);
        //try {
        //    curGame.addPlayer(fakePlayer);
        //} catch (DuplicateException e) {
        //    e.printStackTrace();
        //}

        makeToast("Here's the Starting Status");
        showStatusFragment(0);
        task = new WaitTask(this);
        task.execute(0);
    }



    private void makeToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }
}

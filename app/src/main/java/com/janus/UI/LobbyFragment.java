package com.janus.UI;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerModel;

import com.bignerdranch.android.shared.models.serverModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.janus.ClientModel;
import com.janus.Presenter.LobbyFragmentPresenter;
import com.janus.R;

public class LobbyFragment extends Fragment implements LobbyFragmentPresenter.View{

    public interface Context {
        void onStartGame();
    }

    private Context mContext;

    private LobbyFragmentPresenter presenter;

    private RecyclerView mPlayerList;
    private Button mStartGameButton;
    private MainActivity mMainActivity;
    private RecyclerView mPlayerRecyclerView;
    private TextView mNumberOfPlayers;
    private PlayerAdapter mPlayerAdapter;
    private Boolean isHost = false;
    private playerModel[] mPlayers = new playerModel[]{};

    private OnFragmentInteractionListener mListener;

    public LobbyFragment() {
        // Required empty public constructor
    }

    @Override
    public void updateButtons(boolean isActive) {
        mStartGameButton.setEnabled(isActive);
    }

    @Override
    public void displaySuccess() {

    }

    @Override
    public void displayError(String message) {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_lobby, container, false);
        // Inflate the layout for this fragment
        mMainActivity = (MainActivity) getActivity();

        presenter = new LobbyFragmentPresenter(this);

        mNumberOfPlayers = (TextView) v.findViewById(R.id.numPlayersView);
        presenter.setFragment();

        mPlayerRecyclerView = (RecyclerView) v.findViewById(R.id.playerRecyclerView);
        mPlayerRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mPlayerAdapter = new PlayerAdapter(mPlayers);
        mPlayerRecyclerView.setAdapter(mPlayerAdapter);

        mStartGameButton = (Button) v.findViewById(R.id.startGameButton);
        //If you are host, set the text to "Start Game"
        //Else set to "Ready"
        //mStartGameButton.setText("hello");
        mStartGameButton.setEnabled(false);
        if(isHost){
            mStartGameButton.setText("Start Game");
        } else {
            mStartGameButton.setText("Ready");
        }

        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isHost) {
                    startGameButtonClicked();
                } else {
                    readyButtonClicked();
                }
            }
        });

        if(!presenter.isModelEmpty()){
            presenter.updateUI();
        }

        return v;
    }

    private void startGameButtonClicked() {
        //if host
        presenter.startGameClicked();
        //Create and send start game request
        //Start the game

        //if not host, change player ready state and send to the server
    }

    private void readyButtonClicked(){
        presenter.readyClicked();
    }

    private class PlayerHolder extends RecyclerView.ViewHolder{

        //private ImageView mColorView; (could use these for color boxes?)
        //private Drawable mColor;
        private TextView mPersonNameView;
        private TextView mReadyStatusView;

        public void bind(playerModel p){
            mPersonNameView.setText(p.getUserName().getValue());
            if(p.isHost()){
                mReadyStatusView.setText(R.string.hostString);
            }
            else {
                if (p.isReady()) {
                    mReadyStatusView.setText(R.string.readyString);
                } else {
                    mReadyStatusView.setText(R.string.notReadyString);
                }
            }
        }

        public PlayerHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_player, parent, false));
            mPersonNameView = (TextView) itemView.findViewById(R.id.personNameView);
            mReadyStatusView = (TextView) itemView.findViewById(R.id.readyView);
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {

        private playerModel[] mPlayers;

        public PlayerAdapter(playerModel[] players){
            mPlayers = players;
        }

        @Override
        public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            return new PlayerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position) {
            playerModel p = mPlayers[position];
            holder.bind(p);
        }

        @Override
        public int getItemCount() {
            return mPlayers.length;
        }
    }

    public void update() {
        playerModel[] players = new playerModel[ClientModel.getInstance().getGame().getPlayers().size()];
        mPlayerAdapter = new PlayerAdapter(ClientModel.getInstance().getGame().getPlayers().toArray(players));
        mPlayerRecyclerView.setAdapter(mPlayerAdapter);
        String numPlayers = (mPlayerAdapter.getItemCount() + "/5 Players");
        mNumberOfPlayers.setText(numPlayers);

        playerModel host = ClientModel.getInstance().getGame().getHostPlayer();
        usernameModel username = presenter.getUsername();
        isHost = username.getValue().equals(host.getUserName().getValue());
        if (isHost) {
            mStartGameButton.setText("Start");
            int numOfPlayers = mPlayerAdapter.getItemCount();
            if(numOfPlayers >= 2 && numOfPlayers <= 5) {
                mStartGameButton.setEnabled(true);
            } else {
                mStartGameButton.setEnabled(false);
            }
        }

        if(ClientModel.getInstance().getGame().isGameStarted()){
            Intent intent = new Intent(getActivity(), GameActivity.class);
            startActivity(intent);
        }
    }

    public void updateUI(gameModel gameModel) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}

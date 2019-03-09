package com.janus.UI;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.shared.models.playerModel;
import com.janus.Presenter.MapFragmentPresenter;
import com.janus.R;

import java.util.List;


public class MapFragment extends Fragment implements MapFragmentPresenter.View{

    public interface Context {
        void onClickDrawCard();
        void onClickClaimRoute();
        void onClickDestinationSelect();
        void onClickGameStatus();
    }

    private MapFragmentPresenter presenter;
    private Button mDrawCardsButton;
    private Button mClaimRouteButton;
    private Button mDrawDestinationsButton;
    private MenuItem mStatusMenuItem; //For future use
    private Context mContext;
    private RecyclerView mTurnRecyclerView;
    private PlayerAdapter mPlayerAdapter;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_map, menu);

        //mStatusMenuItem = menu.findItem(R.id.statusAndChat);

        //mSearchMenuItem.setIcon(new IconDrawable(getActivity(), FontAwesomeIcons.fa_search).sizeDp(ICON_SIZE));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.statusAndChat:
                mContext.onClickGameStatus();
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        presenter = new MapFragmentPresenter(this);
        presenter.setFragment();
        mContext = (Context) getActivity();

        mDrawCardsButton = v.findViewById(R.id.drawCardsButton);
        mDrawCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onClickDrawCard();
            }
        });

        mClaimRouteButton = v.findViewById(R.id.claimRouteButton);
        mClaimRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onClickClaimRoute();
            }
        });

        mDrawDestinationsButton = v.findViewById(R.id.drawDestinationsButton);
        mDrawDestinationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onClickDestinationSelect();
            }
        });

        mTurnRecyclerView = v.findViewById(R.id.turnRecyclerView);
        mTurnRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        playerModel[] players = new playerModel[]{};
        mPlayerAdapter = new PlayerAdapter(players);
        mTurnRecyclerView.setAdapter(mPlayerAdapter);

        presenter.updateUI();
        return v;
    }

    private class PlayerHolder extends RecyclerView.ViewHolder{

        private TextView mPlayerNameView;
        private LinearLayout mPlayerBox;

        public void bind(playerModel p){
            mPlayerNameView.setText(p.getUserName().getValue());
            switch (p.getPlayerColor()) {
                case RED:
                    mPlayerBox.setBackgroundColor(Color.RED);
                    break;
                case YELLOW:
                    mPlayerBox.setBackgroundColor(Color.YELLOW);
                    break;
                case BLUE:
                    mPlayerBox.setBackgroundColor(Color.BLUE);
                    break;
                case GREEN:
                    mPlayerBox.setBackgroundColor(Color.GREEN);
                    break;
                case BLACK:
                    mPlayerBox.setBackgroundColor(Color.BLACK);
                    mPlayerNameView.setTextColor(Color.WHITE);
                    break;
                default:
                    break;
            }
            //ToDo: If it's this player's turn, highlight the box
        }

        public PlayerHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_turn, parent, false));
            mPlayerNameView = itemView.findViewById(R.id.playerNameView);
            mPlayerBox = itemView.findViewById(R.id.playerBox);
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

    public void updateTurnIndicator(List<playerModel> updatedPlayers){
        playerModel[] playerModels = new playerModel[updatedPlayers.size()];
        mPlayerAdapter = new PlayerAdapter(updatedPlayers.toArray(playerModels));
        mTurnRecyclerView.setAdapter(mPlayerAdapter);
    }
}

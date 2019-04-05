package com.janus.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bignerdranch.android.shared.models.playerModel;
import com.janus.ClientModel;
import com.janus.Presenter.GameResultsPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.List;

public class GameResultsFragment  extends Fragment{

    public interface Context {}

    private TextView mWinner;
    private TextView mLongestPath;
    private RecyclerView mPlayerList;

    private List<playerModel> players = new ArrayList<>();
    private PlayerAdapter adapter;
    private GameResultsPresenter presenter;

    public GameResultsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_results, container, false);

        presenter = new GameResultsPresenter();

        players = presenter.getPlayers();

        int playerWithLongestRoute = presenter.getFinalStats().get(presenter.getFinalStats().size()-1);
        players.get(playerWithLongestRoute).setPoints(players.get(playerWithLongestRoute).getPoints() + 10);
        //presenter.updateLongestRoutePoints(playerWithLongestRoute);

        int winner = determineWinner(presenter.getFinalStats());

        mWinner = v.findViewById(R.id.winner_TextView);
        mWinner.setText(players.get(winner).getUserName().getValue());

        mLongestPath = v.findViewById(R.id.longest_path_TextView);
        mLongestPath.setText(players.get(playerWithLongestRoute).getUserName().getValue());

        mPlayerList = v.findViewById(R.id.results_player_list_RecyclerView);
        mPlayerList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new PlayerAdapter(players);
        mPlayerList.setAdapter(adapter);

        return v;
    }

    private int determineWinner(List<Integer> finalStats) {
        int winner = 0;
        int winningScore = 0;
        for (int i = 0; i < finalStats.size(); i++) {
            if (finalStats.get(i) > winningScore) {
                winningScore = finalStats.get(i);
                winner = i;
            }
        }

        return winner;
    }

    private class PlayerHolder extends RecyclerView.ViewHolder{

        //private ImageView mColorView; (could use these for color boxes?)
        //private Drawable mColor;
        private TextView mName;
        private TextView mPointTotal;
        private TextView mDestinationCardPoints;
        private TextView mPointsLost;

        public void bind(playerModel p, int position){
            mName.setText(p.getUserName().getValue());
            mPointTotal.setText(Integer.toString(presenter.getFinalStats().get(position)));
            mDestinationCardPoints.setText(Integer.toString(p.calculatePointsFromDestinationCards()));
            mPointsLost.setText(Integer.toString(p.calculatePointsLostFromDestinationCards()));
        }

        public PlayerHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.player_results_list_child, parent, false));
            mName = itemView.findViewById(R.id.player_name_TextView);
            mPointTotal = itemView.findViewById(R.id.player_total_points_TextView);
            mDestinationCardPoints = itemView.findViewById(R.id.player_destination_points_TextView);
            mPointsLost = itemView.findViewById(R.id.player_points_lost_TextView);
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<GameResultsFragment.PlayerHolder> {

        private List<playerModel> players;

        public PlayerAdapter(List<playerModel> players){
            this.players = players;
        }

        @Override
        public GameResultsFragment.PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            return new GameResultsFragment.PlayerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(GameResultsFragment.PlayerHolder holder, int position) {
            playerModel p = players.get(position);
            holder.bind(p, position);
        }

        @Override
        public int getItemCount() {
            return players.size();
        }
    }
}

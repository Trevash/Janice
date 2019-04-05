package com.janus.UI;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shared.models.gameModel;
import com.janus.ClientModel; // TODO remove this import
import com.janus.Presenter.GameListFragmentPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.List;

public class GameListFragment extends Fragment implements GameListFragmentPresenter.View {

    //public static Intent newIntent(List<gameModel> games) {
    //    Intent intent = new Intent();
    //    intent.putE
    //}

    public interface Context {
        void onCreateGame();
    }

    private GameListFragmentPresenter presenter;

    private TextView mNumPlayers;
    private RecyclerView mGameList;
    private Button mJoinGame;
    private Button mCreateGame;
    private List<gameModel> games;
    private LinearLayoutManager mLayoutManager;
    private GameListAdapter mAdapter;
    private int selectedGame = -1;

    public GameListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_list, container, false);

        presenter = new GameListFragmentPresenter(this);
        presenter.setFragment();

        // could use the presenter to get the games list fragment

        //presenter.updateUI(); // This should set the games - need to test before pushing
        games = ClientModel.getInstance().getServerGameList(); // TODO pass games in somehow

        mNumPlayers = (TextView) v.findViewById(R.id.num_players_text_view);

        mGameList = (RecyclerView) v.findViewById(R.id.game_list_recycler_view);
        mGameList.setLayoutManager(new LinearLayoutManager(getActivity()));

        mAdapter = new GameListAdapter(games);
        mGameList.setAdapter(mAdapter);

        mJoinGame = (Button) v.findViewById(R.id.join_game_button);
        mJoinGame.setEnabled(false);
        mJoinGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.joinGameClicked();
            }
        });

        mCreateGame = (Button) v.findViewById(R.id.create_game_button);
        mCreateGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.createGameClicked();
            }
        });

        return v;
    }

    @Override
    public void updateGameListButtons(boolean isActive){
        mCreateGame.setEnabled(isActive);
        mJoinGame.setEnabled(isActive);
    }

    @Override
    public void displayGameListError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayGameListSuccess() {
        Toast.makeText(getActivity(), R.string.sign_in_welcome, Toast.LENGTH_LONG).show();
        ((Context) getActivity()).onCreateGame();
    }

    public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {

        List<gameModel> games;

        private class GameViewHolder extends RecyclerView.ViewHolder {

            public gameModel mGame;
            public TextView mGameName;
            public TextView mPlayerNumber;
            public RelativeLayout layout;
            public int position;

            public GameViewHolder(RelativeLayout r) {
                super(r);
                layout = r;
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.selectGame(mGame);
                        setSelectedGame(getAdapterPosition());
                        update(games);
                    }
                });

                mGameName = (TextView) r.findViewById(R.id.game_name_text_view);
                mPlayerNumber = (TextView) r.findViewById(R.id.player_number_text_view);
            }

            public void checkPosition(int position) {
                if (position == selectedGame) {
                    layout.setBackgroundColor(Color.BLUE);
                }
                else {
                    layout.setBackgroundColor(Color.WHITE);
                }
            }

            public void setSelectedGame(int position) {
                selectedGame = position;
            }
        }

        public GameListAdapter(List<gameModel> games) {
            List<gameModel> filteredGames = new ArrayList<>();
            for (int i = 0; i < games.size(); i++) {
                if (!games.get(i).isGameStarted()) {
                    filteredGames.add(games.get(i));
                }
            }
            this.games = filteredGames;
        }

        @Override
        public GameListAdapter.GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
            RelativeLayout r = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_child, parent, false);
            return new GameViewHolder(r);
        }

        @Override
        public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
            gameModel game = games.get(position);

            holder.mGameName.setText(game.getGameName());
            holder.mPlayerNumber.setText(game.numPlayers() + "/5");
            holder.mGame = game;
            holder.checkPosition(position);
        }

        @Override
        public int getItemCount() {
            return games.size();
        }
    }

    public void update(List<gameModel> games) {
        mAdapter = new GameListAdapter(games);
        mGameList.setAdapter(mAdapter);
    }

    @Override
    public void updateGameList(final List<gameModel> games) {
        // final allows games to be accessed by Runnable
        // note: actually can have null pointer exception here
        if(getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    update(games);
                }
            });
        } else {
            System.out.println("Error: updateGameList called on GameListFragment when its " +
                    "activity was null!");
        }
    }
}

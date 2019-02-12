package com.janus.UI;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.userModel;
import com.janus.ClientModel;
import com.janus.Presenter.GameListFragmentPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.List;

public class GameListFragment extends Fragment implements GameListFragmentPresenter.View {

    public interface Context {
        void onCreateGame();
    }

    private Context mContext;

    private GameListFragmentPresenter presenter;

    private TextView mNumPlayers;
    private RecyclerView mGameList;
    private Button mJoinGame;
    private Button mCreateGame;
    private List<gameModel> games;
    private LinearLayoutManager mLayoutManager;
    private GameListAdapter mAdapter;

    public GameListFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_game_list, container, false);

        presenter = new GameListFragmentPresenter(this);
        presenter.setFragment();
        games = ClientModel.getInstance().getServerGameList();

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
    public void updateButtons(boolean isActive){
        mCreateGame.setEnabled(isActive);
        mJoinGame.setEnabled(isActive);
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displaySuccess() {
        Toast.makeText(getActivity(), R.string.sign_in_welcome, Toast.LENGTH_LONG).show();
        mContext = (Context) getActivity();
        mContext.onCreateGame();
    }

    public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {

        List<gameModel> games;

        private class GameViewHolder extends RecyclerView.ViewHolder {

            public gameModel mGame;
            public TextView mGameName;
            public TextView mPlayerNumber;
            public RelativeLayout layout;

            public GameViewHolder(RelativeLayout r) {
                super(r);
                layout = r;
                layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.selectGame(mGame);
                    }
                });

                mGameName = (TextView) r.findViewById(R.id.game_name_text_view);
                mPlayerNumber = (TextView) r.findViewById(R.id.player_number_text_view);
            }
        }

        public GameListAdapter(List<gameModel> games) {
            this.games = games;
        }

        @Override
        public GameListAdapter.GameViewHolder onCreateViewHolder(ViewGroup parent, int position) {
            RelativeLayout r = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.game_list_child, parent, false);
            GameViewHolder viewHolder = new GameViewHolder(r);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(GameViewHolder holder, int position) {
            gameModel game = games.get(position);

            holder.mGameName.setText(game.getGameName());
            holder.mPlayerNumber.setText(game.numPlayers() + "/5");
            holder.mGame = game;

        }

        @Override
        public int getItemCount() {
            return games.size();
        }
    }

    public void update() {
        mAdapter = new GameListAdapter(ClientModel.getInstance().getServerGameList());
        mGameList.setAdapter(mAdapter);
    }

    @Override
    public void updateGameList(List<gameModel> games) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                update();
            }
        });
    }
}

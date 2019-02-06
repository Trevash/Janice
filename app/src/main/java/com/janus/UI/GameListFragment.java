package com.janus.UI;

import android.content.Context;
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
import android.widget.TextView;
import android.widget.Toast;

import com.janus.Presenter.GameListFragmentPresenter;
import com.janus.R;

import java.util.ArrayList;

public class GameListFragment extends Fragment implements GameListFragmentPresenter.View {

    private GameListFragmentPresenter presenter;

    private EditText mNumPlayers;
    private RecyclerView mGameList;
    private Button mJoinGame;
    private Button mCreateGame;

    private OnFragmentInteractionListener mListener;

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

        mNumPlayers = (EditText) v.findViewById(R.id.num_players_edit_text);
        mNumPlayers.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mGameList = (RecyclerView) v.findViewById(R.id.game_list_recycler_view);
        mGameList.setLayoutManager(new LinearLayoutManager(getActivity()));

        GameListAdapter adapter = new GameListAdapter(new ArrayList<String>());
        mGameList.setAdapter(adapter);

        mJoinGame = (Button) v.findViewById(R.id.join_game_button);
        mJoinGame.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                presenter.joinGameClicked(1);
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
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public class GameListAdapter extends RecyclerView.Adapter<GameListAdapter.GameViewHolder> {

        ArrayList<String> games;

        private class GameViewHolder extends RecyclerView.ViewHolder {

            private TextView mGameName;
            private TextView mPlayerNum;

            public GameViewHolder(View v) {
                super(v);
            }
        }

        public GameListAdapter(ArrayList<String> games) {
            this.games = games;
        }

        @Override
        public GameViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
            return null;
        }

        @Override
        public void onBindViewHolder(GameViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return games.size();
        }
    }
}

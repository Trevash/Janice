package com.janus.UI;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.android.shared.models.chatMessageModel;
import com.janus.Presenter.GameHistoryPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.List;

public class GameHistoryFragment extends Fragment implements GameHistoryPresenter.View {

    private GameHistoryPresenter presenter;
    private GameHistoryAdapter adapter;

    private RecyclerView mHistoryList;

    private List<chatMessageModel> history = new ArrayList<>();

    public GameHistoryFragment() {
        presenter = new GameHistoryPresenter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_game_history, container, false);

        history = presenter.getHistory();

        mHistoryList = v.findViewById(R.id.history_RecyclerView);
        mHistoryList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new GameHistoryAdapter(history);
        mHistoryList.setAdapter(adapter);

        return v;
    }

    public void updatePresenter() {
        presenter.setFragment();
    }

    public void updateUI() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                history = presenter.getHistory();

                adapter = new GameHistoryAdapter(history);
                mHistoryList.setAdapter(adapter);
            }
        });
    }

    public class GameHistoryAdapter extends RecyclerView.Adapter<GameHistoryAdapter.HistoryViewHolder> {

        List<chatMessageModel> history;

        private class HistoryViewHolder extends RecyclerView.ViewHolder {

            private TextView mUsername;
            private TextView mMessage;

            public HistoryViewHolder(RelativeLayout l) {
                super(l);

                mUsername = l.findViewById(R.id.userName_TextView);
                mMessage = l.findViewById(R.id.message_TextView);
            }
        }

        public GameHistoryAdapter(List<chatMessageModel> history) {
            this.history = history;
        }

        @Override
        public GameHistoryAdapter.HistoryViewHolder onCreateViewHolder(ViewGroup parent, int position) {
            RelativeLayout l = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_list_child, parent, false);
            return new HistoryViewHolder(l);
        }

        @Override
        public void onBindViewHolder(HistoryViewHolder holder, int position) {
            chatMessageModel historyItem = history.get(position);

            holder.mUsername.setText(historyItem.getUsername().getValue() + ": ");
            holder.mMessage.setText(historyItem.getMessage());
        }

        @Override
        public int getItemCount() {
            return history.size();
        }
    }
}

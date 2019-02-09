package com.janus.UI;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.janus.Presenter.LobbyFragmentPresenter;
import com.janus.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LobbyFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LobbyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LobbyFragment extends Fragment implements LobbyFragmentPresenter.View{
    private LobbyFragmentPresenter presenter;

    private RecyclerView mPlayerList;
    private Button mStartGameButton;
    private MainActivity mMainActivity;
    private RecyclerView mPlayerRecyclerView;
    private TextView mNumberOfPlayers;
    private PlayerAdapter mPlayerAdapter;

    private OnFragmentInteractionListener mListener;

    public LobbyFragment() {
        // Required empty public constructor
    }

    @Override
    public void updateButtons(boolean isActive) {
        mStartGameButton.setEnabled(true);

        //if less than 2 players or not everybody has checked ready, button is disabled
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

        mStartGameButton = (Button) v.findViewById(R.id.startGameButton);
        mStartGameButton.setEnabled(false);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGameButtonClicked();
            }
        });
        return v;
    }

    private void startGameButtonClicked() {
        //Call method in LobbyPresenter
        //Create and send start game request
        //Start the game
    }

    private class PlayerHolder extends RecyclerView.ViewHolder{

        //private ImageView mColorView; (could use these for color boxes?)
        //private Drawable mColor;
        private TextView mPersonNameView;
        private TextView mReadyStatusView;

        public void bind(/*Player p*/){
            //mPersonNameView.setText(/*p.getName()*/);
            //mReadyStatusView.setText(/*p.getStatus()*/);
        }

        public PlayerHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_player, parent, false));
            mPersonNameView = (TextView) itemView.findViewById(R.id.personNameView);
            mReadyStatusView = (TextView) itemView.findViewById(R.id.readyView);
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {

        private Player[] mPlayers;

        public PlayerAdapter(Player[] players){
            mPlayers = players;
        }

        @Override
        public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            return new PlayerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position) {
            Player p = mPlayers[position];
            holder.bind(p);
        }

        @Override
        public int getItemCount() {
            return mPlayers.length;
        }
    }

    private void updateUI() {
        mPlayerAdapter = new PlayerAdapter(/*Put in list of players here*/);
        mPlayerRecyclerView.setAdapter(mPlayerAdapter);
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

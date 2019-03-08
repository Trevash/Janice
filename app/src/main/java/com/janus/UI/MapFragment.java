package com.janus.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.janus.R;


public class MapFragment extends Fragment {

    public interface Context {
        void onClickDrawCard();
        void onClickClaimRoute();
        void onClickDestinationSelect();
        void onClickGameStatus();
    }

    private Button mDrawCardsButton;
    private Button mClaimRouteButton;
    private Button mDrawDestinationsButton;
    private MenuItem mStatusMenuItem; //For future use
    private Context mContext;

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
        mDrawCardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onClickDestinationSelect();
            }
        });

        return v;
    }
}

package com.janus.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.janus.R;


public class MapFragment extends Fragment {

    public interface Context {
        void onClickDrawCard();
        void onClickClaimRoute();
        void onClickDestinationSelect();
        void onClickGameStatus();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_map, menu);

        /*mSearchMenuItem = menu.findItem(R.id.search_icon);
        mFilterMenuItem = menu.findItem(R.id.filter_icon);
        mSettingsMenuItem = menu.findItem(R.id.settings_icon);

        mSearchMenuItem.setIcon(new IconDrawable(getActivity(), FontAwesomeIcons.fa_search).sizeDp(ICON_SIZE));
        mFilterMenuItem.setIcon(new IconDrawable(getActivity(), FontAwesomeIcons.fa_filter).sizeDp(ICON_SIZE));
        mSettingsMenuItem.setIcon(new IconDrawable(getActivity(), FontAwesomeIcons.fa_gear).sizeDp(ICON_SIZE));*/

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map, container, false);
    }
}

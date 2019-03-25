package com.janus.UI;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.janus.Presenter.StatusFragmentPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment implements StatusFragmentPresenter.View {

    public interface Context {
        void onFinishAction();
        void onMapFragmentSelected();
    }

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private StatusFragmentPresenter presenter;
    private Context context;
    private View view;
    private StatusListAdapter adapter;
    private List<playerModel> players = new ArrayList<>();

    private ChatFragment chatFragment;
    private DestinationRoutesFragment destinationRoutesFragment;
    private GameHistoryFragment gameHistoryFragment;

    private List<TextView> colorTextViews;
    private List<TextView> playerOneStatusTextViews;
    private List<TextView> playerTwoStatusTextViews;
    private List<TextView> playerThreeStatusTextViews;
    private List<TextView> playerFourStatusTextViews;
    private List<TextView> playerFiveStatusTextViews;

    private TextView mTotalTrainCards;
    private TextView mTotalDestinationCards;
    private RecyclerView mStatusList;

    private int whichFragmentToShow = 0;

    public StatusFragment() {}
    
    public void setWhichFragmentToShow(int i) {
    	whichFragmentToShow = i;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_status, menu);

        //mStatusMenuItem = menu.findItem(R.id.statusAndChat);

        //mSearchMenuItem.setIcon(new IconDrawable(getActivity(), FontAwesomeIcons.fa_search).sizeDp(ICON_SIZE));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.map_menu_item:
                context.onMapFragmentSelected();
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        switch (whichFragmentToShow) {
            case 0:
                chatFragment.updatePresenter();
                break;
            case 1:
                destinationRoutesFragment.updatePresenter();
                break;
            case 2:
                //gameHistoryFragment.updatePresenter();
                break;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_status, container, false);
        context = (Context) getActivity();
        presenter = new StatusFragmentPresenter(this);
        presenter.setStatusPresenter();
        this.players = presenter.getPlayers();

        viewPager = v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        
        viewPager.setCurrentItem(whichFragmentToShow);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        chatFragment.updatePresenter();
                        break;
                    case 1:
                        destinationRoutesFragment.updatePresenter();
                        break;
                    case 2:
                        gameHistoryFragment.updatePresenter();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        tabLayout = v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        mStatusList = v.findViewById(R.id.status_list_RecyclerView);
        mStatusList.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter = new StatusListAdapter(players);
        mStatusList.setAdapter(adapter);

        this.view = v;

        //buildStats(v);

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        LinearLayout layout = view.findViewById(R.id.status_fragment);
        layout.setBackgroundColor(Color.WHITE);
    }

    private void setupViewPager(ViewPager viewPager) {
        //for (Fragment fragment : getActivity().getSupportFragmentManager().getFragments()) {
        //    getActivity().getSupportFragmentManager().beginTransaction().remove(fragment).commit();
        //}

        // TODO currently has a warning saying that this can produce a null-pointer exception - may want to fix
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        chatFragment = new ChatFragment();
        destinationRoutesFragment = new DestinationRoutesFragment();
        gameHistoryFragment = new GameHistoryFragment();
        adapter.addFragment(chatFragment, "Chat");
        adapter.addFragment(destinationRoutesFragment, "Destinations");
        adapter.addFragment(gameHistoryFragment, "History");
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    /*private void buildStats(View v) {
        String currentPlayerName = presenter.getCurrentPlayer().getUserName().getValue();
        List<playerModel> players = presenter.getPlayers();
        List<int[]> stats = presenter.getStats();

        TextView mStatusName = v.findViewById(R.id.status_name);
        mStatusName.setText(currentPlayerName);

        colorTextViews = new ArrayList<>();

        colorTextViews.add((TextView)v.findViewById(R.id.num_red));
        colorTextViews.add((TextView)v.findViewById(R.id.num_blue));
        colorTextViews.add((TextView)v.findViewById(R.id.num_green));
        colorTextViews.add((TextView)v.findViewById(R.id.num_purple));
        colorTextViews.add((TextView)v.findViewById(R.id.num_orange));
        colorTextViews.add((TextView)v.findViewById(R.id.num_white));
        colorTextViews.add((TextView)v.findViewById(R.id.num_black));
        colorTextViews.add((TextView)v.findViewById(R.id.num_yellow));
        colorTextViews.add((TextView)v.findViewById(R.id.num_rainbow));

        int[] numTrainCardsPerColor = stats.get(0);

        for (int i = 0; i < numTrainCardsPerColor.length; i++) {
            colorTextViews.get(i).setText(": " +Integer.toString(numTrainCardsPerColor[i]));
        }

        int[] totals = stats.get(1);
        mTotalTrainCards = v.findViewById(R.id.total_cards);
        mTotalTrainCards.setText(Integer.toString(totals[0]));
        mTotalDestinationCards = v.findViewById(R.id.total_destination_cards);
        mTotalDestinationCards.setText(Integer.toString(totals[1]));

        playerOneStatusTextViews = new ArrayList<>();
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_name));
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_points));
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_number_of_trains));
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_number_of_cards));
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_number_of_destination_cards));

        playerOneStatusTextViews.get(0).setText(players.get(0).getUserName().getValue());

        for (int i = 1; i < playerOneStatusTextViews.size(); i++) {
            playerOneStatusTextViews.get(i).setText(Integer.toString(stats.get(2)[i-1]));
        }

        // stats[0] is integer number of cards of each type
        if (stats.size() > 3) {
            playerTwoStatusTextViews = new ArrayList<>();
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_name));
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_points));
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_number_of_trains));
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_number_of_cards));
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_number_of_destination_cards));

            playerTwoStatusTextViews.get(0).setText(players.get(1).getUserName().getValue());

            for (int i = 1; i < playerTwoStatusTextViews.size(); i++) {
                playerTwoStatusTextViews.get(i).setText(Integer.toString(stats.get(3)[i-1]));
            }
        }

        if (stats.size() > 4) {
            playerThreeStatusTextViews = new ArrayList<>();
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_name));
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_points));
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_number_of_trains));
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_number_of_cards));
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_number_of_destination_cards));

            playerThreeStatusTextViews.get(0).setText(players.get(2).getUserName().getValue());

            for (int i = 1; i < playerThreeStatusTextViews.size(); i++) {
                playerThreeStatusTextViews.get(i).setText(Integer.toString(stats.get(4)[i-1]));
            }
        }

        if (stats.size() > 5) {
            playerFourStatusTextViews = new ArrayList<>();
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_name));
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_points));
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_number_of_trains));
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_number_of_cards));
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_number_of_destination_cards));

            playerFourStatusTextViews.get(0).setText(players.get(3).getUserName().getValue());

            for (int i = 1; i < playerFourStatusTextViews.size(); i++) {
                playerFourStatusTextViews.get(i).setText(Integer.toString(stats.get(5)[i-1]));
            }
        }

        if (stats.size() > 6) {
            playerFiveStatusTextViews = new ArrayList<>();
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_name));
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_points));
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_number_of_trains));
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_number_of_cards));
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_number_of_destination_cards));

            playerOneStatusTextViews.get(0).setText(players.get(4).getUserName().getValue());

            for (int i = 1; i < playerFiveStatusTextViews.size(); i++) {
                playerFiveStatusTextViews.get(i).setText(Integer.toString(stats.get(6)[i-1]));
            }
        }
    }*/

    private void buildPlayerStats(int playerPosition, StatusListAdapter.StatusViewHolder holder) {
        playerModel playerInfo = presenter.getPlayers().get(playerPosition);
        int[] playerStats = presenter.getStats().get(playerPosition + 2);

        holder.mPlayerName.setText(playerInfo.getUserName().getValue());
        holder.mPlayerPoints.setText(Integer.toString(playerStats[0]));
        holder.mNumTrains.setText(Integer.toString(playerStats[1]));
        holder.mNumCards.setText(Integer.toString(playerStats[2]));
        holder.mNumDestinationCards.setText(Integer.toString(playerStats[3]));
    }

    public void updateUI() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //buildStats(view);

                    players = presenter.getPlayers();
                    adapter = new StatusListAdapter(players);
                    mStatusList.setAdapter(adapter);
                }
            });
        }
    }

    public class StatusListAdapter extends RecyclerView.Adapter<StatusListAdapter.StatusViewHolder> {

        List<playerModel> players;

        private class StatusViewHolder extends RecyclerView.ViewHolder {

            public TextView mPlayerName;
            public TextView mPlayerPoints;
            public TextView mNumTrains;
            public TextView mNumCards;
            public TextView mNumDestinationCards;

            public LinearLayout layout;

            public StatusViewHolder(LinearLayout r) {
                super(r);
                layout = r;

                mPlayerName = r.findViewById(R.id.player_TextView);
                mPlayerPoints = r.findViewById(R.id.points_TextView);
                mNumTrains = r.findViewById(R.id.num_trains_TextView);
                mNumCards = r.findViewById(R.id.num_cards_TextView);
                mNumDestinationCards = r.findViewById(R.id.num_destination_cards_TextView);
            }
        }

        public StatusListAdapter(List<playerModel> players) {

            this.players = players;
        }

        @Override
        public StatusListAdapter.StatusViewHolder onCreateViewHolder(ViewGroup parent, int position) {
            LinearLayout r = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.status_list_child, parent, false);
            StatusFragment.StatusListAdapter.StatusViewHolder viewHolder = new StatusFragment.StatusListAdapter.StatusViewHolder(r);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(StatusListAdapter.StatusViewHolder holder, int position) {
            buildPlayerStats(position, holder);
        }

        @Override
        public int getItemCount() {
            return players.size();
        }
    }
}

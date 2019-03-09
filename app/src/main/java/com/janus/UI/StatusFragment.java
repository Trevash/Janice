package com.janus.UI;

import android.support.design.widget.TabLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.janus.Presenter.StatusFragmentPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.List;

public class StatusFragment extends Fragment implements StatusFragmentPresenter.View {

    public interface Context {
        void onFinishAction();
    }

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private StatusFragmentPresenter presenter;

    private List<TextView> colorTextViews;
    private List<TextView> playerOneStatusTextViews;
    private List<TextView> playerTwoStatusTextViews;
    private List<TextView> playerThreeStatusTextViews;
    private List<TextView> playerFourStatusTextViews;
    private List<TextView> playerFiveStatusTextViews;

    public StatusFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_status, container, false);

        presenter = new StatusFragmentPresenter(this);

        viewPager = v.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = v.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        buildStats(v);

        return v;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(new ChatFragment(), "Chat");
        adapter.addFragment(new DestinationRoutesFragment(), "Destinations");
        adapter.addFragment(new GameHistoryFragment(), "History");
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
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

    private void buildStats(View v) {
        List<int[]> stats = presenter.getStats();

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
            colorTextViews.get(i).setText(numTrainCardsPerColor[i]);
        }

        playerOneStatusTextViews = new ArrayList<>();
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_name));
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_points));
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_number_of_trains));
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_number_of_cards));
        playerOneStatusTextViews.add((TextView) v.findViewById(R.id.player_one_number_of_routes));
        for (int i = 0; i < playerOneStatusTextViews.size(); i++) {
            playerOneStatusTextViews.get(i).setText(stats.get(1)[i]);
        }

        if (stats.size() > 2) {
            playerTwoStatusTextViews = new ArrayList<>();
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_name));
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_points));
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_number_of_trains));
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_number_of_cards));
            playerTwoStatusTextViews.add((TextView) v.findViewById(R.id.player_two_number_of_routes));
            for (int i = 0; i < playerTwoStatusTextViews.size(); i++) {
                playerTwoStatusTextViews.get(i).setText(stats.get(2)[i]);
            }
        }

        if (stats.size() > 3) {
            playerThreeStatusTextViews = new ArrayList<>();
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_name));
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_points));
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_number_of_trains));
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_number_of_cards));
            playerThreeStatusTextViews.add((TextView) v.findViewById(R.id.player_three_number_of_routes));
            for (int i = 0; i < playerThreeStatusTextViews.size(); i++) {
                playerThreeStatusTextViews.get(i).setText(stats.get(3)[i]);
            }
        }

        if (stats.size() > 4) {
            playerFourStatusTextViews = new ArrayList<>();
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_name));
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_points));
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_number_of_trains));
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_number_of_cards));
            playerFourStatusTextViews.add((TextView) v.findViewById(R.id.player_four_number_of_routes));
            for (int i = 0; i < playerFourStatusTextViews.size(); i++) {
                playerFourStatusTextViews.get(i).setText(stats.get(4)[i]);
            }
        }

        if (stats.size() > 5) {
            playerFiveStatusTextViews = new ArrayList<>();
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_name));
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_points));
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_number_of_trains));
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_number_of_cards));
            playerFiveStatusTextViews.add((TextView) v.findViewById(R.id.player_five_number_of_routes));
            for (int i = 0; i < playerFiveStatusTextViews.size(); i++) {
                playerFiveStatusTextViews.get(i).setText(stats.get(5)[i]);
            }
        }
    }
}

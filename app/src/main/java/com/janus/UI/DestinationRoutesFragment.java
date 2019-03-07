package com.janus.UI;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.janus.Presenter.DestinationRoutesFragmentPresenter;
import com.janus.R;

import com.bignerdranch.android.shared.models.*;

import java.util.ArrayList;
import java.util.List;

public class DestinationRoutesFragment extends Fragment implements DestinationRoutesFragmentPresenter.View {

    private DestinationRoutesFragmentPresenter presenter;
    private RouteListAdapter adapter;

    private RecyclerView mRouteList;

    private List<destinationCardModel> routes = new ArrayList<>();

    public DestinationRoutesFragment() {}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_destination_routes, container, false);

        presenter = new DestinationRoutesFragmentPresenter(this);
        presenter.setFragment();

        /* For testing Dummy Data
        routes.add(new destinationCardModel(new cityModel("New York"), new cityModel("Salt Lake"), 10));
        routes.add(new destinationCardModel(new cityModel("Orlando"), new cityModel("Los Angelos"), 12));
        */

        mRouteList = v.findViewById(R.id.destination_RecyclerView);
        mRouteList.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new RouteListAdapter(routes);
        mRouteList.setAdapter(adapter);



        return v;
    }

    public void updateSendButton(boolean isActive) {

    }

    public class RouteListAdapter extends RecyclerView.Adapter<DestinationRoutesFragment.RouteListAdapter.RouteViewHolder> {
        List<destinationCardModel> destinationCards;

        private class RouteViewHolder extends RecyclerView.ViewHolder {

            private chatMessageModel chat;
            private TextView mRouteInfo;

            public RouteViewHolder(ConstraintLayout r) {
                super(r);

                mRouteInfo = r.findViewById(R.id.destinationRouteInfo);
            }
        }

        public RouteListAdapter(List<destinationCardModel> destinationCards) {
            this.destinationCards = destinationCards;
        }

        @Override
        public DestinationRoutesFragment.RouteListAdapter.RouteViewHolder onCreateViewHolder(ViewGroup parent, int position) {
            ConstraintLayout r = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.destination_route_list_child, parent, false);
            return new DestinationRoutesFragment.RouteListAdapter.RouteViewHolder(r);
        }

        @Override
        public void onBindViewHolder(DestinationRoutesFragment.RouteListAdapter.RouteViewHolder holder, int position) {
            destinationCardModel destinationCard = destinationCards.get(position);

            holder.mRouteInfo.setText(destinationCard.getFormattedDestinationCard());
        }

        @Override
        public int getItemCount() {
            return destinationCards.size();
        }
    }
}

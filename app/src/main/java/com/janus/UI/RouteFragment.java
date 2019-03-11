package com.janus.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.shared.models.singleRouteModel;
import com.janus.Presenter.RouteFragmentPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.List;

public class RouteFragment extends Fragment implements RouteFragmentPresenter.View{

    public interface Context {
        void onFinishAction();
    }

    private RouteFragmentPresenter presenter;
    private RecyclerView mRouteRecyclerView;
    private RouteAdapter mRouteAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_route, container, false);
        presenter = new RouteFragmentPresenter(this);
        presenter.setFragment();

        mRouteRecyclerView = v.findViewById(R.id.routeRecyclerView);
        mRouteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<singleRouteModel> routes = new ArrayList<>();
        mRouteAdapter = new RouteAdapter(routes);
        mRouteRecyclerView.setAdapter(mRouteAdapter);

        presenter.updateUI();

        return v;
    }

    private class RouteHolder extends RecyclerView.ViewHolder{

        private TextView mRouteCitiesView;
        private TextView mRouteLengthView;
        private TextView mRouteColorView;
        private Button mClaimButton;

        public void bind(singleRouteModel r){
            mRouteCitiesView.setText(r.toString());
            mRouteLengthView.setText(Integer.toString(r.getLength()));
            mRouteColorView.setText(r.getTrainColor().toString());
        }

        public RouteHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_route, parent, false));
            mRouteCitiesView = itemView.findViewById(R.id.routeCitiesView);
            mRouteLengthView = itemView.findViewById(R.id.routeLengthView);
            mRouteColorView = itemView.findViewById(R.id.routeColorView);
            mClaimButton = itemView.findViewById(R.id.claimButton);

            //This button could be eliminated by making the list elements clickable
            mClaimButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //presenter.claimRoute(routeID);
                }
            });
        }
    }

    private class RouteAdapter extends RecyclerView.Adapter<RouteHolder> {

        private List<singleRouteModel> mRoutes;

        public RouteAdapter(List<singleRouteModel> routes){
            mRoutes = routes;
        }

        @Override
        public RouteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            return new RouteHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(RouteHolder holder, int position) {
            singleRouteModel r = mRoutes.get(position);
            holder.bind(r);
        }

        @Override
        public int getItemCount() {
            return mRoutes.size();
        }
    }

    public void updateRoutes(List<singleRouteModel> routes){
        mRouteAdapter = new RouteAdapter(routes);
        mRouteRecyclerView.setAdapter(mRouteAdapter);
    }
}

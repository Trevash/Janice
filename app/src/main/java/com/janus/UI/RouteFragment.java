package com.janus.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.shared.models.colors.routeColorEnum;
import com.bignerdranch.android.shared.models.singleRouteModel;
import com.janus.Presenter.RouteFragmentPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.List;

public class RouteFragment extends Fragment implements RouteFragmentPresenter.View {

    public interface Context {
        void onFinishAction();
        void onMapFragmentSelected();
        void onEndGame();
    }

    private RouteFragmentPresenter presenter;
    private RecyclerView mRouteRecyclerView;
    private RouteAdapter mRouteAdapter;
    private Context mContext;

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_route, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.mapButton:
                mContext.onMapFragmentSelected();
                return true;
            default:
                return true;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setFragment();
        presenter.updateUI();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_route, container, false);
        presenter = new RouteFragmentPresenter(this);
        presenter.setFragment();
        mContext = (Context) getActivity();

        mRouteRecyclerView = v.findViewById(R.id.routeRecyclerView);
        mRouteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        List<singleRouteModel> routes = new ArrayList<>();
        mRouteAdapter = new RouteAdapter(routes);
        mRouteRecyclerView.setAdapter(mRouteAdapter);

        presenter.updateUI();

        return v;
    }

    private class RouteHolder extends RecyclerView.ViewHolder implements AdapterView.OnItemSelectedListener{

        private TextView mRouteCitiesView;
        private TextView mRouteLengthView;
        private TextView mRouteColorView;
        private Spinner mColorSpinner;
        private Button mClaimButton;
        private singleRouteModel mRoute;
        private String mSpinnerValue;

        public void bind(singleRouteModel r){
            mRouteCitiesView.setText(r.toString());
            mRouteLengthView.setText(Integer.toString(r.getLength()));
            mRouteColorView.setText(r.getTrainColor().toString());

            mColorSpinner.setVisibility(View.VISIBLE);
            if(!r.getTrainColor().equals(routeColorEnum.GRAY)){
                mColorSpinner.setVisibility(View.GONE);
            } else {
                String[] colors = presenter.getGrayRouteColorChoices(r.getLength());
                ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_dropdown_item, colors);
                mColorSpinner.setAdapter(adapter);
                mColorSpinner.setOnItemSelectedListener(this);
            }

            mRoute = r;
        }

        public void onItemSelected(AdapterView<?> parent, View view,
                                   int pos, long id) {
            mSpinnerValue = (String) parent.getItemAtPosition(pos);
        }

        public void onNothingSelected(AdapterView<?> parent) {
            // Another interface callback
        }

        public RouteHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_route, parent, false));
            mRouteCitiesView = itemView.findViewById(R.id.routeCitiesView);
            mRouteLengthView = itemView.findViewById(R.id.routeLengthView);
            mRouteColorView = itemView.findViewById(R.id.routeColorView);
            mColorSpinner = itemView.findViewById(R.id.colorSpinner);
            mClaimButton = itemView.findViewById(R.id.claimButton);

            //This button could be eliminated by making the list elements clickable
            mClaimButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    routeColorEnum color;
                    if(mSpinnerValue != null) { //If gray route and has spinner
                        color = presenter.convertStringToColor(mSpinnerValue);
                    } else { //Not grey route
                        color = mRoute.getTrainColor();
                    }
                    if(presenter.connectedToServer()) {
                        if (presenter.canClaimRoute()) {
                            presenter.claimRoute(mRoute, color);
                            mContext.onFinishAction();
                        } else {
                            Toast.makeText(getActivity(), R.string.notTurnString, Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(getActivity(), "The Server is Down!", Toast.LENGTH_LONG).show();
                    }
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

    public void endGame(){
        mContext.onEndGame();
    }

    public void lastRoundToast(){
        Toast.makeText(getActivity(), "This is the Last Round!", Toast.LENGTH_LONG).show();
    }
}

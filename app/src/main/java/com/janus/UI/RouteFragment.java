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

import com.bignerdranch.android.shared.models.singleRouteModel;
import com.janus.Presenter.RouteFragmentPresenter;
import com.janus.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RouteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RouteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RouteFragment extends Fragment implements RouteFragmentPresenter.View{

    private RouteFragmentPresenter presenter;
    private RecyclerView mRouteRecyclerView;
    private RouteAdapter mRouteAdapter;

    public RouteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RouteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RouteFragment newInstance(String param1, String param2) {
        RouteFragment fragment = new RouteFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_route, container, false);
        presenter = new RouteFragmentPresenter(this);
        presenter.setFragment();

        mRouteRecyclerView = v.findViewById(R.id.routeRecyclerView);
        presenter.updateUI();

        return v;
    }

    private class RouteHolder extends RecyclerView.ViewHolder{

        private TextView mRouteCitiesView;
        private TextView mRouteLengthView;
        private TextView mRouteColorView;
        private Button mClaimRouteButton;

        public void bind(singleRouteModel r){
            mRouteCitiesView.setText(r.getCity1() + " - " + r.getCity2());
            mRouteLengthView.setText(r.getLength());
            mRouteColorView.setText(r.getTrainColor().toString());
        }

        public RouteHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_route, parent, false));
            mRouteCitiesView = itemView.findViewById(R.id.routeCitiesView);
            mRouteLengthView = itemView.findViewById(R.id.routeLengthView);
            mRouteColorView = itemView.findViewById(R.id.routeColorView);
            mClaimRouteButton = itemView.findViewById(R.id.claimRouteButton);

            //This button could be eliminated by making the list elements clickable
            mClaimRouteButton.setOnClickListener(new View.OnClickListener() {
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

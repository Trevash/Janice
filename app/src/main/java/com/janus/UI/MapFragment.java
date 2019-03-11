package com.janus.UI;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bignerdranch.android.shared.Constants;
import com.bignerdranch.android.shared.models.abstractDoubleRoute;
import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.cityModel;
import com.bignerdranch.android.shared.models.colors.playerColorEnum;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.models.singleRouteModel;
import com.janus.Presenter.MapFragmentPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MapFragment extends Fragment implements MapFragmentPresenter.View{

    public interface Context {
        void onClickDrawCard();
        void onClickClaimRoute();
        void onClickDestinationSelect();
        void onClickGameStatus();
        void onClickRunDemo();
    }

    private MapFragmentPresenter presenter;
    private Button mDrawCardsButton;
    private Button mClaimRouteButton;
    private Button mDrawDestinationsButton;
    private Button mRunDemoButton;
    private MenuItem mStatusMenuItem; //For future use
    private Context mContext;
    private RecyclerView mTurnRecyclerView;
    private PlayerAdapter mPlayerAdapter;
    private ArrayList<abstractRoute> mRoutes = new ArrayList<>();
    private ImageView mapImage;
    private Map<routeColorEnum, Integer> colorMap = new HashMap<>();
    private Map<playerColorEnum, Integer> colorMap2 = new HashMap<>();

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
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        presenter = new MapFragmentPresenter(this);
        presenter.setFragment();
        mContext = (Context) getActivity();

        colorMap.put(routeColorEnum.BLUE, Color.BLUE);
        colorMap.put(routeColorEnum.BLACK, Color.BLACK);
        colorMap.put(routeColorEnum.ORANGE, Color.rgb(255, 165, 0));
        colorMap.put(routeColorEnum.GREEN, Color.GREEN);
        colorMap.put(routeColorEnum.GRAY, Color.GRAY);
        colorMap.put(routeColorEnum.PURPLE, Color.MAGENTA);
        colorMap.put(routeColorEnum.RED, Color.RED);
        colorMap.put(routeColorEnum.WHITE, Color.WHITE);
        colorMap.put(routeColorEnum.YELLOW, Color.YELLOW);

        colorMap2.put(playerColorEnum.BLUE, Color.BLUE);
        colorMap2.put(playerColorEnum.BLACK, Color.BLACK);
        colorMap2.put(playerColorEnum.GREEN, Color.GREEN);
        colorMap2.put(playerColorEnum.RED, Color.RED);
        colorMap2.put(playerColorEnum.YELLOW, Color.YELLOW);

        mapImage = v.findViewById(R.id.drawImageView);
        mapImage.setImageDrawable(new Drawings());

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
        mDrawDestinationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onClickDestinationSelect();
            }
        });

        mRunDemoButton = v.findViewById(R.id.demo_Button);
        mRunDemoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.onClickRunDemo();
            }
        });

        mTurnRecyclerView = v.findViewById(R.id.turnRecyclerView);
        mTurnRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        playerModel[] players = new playerModel[]{};
        mPlayerAdapter = new PlayerAdapter(players);
        mTurnRecyclerView.setAdapter(mPlayerAdapter);

        presenter.updateUI();
        return v;
    }

    private class PlayerHolder extends RecyclerView.ViewHolder{

        private TextView mPlayerNameView;
        private LinearLayout mPlayerBox;

        public void bind(playerModel p){
            mPlayerNameView.setText(p.getUserName().getValue());
            switch (p.getPlayerColor()) {
                case RED:
                    if(presenter.isPlayersTurn(p.getId())){
                        mPlayerBox.setBackgroundColor(Color.RED);
                    }
                    mPlayerBox.setBackgroundColor(getResources().getColor(R.color.translucentRed));
                    break;
                case YELLOW:
                    if(presenter.isPlayersTurn(p.getId())){
                        mPlayerBox.setBackgroundColor(Color.YELLOW);
                    }
                    mPlayerBox.setBackgroundColor(getResources().getColor(R.color.translucentYellow));
                    break;
                case BLUE:
                    if(presenter.isPlayersTurn(p.getId())){
                        mPlayerBox.setBackgroundColor(Color.BLUE);
                    }
                    mPlayerBox.setBackgroundColor(getResources().getColor(R.color.translucentBlue));
                    break;
                case GREEN:
                    if(presenter.isPlayersTurn(p.getId())){
                        mPlayerBox.setBackgroundColor(Color.GREEN);
                    }
                    mPlayerBox.setBackgroundColor(getResources().getColor(R.color.translucentGreen));
                    break;
                case BLACK:
                    if(presenter.isPlayersTurn(p.getId())){
                        mPlayerBox.setBackgroundColor(Color.BLACK);
                    }
                    mPlayerBox.setBackgroundColor(getResources().getColor(R.color.translucentBlack));
                    mPlayerNameView.setTextColor(Color.WHITE);
                    break;
                default:
                    break;
            }
        }

        public PlayerHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_turn, parent, false));
            mPlayerNameView = itemView.findViewById(R.id.playerNameView);
            mPlayerBox = itemView.findViewById(R.id.playerBox);
        }
    }

    private class PlayerAdapter extends RecyclerView.Adapter<PlayerHolder> {

        private playerModel[] mPlayers;

        public PlayerAdapter(playerModel[] players){
            mPlayers = players;
        }

        @Override
        public PlayerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            return new PlayerHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PlayerHolder holder, int position) {
            playerModel p = mPlayers[position];
            holder.bind(p);
        }

        @Override
        public int getItemCount() {
            return mPlayers.length;
        }
    }

    public void updateTurnIndicator(List<playerModel> updatedPlayers){
        playerModel[] playerModels = new playerModel[updatedPlayers.size()];
        mPlayerAdapter = new PlayerAdapter(updatedPlayers.toArray(playerModels));
        mTurnRecyclerView.setAdapter(mPlayerAdapter);
    }

    public void updateRoutes(List<abstractRoute> updatedRoutes){
        mRoutes = new ArrayList<>(updatedRoutes);
        mapImage.setImageDrawable(new Drawings());
    }

    private class Drawings extends Drawable {
        @Override
        public void draw(Canvas canvas) {
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            for(int i = 0; i < Constants.Cities.CITIES.length; i++) {
                cityModel currentCity = Constants.Cities.CITIES[i];
                float xVal = currentCity.getxCoordinate() * 2;
                float yVal = (float) (currentCity.getyCoordinate() * 1.7);
                canvas.drawCircle(xVal, yVal, 15, paint);
            }
            paint.setStrokeWidth(13);
            for(int i = 0; i < mRoutes.size(); i++){
                abstractRoute route = mRoutes.get(i);
                cityModel city1 = route.getCity1();
                cityModel city2 = route.getCity2();
                float city1X = city1.getxCoordinate() * 2;
                float city1Y = (float) (city1.getyCoordinate() * 1.7);
                float city2X = city2.getxCoordinate() * 2;
                float city2Y = (float) (city2.getyCoordinate() * 1.7);
                if(route.getClass() == singleRouteModel.class){
                    singleRouteModel singleRoute = (singleRouteModel) route;
                    paint.setColor(colorMap.get(singleRoute.getTrainColor()));
                    canvas.drawLine(city1X, city1Y, city2X, city2Y, paint);
                    if(!singleRoute.claimable()){
                        float dotLocationX = (float) ((city1X + city2X) / 2.0);
                        float dotLocationY = (float) ((city1Y + city2Y) / 2.0);
                        playerModel claimer = presenter.getPlayerByID(singleRoute.getClaimer());
                        paint.setColor(colorMap2.get(claimer.getPlayerColor()));
                        canvas.drawCircle(dotLocationX, dotLocationY, 7, paint);
                    }
                } else {
                    abstractDoubleRoute doubleRoute = (abstractDoubleRoute) route;
                    paint.setColor(colorMap.get(doubleRoute.getTrainColor1()));
                    canvas.drawLine(city1X + 10, city1Y + 10,
                            city2X + 10, city2Y + 10, paint);
                    paint.setColor(colorMap.get(doubleRoute.getTrainColor2()));
                    canvas.drawLine(city1X - 10, city1Y - 10,
                            city2X - 10, city2Y - 10, paint);
                    if(!doubleRoute.claimableRoute1()){
                        float dotLocationX = (float) (((city1X + 10) + (city2X + 10)) / 2.0);
                        float dotLocationY = (float) (((city1Y + 10) + (city2Y + 10)) / 2.0);
                        playerModel claimer = presenter.getPlayerByID(doubleRoute.getClaimer1());
                        paint.setColor(colorMap2.get(claimer.getPlayerColor()));
                        canvas.drawCircle(dotLocationX, dotLocationY, 12, paint);
                    }
                    if(!doubleRoute.claimableRoute2()){
                        float dotLocationX = (float) (((city1X - 10) + (city2X - 10)) / 2.0);
                        float dotLocationY = (float) (((city1Y - 10) + (city2Y - 10)) / 2.0);
                        playerModel claimer = presenter.getPlayerByID(doubleRoute.getClaimer2());
                        paint.setColor(colorMap2.get(claimer.getPlayerColor()));
                        canvas.drawCircle(dotLocationX, dotLocationY, 12, paint);
                    }
                }
            }
            //canvas.drawCircle((296 * 2), (float) (328 * 1.7), 15, paint); //new SLC coordinates
            //canvas.drawCircle(620, 570, 15, paint); //SLC
            //New Denver: 444, 390
            //canvas.drawCircle(900, 660, 15, paint); //Denver
            //New Helena: 386, 218
            //canvas.drawCircle(780, 380, 15, paint); //Helena
            //canvas.drawCircle(440, 780, 15, paint); //Las Vegas
        }

        @Override
        public int getOpacity() {return PixelFormat.OPAQUE;}

        @Override
        public void setAlpha(int alpha) {}

        @Override
        public void setColorFilter(ColorFilter cf) {}
    }
}

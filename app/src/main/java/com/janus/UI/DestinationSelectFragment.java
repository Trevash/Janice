package com.janus.UI;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.janus.Presenter.DestinationFragmentPresenter;
import com.janus.R;

import java.util.ArrayList;
import java.util.List;

public class DestinationSelectFragment extends Fragment implements DestinationFragmentPresenter.View {

    public interface Context {
        void onFinishAction();
    }

    //ToDo: Disable if it's not your turn, connect to server to get destination cards.
    //ToDo: Also identify whether this is your first time drawing destination cards

    private DestinationFragmentPresenter presenter;
    private Context mContext;
    private TextView mPrompt;
    private TextView mDestination1;
    private TextView mDestination2;
    private TextView mDestination3;
    private List<DestinationCardModel> availableDestinationCards = new ArrayList<>();
    private List<DestinationCardModel> selectedDestinationCards = new ArrayList<>();
    private boolean destination1Selected = false;
    private boolean destination2Selected = false;
    private boolean destination3Selected = false;
    private Button mAcceptButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_destination_select, container, false);
        presenter = new DestinationFragmentPresenter(this);
        presenter.setFragment();
        mContext = (Context) getActivity();

        mPrompt = v.findViewById(R.id.destinationSelectPrompt);

        mDestination1 = v.findViewById(R.id.destination1);
        mDestination1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!destination1Selected){
                    destination1Selected = true;
                    selectedDestinationCards.add(availableDestinationCards.get(0));
                    mDestination1.setTextColor(Color.GREEN);
                    updateButton();
                } else {
                    destination1Selected = false;
                    selectedDestinationCards.remove(availableDestinationCards.get(0));
                    mDestination1.setTextColor(Color.BLACK);
                    updateButton();
                }
            }
        });

        mDestination2 = v.findViewById(R.id.destination2);
        mDestination2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!destination2Selected){
                    destination2Selected = true;
                    selectedDestinationCards.add(availableDestinationCards.get(1));
                    mDestination2.setTextColor(Color.GREEN);
                    updateButton();
                } else {
                    destination2Selected = false;
                    selectedDestinationCards.remove(availableDestinationCards.get(1));
                    mDestination2.setTextColor(Color.BLACK);
                    updateButton();
                }
            }
        });

        mDestination3 = v.findViewById(R.id.destination3);
        mDestination3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!destination3Selected){
                    destination3Selected = true;
                    selectedDestinationCards.add(availableDestinationCards.get(2));
                    mDestination3.setTextColor(Color.GREEN);
                    updateButton();
                } else {
                    destination3Selected = false;
                    selectedDestinationCards.remove(availableDestinationCards.get(2));
                    mDestination3.setTextColor(Color.BLACK);
                    updateButton();
                }
            }
        });

        mAcceptButton = v.findViewById(R.id.acceptDestinations);
        mAcceptButton.setEnabled(false);
        mAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.selectDestinationCards(selectedDestinationCards);
                mContext.onFinishAction();
            }
        });

        presenter.updateUI();
        return v;
    }

    @Override
    public void updateDestinationCards(/*destinationCards cards*/) {
        //availableDestinationCards = cards;
        //mPrompt.setText();
        //mDestination1.setText(card.at(0).getCity1() + " - " + card.at(0).getCity2() + ": " + card.at(0).getLength();
        //mDestination2.setText(card.at(1).getCity1() + " - " + card.at(1).getCity2() + ": " + card.at(1).getLength();
        //mDestination3.setText(card.at(2).getCity1() + " - " + card.at(2).getCity2() + ": " + card.at(2).getLength();
    }

    public void updateButton(){
        if(selectedDestinationCards.size() >= 2){
            mAcceptButton.setEnabled(true);
        } else {
            mAcceptButton.setEnabled(false);
        }
    }
}
package com.janus.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.bignerdranch.android.shared.models.trainCardModel;
import com.janus.Presenter.DeckFragmentPresenter;
import com.janus.R;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DeckFragment extends Fragment implements DeckFragmentPresenter.View{

    public interface Context {
        void onFinishAction();
    }

    private DeckFragmentPresenter presenter;
    private Context mContext;
    private List<trainCardModel> deck;
    private List<trainCardModel> faceUpCards;
    private TextView mDeckSizeView;
    private ImageView mFaceDownDeckView;
    private ImageView mCard1View;
    private ImageView mCard2View;
    private ImageView mCard3View;
    private ImageView mCard4View;
    private ImageView mCard5View;
    private Map<cardColorEnum, Integer> colorMap = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_deck, container, false);
        presenter = new DeckFragmentPresenter(this);
        presenter.setFragment();
        mContext = (Context) getActivity();

        mDeckSizeView = v.findViewById(R.id.deckSizeView);
        mFaceDownDeckView = v.findViewById(R.id.faceDownDeckView);
        mFaceDownDeckView.setImageResource(R.drawable.ttr_back);
        mCard1View = v.findViewById(R.id.card1View);
        mCard2View = v.findViewById(R.id.card2View);
        mCard3View = v.findViewById(R.id.card3View);
        mCard4View = v.findViewById(R.id.card4View);
        mCard5View = v.findViewById(R.id.card5View);

        mFaceDownDeckView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.drawCard(0);
                mContext.onFinishAction();
            }
        });

        mCard1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.drawCard(1);
                mContext.onFinishAction();
            }
        });

        mCard2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.drawCard(2);
                mContext.onFinishAction();
            }
        });

        mCard3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.drawCard(3);
                mContext.onFinishAction();
            }
        });

        mCard4View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.drawCard(4);
                mContext.onFinishAction();
            }
        });

        mCard5View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.drawCard(5);
                mContext.onFinishAction();
            }
        });

        colorMap.put(cardColorEnum.BLUE, R.drawable.ttr_blue);
        colorMap.put(cardColorEnum.BLACK, R.drawable.ttr_black);
        colorMap.put(cardColorEnum.ORANGE, R.drawable.ttr_orange);
        colorMap.put(cardColorEnum.GREEN, R.drawable.ttr_green);
        colorMap.put(cardColorEnum.LOCOMOTIVE, R.drawable.ttr_locomotive);
        colorMap.put(cardColorEnum.PURPLE, R.drawable.ttr_purple);
        colorMap.put(cardColorEnum.RED, R.drawable.ttr_red);
        colorMap.put(cardColorEnum.WHITE, R.drawable.ttr_white);
        colorMap.put(cardColorEnum.YELLOW, R.drawable.ttr_yellow);

        presenter.updateUI();
        return v;
    }

    public void updateDeck(List<trainCardModel> updatedDeck){
        deck = updatedDeck;

        mDeckSizeView.setText("Number of Cards in the Deck: " + deck.size());
    }

    public void updateFaceUpCards(List<trainCardModel> updatedTrainCards){

        faceUpCards = updatedTrainCards;
        if(faceUpCards.size() >= 1) { //These checks are for when cards are running out
            mCard1View.setImageResource(colorMap.get(faceUpCards.get(0).getColor()));
        }
        if(faceUpCards.size() >= 2) {
            mCard2View.setImageResource(colorMap.get(faceUpCards.get(1).getColor()));
        }
        if(faceUpCards.size() >= 3) {
            mCard3View.setImageResource(colorMap.get(faceUpCards.get(2).getColor()));
        }
        if(faceUpCards.size() >= 4) {
            mCard4View.setImageResource(colorMap.get(faceUpCards.get(3).getColor()));
        }
        if(faceUpCards.size() >= 5) {
            mCard5View.setImageResource(colorMap.get(faceUpCards.get(4).getColor()));
        }

    }
}

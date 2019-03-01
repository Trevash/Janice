package com.janus.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.janus.Presenter.DeckFragmentPresenter;
import com.janus.R;

import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeckFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeckFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeckFragment extends Fragment implements DeckFragmentPresenter.View{

    private DeckFragmentPresenter presenter;
    private List<trainCard> deck;
    private List<trainCard> faceUpCards;
    private TextView mDeckSizeView;
    private ImageView mFaceDownDeckView;
    private ImageView mCard1View;
    private ImageView mCard2View;
    private ImageView mCard3View;
    private ImageView mCard4View;
    private ImageView mCard5View;
    private Map<cardColorEnum, Integer> colorMap;

    public DeckFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DeckFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeckFragment newInstance(String param1, String param2) {
        DeckFragment fragment = new DeckFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

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

        mDeckSizeView = v.findViewById(R.id.deckSizeView);
        mFaceDownDeckView = v.findViewById(R.id.faceDownDeckView);
        mFaceDownDeckView.setImageResource(R.drawable.ttrBack);
        mCard1View = v.findViewById(R.id.card1View);
        mCard2View = v.findViewById(R.id.card2View);
        mCard3View = v.findViewById(R.id.card3View);
        mCard4View = v.findViewById(R.id.card4View);
        mCard5View = v.findViewById(R.id.card5View);

        mFaceDownDeckView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.drawCard(top of deck);
            }
        });

        mCard1View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.drawCard(cardID);
            }
        });

        mCard2View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.drawCard(cardID);
            }
        });

        mCard3View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.drawCard(cardID);
            }
        });

        mCard4View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.drawCard(cardID);
            }
        });

        mCard5View.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //presenter.drawCard(cardID);
            }
        });

        colorMap.put(cardColorEnum.BLUE, R.drawable.ttrBlue);
        colorMap.put(cardColorEnum.BLACK, R.drawable.ttrBlack);
        colorMap.put(cardColorEnum.ORANGE, R.drawable.ttrOrange);
        colorMap.put(cardColorEnum.GREEN, R.drawable.ttrGreen);
        colorMap.put(cardColorEnum.LOCOMOTIVE, R.drawable.ttrLocomotive);
        colorMap.put(cardColorEnum.PURPLE, R.drawable.ttrPurple);
        colorMap.put(cardColorEnum.RED, R.drawable.ttrRed);
        colorMap.put(cardColorEnum.WHITE, R.drawable.ttrWhite);
        colorMap.put(cardColorEnum.YELLOW, R.drawable.ttrYellow);

        presenter.updateUI();
        return v;
    }

    public void updateDeck(List<trainCard> updatedDeck){
        deck = updatedDeck;

        mDeckSizeView.setText("Number of Cards in the Deck: " + deck.size());
    }

    public void updateFaceUpCards(List<trainCard> updatedTrainCards){
        //Assumes precondition that there are 5 faceup cards

        faceUpCards = updatedTrainCards;
        mCard1View.setImageResource(colorMap.get(faceUpCards.get(0).getColor()));
        mCard2View.setImageResource(colorMap.get(faceUpCards.get(1).getColor()));
        mCard3View.setImageResource(colorMap.get(faceUpCards.get(2).getColor()));
        mCard4View.setImageResource(colorMap.get(faceUpCards.get(3).getColor()));
        mCard5View.setImageResource(colorMap.get(faceUpCards.get(4).getColor()));

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

package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.interfaces.IDestinationCardDeck;
import com.bignerdranch.android.shared.models.DestinationCardModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import static com.bignerdranch.android.shared.Constants.DestinationCards.*;

public class DestinationCardDeck implements IDestinationCardDeck {
    // list of all the cards in the deck
    private static final DestinationCardModel[] FULL_DECK = new DestinationCardModel[] {
            ATLANTA_MONTREAL, ATLANTA_NEW_YORK, ATLANTA_SAN_FRANCISCO, BOSTON_MIAMI,
            CALGARY_PHOENIX, CALGARY_SALT_LAKE, CHICAGO_LOS_ANGELES, CHICAGO_NEW_ORLEANS,
            CHICAGO_SANTA_FE, DALLAS_NEW_YORK, DENVER_EL_PASO, DENVER_PITTSBURGH,
            DULUTH_EL_PASO, DULUTH_HOUSTON, HELENA_LOS_ANGELES, HOUSTON_KANSAS_CITY,
            HOUSTON_WINNIPEG, LITTLE_ROCK_WINNIPEG, LOS_ANGELES_MIAMI, LOS_ANGELES_NEW_YORK,
            LOS_ANGELES_SEATTLE, MIAMI_TORONTO, MONTREAL_NEW_ORLEANS, MONTREAL_VANCOUVER,
            NASHVILLE_PORTLAND, NASHVILLE_ST_MARIE, NEW_YORK_SEATTLE, OKLAHOMA_CITY_ST_MARIE,
            PHOENIX_PORTLAND, SANTA_FE_VANCOUVER
    };

    private Deque<DestinationCardModel> deck;

    public DestinationCardDeck() {
        List<DestinationCardModel> tempDeck = Arrays.asList(FULL_DECK);
        shuffle(tempDeck);
        //deck  // not fastest way to do this, but easy and should work
        deck = new LinkedList<>(tempDeck); // converts to linked list

    }

    private void shuffle(List<DestinationCardModel> tempDeck) {
        // for each card
        for(int i = 0; i < tempDeck.size(); i++) {
            // swap with a random card in the deck: it is okay if a card swaps with itself
            swap(tempDeck, i, (int)(Math.random() * tempDeck.size()));
        }
    }

    private void swap(List<DestinationCardModel> tempDeck, int i, int j) {
        DestinationCardModel temp = tempDeck.get(i);
        tempDeck.set(i, tempDeck.get(j));
        tempDeck.set(j, temp);
    }

    /**
     *
     * @return a list containing the destination cards drawn from the deck
     * @throws IllegalStateException if the deck is empty
     */
    public List<DestinationCardModel> drawDestinationCards() {
        List<DestinationCardModel> drawnCards = new ArrayList<>();
        //ListIterator<DestinationCardModel> it = deck.listIterator();
        if(deck.isEmpty()) {
            throw new IllegalStateException("No cards in destination card deck to draw");
        }
        while(!deck.isEmpty() && drawnCards.size() < 3) { // while card to draw and need to draw
            drawnCards.add(deck.pop());
        }
        return drawnCards;
    }


    public void returnDestinationCards(List<DestinationCardModel> selectedCards, List<DestinationCardModel> rejectedCards) {
        for(int i = 0; i < rejectedCards.size(); i++) {
            returnDestinationCard(rejectedCards.get(i));
        }
    }

    public void returnDestinationCard(DestinationCardModel card) {
        deck.add(card);
    }

    public int size() {
        return deck.size();
    }

}

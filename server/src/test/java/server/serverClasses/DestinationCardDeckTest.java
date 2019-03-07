package server.serverClasses;

import com.bignerdranch.android.shared.models.DestinationCardModel;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class DestinationCardDeckTest {

    @Test
    public void drawDestinationCards() {
        DestinationCardDeck deck = new DestinationCardDeck();
        List<DestinationCardModel> draw1 = deck.drawDestinationCards();
        List<DestinationCardModel> draw2 = deck.drawDestinationCards();
        // both draw 3 cards
        assertEquals(3, draw1.size());
        assertEquals(3, draw2.size());
        // both draw different cards
        for(int i = 0; i < 3; i++) {
            assertNotEquals(draw1.get(i), draw2.get(i));
        }
        // test that the deck starts shuffled
        DestinationCardDeck other = new DestinationCardDeck();
        assertNotEquals(draw1, other.drawDestinationCards());

    }

    @Test
    public void drawDestinationCardsFromLowDeck() {
        DestinationCardDeck deck = new DestinationCardDeck();
        List<DestinationCardModel> draw = deck.drawDestinationCards();
        while(deck.size() != 0) {
            deck.drawDestinationCards();
        }
        try {
            deck.drawDestinationCards(); // should throw here
            fail("drawing destination cards from an empty deck did not fail");
        } catch(IllegalStateException ex) {
            deck.returnDestinationCard(draw.get(1));
        }
        List<DestinationCardModel> redraw = deck.drawDestinationCards();
        assertEquals(1, redraw.size());
        assertEquals(0, deck.size());
        assertEquals(draw.get(1), redraw.get(0));
    }

}
package com.janus.Presenter;

import com.bignerdranch.android.shared.models.colors.playerColorEnum;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.models.usernameModel;
import com.bignerdranch.android.shared.resultobjects.Results;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

public class GameListPresenterTest {
    private GameListFragmentPresenter presenter;
    private GameListFragmentMock mock;

    private class GameListFragmentMock implements GameListFragmentPresenter.View {
        private boolean buttonsActive = false;
        private String gameListError = "";
        private boolean gameListSuccessCalled = false;
        private List<gameModel> games = new ArrayList<>();

        public void updateGameListButtons(boolean isActive) {
            this.buttonsActive = isActive;
        }

        public boolean getButtonsActive() {
            return buttonsActive;
        }

        public void displayGameListError(String message) {
            this.gameListError = message;
        }

        public String getGameListError() {
            return this.gameListError;
        }

        public void updateGameList(List<gameModel> games) {
            this.games = games;
        }

        public void displayGameListSuccess() {
            this.gameListSuccessCalled = true;
        }

        public boolean getGameListSuccessCalled() {
            return gameListSuccessCalled;
        }
    }

    @Before
    public void setup() {
        mock = new GameListFragmentMock();
        presenter = new GameListFragmentPresenter(mock);
    }

    @Test
    public void testSelectGame() {
        playerModel player = new playerModel(new usernameModel("Bob"), true, true, playerColorEnum.BLUE);
        gameModel model = new gameModel("Cool game", player, null);
        assertFalse(mock.getButtonsActive());
        presenter.selectGame(model);
        assertEquals(presenter.getGameSelected().getGameName(), "Cool game");
        assertTrue(mock.getButtonsActive());
    }

    @Test
    public void testOnError() {
        presenter.onError("BAD ERROR");
        assertEquals(mock.getGameListError(), "BAD ERROR");
        assertTrue(mock.getButtonsActive());
    }

    @Test
    public void testOnJoinGameComplete() {
        presenter.onJoinGameComplete(new Results("JoinResult", true, "Object"));
        assertTrue(mock.getGameListSuccessCalled());
    }

    @Test
    public void testOnCreateComplete() {
        presenter.onCreateComplete(new Results("CreateResult", true, "Object"));
        assertTrue(mock.getGameListSuccessCalled());
    }
}

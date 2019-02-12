package com.bignerdranch.android.shared.models;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class gameModelTest {

    @BeforeClass
    public static void addUser() {

    }

    @Test
    public void addPlayer() throws Exception {
        playerModel player1 = new playerModel(new usernameModel("player1"), true,
                true);
        playerModel player2 = new playerModel(new usernameModel("player2"), false,
                false);
    }

    @Test
    public void addDuplicatePlayer() throws Exception {
        playerModel player1 = new playerModel(new usernameModel("player1"), true,
                true);
        playerModel player2 = new playerModel(new usernameModel("player2"), false,
                false);
        playerModel duplicate = new playerModel(new usernameModel("player2"), true,
                false);
        //gameModel gameModel = new gameModel()
    }

    @Test
    public void setGameName() {
    }

    @Test
    public void startGame() {
    }

    @Test
    public void getPlayers() {
    }
}
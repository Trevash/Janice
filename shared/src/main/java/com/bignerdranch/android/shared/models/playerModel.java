package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.playerColorEnum;

import java.util.ArrayList;
import java.util.List;

public class playerModel {
    private usernameModel userName;
    private playerIDModel id;
    private boolean isReady;
    private boolean isHost;


    // train hand
    private List<trainCardModel> trainCardHand = new ArrayList<>();

    // destination hand
    private List<DestinationCardModel> destinationCardHand = new ArrayList<>();

    // claimed routes
    private List<abstractRoute> claimedRoutes = new ArrayList<>();

    // color
    private playerColorEnum playerColor;

    // Locomotives left
    private int locomotives = 45;

    // points
    private int points = 0;

    public playerModel(usernameModel userName, boolean isReady, boolean isHost, playerColorEnum playerColor) {
        this.userName = userName;
        this.id = new playerIDModel();
        this.isReady = isReady;
        this.isHost = isHost;
        this.playerColor = playerColor;
    }
    
    public boolean isHost() {
        return isHost;
    }

    public playerIDModel getId() {
        return id;
    }

    public usernameModel getUserName() {
        return userName;
    }

    public boolean isReady() {
        return isReady;
    }

    public void setIsReady(boolean ready) {isReady = ready;}

    public playerColorEnum getPlayerColor() {
        return playerColor;
    }
    
    public void setPlayerColor(playerColorEnum color) {
    	this.playerColor = color;
    }
    
    public void addTrainCardToHand(trainCardModel card){
    	trainCardHand.add(card);
    }
    
    public List<trainCardModel> getTrainCardHand(){
    	return trainCardHand;
    }
    
    public int[] getStats(){
    	int[] stats = new int[4];
    	stats[0] = this.points;
    	stats[1] = this.locomotives;
    	stats[2] = this.trainCardHand.size();
    	stats[3] = this.claimedRoutes.size();
    	return stats;
    }

    public void DEMO_addDestinationCardToHand(DestinationCardModel card) {
    	destinationCardHand.add(card);
    }
    public void DEMO_removeDestinationCardToHand(int i) {
    	destinationCardHand.remove(i);
    }

    public void addDestinationCards(List<DestinationCardModel> cards) {
        destinationCardHand.addAll(cards);
    }

    public List<DestinationCardModel> getDestinationCardHand() {
        return destinationCardHand;
    }


    public void addToClaimedRoutes(abstractRoute claimedRoute) {
        //TODO: Increment score according to the length of the route
        this.points += 5;
        this.locomotives -= claimedRoute.getLength();
        claimedRoutes.add(claimedRoute);
    }
}


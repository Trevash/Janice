package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.bignerdranch.android.shared.models.colors.playerColorEnum;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;

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
    	stats[3] = this.destinationCardHand.size();
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


    public LinkedList addToClaimedRoutes(abstractRoute claimedRoute) throws Exception {
        switch (claimedRoute.getLength()) {
            case 1:
                this.points += 1;
                break;
            case 2:
                this.points += 2;
                break;
            case 3:
                this.points += 4;
                break;
            case 4:
                this.points += 7;
                break;
            case 5:
                this.points += 10;
                break;
            case 6:
                this.points += 15;
                break;
            default:
                throw new Exception("Invalid length of route!");
        }

        this.locomotives -= claimedRoute.getLength();
        claimedRoutes.add(claimedRoute);

        return this.payCostOfRoute(claimedRoute);
    }

    public LinkedList payCostOfRoute(abstractRoute claimedRoute) {
        //Get claimed route color
        String claimedRouteColor = ((singleRouteModel) claimedRoute).getTrainColor().toString();

        //Make tracker for number of cards to be paid
        int costTracker = claimedRoute.getLength();

        LinkedList discards = new LinkedList();

        //Iterate through list first time for colored cards
        for (int i = 0; i < trainCardHand.size() && costTracker > 0; i++) {
            if(trainCardHand.get(i).getColor().toString().equals(claimedRouteColor)){
                discards.add(trainCardHand.remove(i));
                costTracker--;
            }
        }

        //Iterate through list second time for necessary locomotive cards
        for (int i = 0; i < trainCardHand.size() && costTracker > 0; i++) {
            if(trainCardHand.get(i).getColor() == cardColorEnum.LOCOMOTIVE){
                discards.add(trainCardHand.remove(i));
                costTracker--;
            }
        }

        return discards;
    }
    
    public List<Set<String>> groupCitiesByConnection(){
    	List<Set<String>> routeGroups = new ArrayList<Set<String>>();
    	for(int i = 0;  i< claimedRoutes.size(); i++) {
    		int city1 = -1;
    		int city2 = -1;
    		for(int j = 0; j < routeGroups.size(); j++) {
    			if(routeGroups.get(j).contains(claimedRoutes.get(i).getCity1().getName())){
    				city1 = j;
    			}
    			if(routeGroups.get(j).contains(claimedRoutes.get(i).getCity1().getName())){
    				city2 = j;
    			}
    		}
    		//if neither city has been considered yet
    		if(city1 == -1 && city2 == -1) {
    			Set<String> routeGroup = new HashSet<String>();
    			routeGroup.add(claimedRoutes.get(i).getCity1().getName());
    			routeGroup.add(claimedRoutes.get(i).getCity2().getName());
    			routeGroups.add(new HashSet<String>());
    		}
    		// if both cities have been considered, but in different groups
    		else if(city1 != -1 && city2 != -1 && city1 != city2) {
    			Set<String> routeGroup1 = routeGroups.get(city1);
    			Set<String> routeGroup2 = routeGroups.get(city2);
    			routeGroup1.addAll(routeGroup2);
    			routeGroups.remove(city2);
    		}
    		//if only one city has not be considered, it is added to group that considered it's neighbor
    		else if(city1 == -1){
    			routeGroups.get(city2).add(claimedRoutes.get(i).getCity1().getName());
    		}
    		else if(city2 == -1) {
    			routeGroups.get(city1).add(claimedRoutes.get(i).getCity1().getName());
    		}
    		//no final else statement: if both in same group, don't need to add anything
    	}
    	return routeGroups;
    }
    
    public int calculatePointsFromDestinationCards() {
    	List<Set<String>> routeGroups = groupCitiesByConnection();
    	
    	int overallPoints = 0;
    	for (int i = 0; i < destinationCardHand.size(); i++) {
			DestinationCardModel card = destinationCardHand.get(i);
			String city1Name = card.getCity1().getName();
			String city2Name = card.getCity2().getName();
			boolean isNotFulfilled = true;
    		for (int j = 0; j < routeGroups.size(); j++) {
    			if(routeGroups.get(j).contains(city1Name) && routeGroups.get(j).contains(city2Name)) {
    				isNotFulfilled = false;
    			}		
    		}
    		if(isNotFulfilled) {
    			overallPoints -= card.getPointValue();
    		}
    		else {
    			overallPoints += card.getPointValue();
    		}
    	}
    	return overallPoints;
    }

    public int getPoints() {
        return this.points;
    }

    public void setTrainCardHand(List<trainCardModel> hand) {
        this.trainCardHand = hand;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public class adjListNode{
    	String v;
    	int weight;
    	public adjListNode(String _v, int _w) {
    		v = _v;
    		weight = _w;
    	}
        String getV() { return v; } 
        int getWeight() { return weight; } 
    }
    
    public class routeGraph{
    	int V;
    	Map<String, List<adjListNode>> adj;
    	//List<List<adjListNode>> adj;
    	
    	public routeGraph(Set<String> routeGroup, List<abstractRoute> routes) {
    		
    		V = routeGroup.size();
    		
    		adj = new HashMap<String, List<adjListNode>>();
    		Iterator<String> it = routeGroup.iterator();
    		while (it.hasNext()) {
    			adj.put(it.next(), new ArrayList<adjListNode>());
    		}
    		
    		for (int i = 0; i < routes.size(); i++) {
    			String city1Name = routes.get(i).getCity1().getName();
    			String city2Name = routes.get(i).getCity2().getName();
    			int length = routes.get(i).getLength();
    			if(adj.containsKey(city1Name)) {
    				adj.get(city1Name).add(new adjListNode(city2Name,length));
    				adj.get(city2Name).add(new adjListNode(city1Name,length));
    			}
    		}

    	}
    	
    	public adjListNode bfs(String cityName) {
    		Map<String, Integer> dis = new HashMap<String,Integer>();
    		Iterator<String> it = adj.keySet().iterator();
    		while (it.hasNext()) { dis.put(it.next(), -1); }
    		Queue<String> q = new LinkedList<>();
    		q.add(cityName);
    		dis.put(cityName, 0);
    		while(!q.isEmpty()) {
    			String t = q.remove();
    			
    			for(int i = 0; i< adj.get(t).size(); i++) {
    				adjListNode node = adj.get(t).get(i);
    				if(dis.get(node.getV()) == -1) {
    					q.add(node.getV());
    					dis.put(node.getV(), dis.get(t)+node.getWeight());
    				}
    			}
    		}
    		int maxDist = 0;
    		String maxCity = "";
    		Iterator<String> it2 = dis.keySet().iterator();
    		while(it.hasNext()) {
    			String cityNameTemp = it.next();
    			if(dis.get(cityNameTemp)>maxDist) {
    				maxDist = dis.get(cityNameTemp);
    				maxCity = cityNameTemp;
    			}
    		}

    		return new adjListNode(maxCity, maxDist);
    	}
    	
    	public int getLongestPathLength() {
    		Entry<String, List<adjListNode>> entry = adj.entrySet().iterator().next();
    		String key = entry.getKey();
    		adjListNode t1 = bfs(key);
    		adjListNode t2 = bfs(t1.getV());
    		return t2.getWeight();
    	}
    }
    
    public int calculateLongestRouteOfPlayer() {
    	List<Set<String>> routeGroups = groupCitiesByConnection();
    	int longestRoute = 0;
    	for(int i = 0; i < routeGroups.size(); i++) {
    		routeGraph graph = new routeGraph(routeGroups.get(i),claimedRoutes);
    		int tempLength = graph.getLongestPathLength();
    		if (tempLength > longestRoute){
    			longestRoute = tempLength;
    		}
    	}
    	return longestRoute;
    }
}


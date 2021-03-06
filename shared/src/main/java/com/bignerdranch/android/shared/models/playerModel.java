package com.bignerdranch.android.shared.models;

import com.bignerdranch.android.shared.exceptions.RouteNotFoundException;
import com.bignerdranch.android.shared.models.colors.cardColorEnum;
import com.bignerdranch.android.shared.models.colors.playerColorEnum;
import com.bignerdranch.android.shared.models.colors.routeColorEnum;

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
    private int locomotives = 5;
    // points
    private int points = 0;

    public List<abstractRoute> getClaimedRoutes() {
        return this.claimedRoutes;
    }

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

    public void setIsReady(boolean ready) {
        isReady = ready;
    }

    public playerColorEnum getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(playerColorEnum color) {
        this.playerColor = color;
    }

    public void addTrainCardToHand(trainCardModel card) {
        trainCardHand.add(card);
    }

    public List<trainCardModel> getTrainCardHand() {
        return trainCardHand;
    }

    public int[] getStats() {
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


    public LinkedList<trainCardModel> addToClaimedRoutes(abstractRoute claimedRoute, routeColorEnum color)
            throws RouteNotFoundException {
        LinkedList<trainCardModel> discards = payCostOfRoute(claimedRoute, color);

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
                throw new IllegalArgumentException("Invalid length of route!");
        }

        this.locomotives -= claimedRoute.getLength();
        claimedRoutes.add(claimedRoute);

        return discards;
    }

    private LinkedList<trainCardModel> payCostOfRoute(abstractRoute claimedRoute, routeColorEnum color)
            throws RouteNotFoundException {
        checkIfSufficientCards(claimedRoute, color);

        //Get claimed route color
        String claimedRouteColor = color.name();

        //Make tracker for number of cards to be paid
        int costTracker = claimedRoute.getLength();

        LinkedList<trainCardModel> discards = new LinkedList<>();

        //Iterate through list first time for colored cards
        for (int i = 0; i < trainCardHand.size() && costTracker > 0; i++) {
            if (trainCardHand.get(i).getColor().name().equals(claimedRouteColor)) {
                discards.add(trainCardHand.remove(i));
                costTracker--;
                i--;
            }
        }

        //Iterate through list second time for necessary locomotive cards
        for (int i = 0; i < trainCardHand.size() && costTracker > 0; i++) {
            if (trainCardHand.get(i).getColor().name().equals(cardColorEnum.LOCOMOTIVE.name())) {
                discards.add(trainCardHand.remove(i));
                costTracker--;
                i--;
            }
        }

        return discards;
    }

    private void checkIfSufficientCards(abstractRoute claimedRoute, routeColorEnum color) throws RouteNotFoundException {
        //Get claimed route color
        String claimedRouteColor = color.name();

        //Make tracker for number of cards to be paid
        int costTracker = claimedRoute.getLength();

        LinkedList discards = new LinkedList();

        //Iterate through list first time for colored cards
        for (int i = 0; i < trainCardHand.size() && costTracker > 0; i++) {
            if (trainCardHand.get(i).getColor().name().equals(claimedRouteColor)) {
                costTracker--;
            }
        }

        //Iterate through list second time for necessary locomotive cards
        for (int i = 0; i < trainCardHand.size() && costTracker > 0; i++) {
            if (trainCardHand.get(i).getColor().name().equals(cardColorEnum.LOCOMOTIVE.name())) {
                costTracker--;
            }
        }

        if (costTracker > 0) {
            throw new RouteNotFoundException("Insufficient cards to claim route!");
        }
    }

    public List<Set<cityModel>> groupCitiesByConnection(){
    	List<Set<cityModel>> routeGroups = new ArrayList<>();
    	for(int i = 0;  i< claimedRoutes.size(); i++) {
    		int city1 = -1;
    		int city2 = -1;
    		for(int j = 0; j < routeGroups.size(); j++) {
    			if(routeGroups.get(j).contains(claimedRoutes.get(i).getCity1())){
    				city1 = j;
    			}
    			if(routeGroups.get(j).contains(claimedRoutes.get(i).getCity2())){
    				city2 = j;
    			}
    		}
    		//if neither city has been considered yet
    		if(city1 == -1 && city2 == -1) {
    			Set<cityModel> routeGroup = new HashSet<>();
    			routeGroup.add(claimedRoutes.get(i).getCity1());
    			routeGroup.add(claimedRoutes.get(i).getCity2());
    			routeGroups.add(routeGroup);
    		}
    		// if both cities have been considered, but in different groups
    		else if(city1 != -1 && city2 != -1 && city1 != city2) {
    			Set<cityModel> routeGroup1 = routeGroups.get(city1);
    			Set<cityModel> routeGroup2 = routeGroups.get(city2);
    			routeGroup1.addAll(routeGroup2);
    			routeGroups.remove(city2);
    		}
    		//if only one city has not be considered, it is added to group that considered it's neighbor
    		else if(city1 == -1){
    			routeGroups.get(city2).add(claimedRoutes.get(i).getCity1());
    		}
    		else if(city2 == -1) {
    			routeGroups.get(city1).add(claimedRoutes.get(i).getCity2());
    		}
    		//no final else statement: if both in same group, don't need to add anything
    	}
    	
//    	for(int i = 0; i< routeGroups.size(); i++) {
//    		System.out.println(i);
//    		Iterator<cityModel> it = routeGroups.get(i).iterator();
//    		while (it.hasNext()) {
//    			System.out.println(it.next().getName());
//    		}
//    	}
    	return routeGroups;
    }

    public int calculatePointsFromDestinationCards() {
    	List<Set<cityModel>> routeGroups = groupCitiesByConnection();
    	
    	int overallPoints = 0;
    	for (int i = 0; i < destinationCardHand.size(); i++) {
			DestinationCardModel card = destinationCardHand.get(i);
			cityModel city1Name = card.getCity1();
			cityModel city2Name = card.getCity2();
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

    public void setTrainCars(int locomotives) {
        this.locomotives = locomotives;
    }

    public void setStats(Integer integer) {
        //TODO: actually update final stats
    }

    public class adjListNode{
    	cityModel v;
    	int weight;
    	public adjListNode(cityModel _v, int _w) {
    		v = _v;
    		weight = _w;
    	}
        cityModel getV() { return v; } 
        int getWeight() { return weight; } 
    }
    
    public class routeGraph{
    	int V;
    	Map<cityModel, List<adjListNode>> adj;
    	//List<List<adjListNode>> adj;
    	public routeGraph(Set<cityModel> routeGroup, List<abstractRoute> routes) {
    		V = routeGroup.size();
    		
    		adj = new HashMap<cityModel, List<adjListNode>>();
    		Iterator<cityModel> it = routeGroup.iterator();
    		while (it.hasNext()) {
    			adj.put(it.next(), new ArrayList<adjListNode>());
    		}

    		for (int i = 0; i < routes.size(); i++) {
    			cityModel city1Name = routes.get(i).getCity1();
    			cityModel city2Name = routes.get(i).getCity2();
    			int length = routes.get(i).getLength();
    			if(adj.containsKey(city1Name)) {
    				adj.get(city1Name).add(new adjListNode(city2Name,length));
    				adj.get(city2Name).add(new adjListNode(city1Name,length));
    			}
    		}


    	}
    	
    	public adjListNode bfs(cityModel cityName) {

    		Map<cityModel, Integer> dis = new HashMap<>();
    		Iterator<cityModel> it = adj.keySet().iterator();
    		while (it.hasNext()) { dis.put(it.next(), -1); }
    		Queue<cityModel> q = new LinkedList<>();
    		q.add(cityName);
    		dis.put(cityName, 0);
    		

    		while(!q.isEmpty()) {
    			cityModel t = q.remove();
    			
    			for(int i = 0; i< adj.get(t).size(); i++) {
    				adjListNode node = adj.get(t).get(i);
    				if(dis.get(node.getV()) == -1) {
    					q.add(node.getV());
    					dis.put(node.getV(), dis.get(t)+node.getWeight());
    				}
    			}
    		}

    		int maxDist = 0;
    		cityModel maxCity = null;
    		Iterator<cityModel> it2 = dis.keySet().iterator();
    		while(it2.hasNext()) {
    			cityModel cityNameTemp = it2.next();
    			if(dis.get(cityNameTemp)>maxDist) {
    				maxDist = dis.get(cityNameTemp);
    				maxCity = cityNameTemp;
    			}
    		}

    		return new adjListNode(maxCity, maxDist);
    	}
    	
    	public int getLongestPathLength() {

    		Entry<cityModel, List<adjListNode>> entry = adj.entrySet().iterator().next();
    		cityModel key = entry.getKey();
    		adjListNode t1 = bfs(key);
    		adjListNode t2 = bfs(t1.getV());
    		return t2.getWeight();
    	}
    }

    private boolean determineCardFulfilled(DestinationCardModel card) {
        cityModel city1 = card.getCity1();
        cityModel city2 = card.getCity2();
        for (abstractRoute route : getClaimedRoutes()) {
        }
        return false;
    }
    
    public int calculateLongestRouteOfPlayer() {
    	List<Set<cityModel>> routeGroups = groupCitiesByConnection();
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

    public int calculatePointsLostFromDestinationCards() {
        int totalLost = 0;
        for (DestinationCardModel card : destinationCardHand) {
            boolean cardFulfilled = determineCardFulfilled(card);
            if (!cardFulfilled) {
                totalLost += card.getPointValue();
            }
        }
        return totalLost;
    }
    public int getLocomotives() {
        return this.locomotives;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        playerModel that = (playerModel) o;

        if (!getUserName().equals(that.getUserName())) return false;
        if (!getId().equals(that.getId())) return false;
        return getPlayerColor() == that.getPlayerColor();
    }

    @Override
    public int hashCode() {
        int result = getUserName().hashCode();
        result = 31 * result + getId().hashCode();
        return result;
    }
}


package server.serverClasses;

import com.bignerdranch.android.shared.exceptions.InvalidAuthorizationException;
import com.bignerdranch.android.shared.exceptions.RouteAlreadyClaimedException;
import com.bignerdranch.android.shared.exceptions.RouteNotFoundException;
import com.bignerdranch.android.shared.models.abstractRoute;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.bignerdranch.android.shared.models.colors.playerColorEnum;
import com.bignerdranch.android.shared.models.doubleRouteModelFew;
import com.bignerdranch.android.shared.models.doubleRouteModelMany;
import com.bignerdranch.android.shared.models.gameIDModel;
import com.bignerdranch.android.shared.models.gameModel;
import com.bignerdranch.android.shared.models.playerIDModel;
import com.bignerdranch.android.shared.models.playerModel;
import com.bignerdranch.android.shared.models.singleRouteModel;
import com.bignerdranch.android.shared.models.userIDModel;
import com.bignerdranch.android.shared.models.userModel;
import com.bignerdranch.android.shared.requestObjects.ClaimRouteRequest;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;
import com.bignerdranch.android.shared.requestObjects.StartGameRequest;
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.bignerdranch.android.shared.exceptions.DuplicateException;
import com.bignerdranch.android.shared.exceptions.GameNotFoundException;
import com.bignerdranch.android.shared.exceptions.UserNotFoundException;
import com.bignerdranch.android.shared.resultobjects.ChatboxData;
import com.bignerdranch.android.shared.resultobjects.ClaimRouteData;

import java.util.ArrayList;
import java.util.List;

public class serverModel {
    private static serverModel sm = null;

    private List<userModel> users = new ArrayList<>();

    private List<gameModel> games = new ArrayList<>();

    public static serverModel getInstance() {
        if(sm == null){
            sm = new serverModel();
        }
        return sm;
    }

    public ClaimRouteData claimRoute(ClaimRouteRequest request) throws RouteNotFoundException, RouteAlreadyClaimedException, UserNotFoundException {
        gameModel curGame = this.getGameByID(request.getGameID());
        abstractRoute curRoute = curGame.getRouteById(request.getRouteID());

        if(curRoute instanceof singleRouteModel){
            curRoute.claim(request.getPlayerID());
        }
        else if(curRoute instanceof doubleRouteModelFew){
            curRoute.claim(request.getPlayerID());
        }
        else if(curRoute instanceof doubleRouteModelMany){
            curRoute.claim(request.getPlayerID(), request.getColor());
        }
        else{
            throw new RouteNotFoundException("Route of invalid class type passed to server!");
        }

        return new ClaimRouteData(curGame.getGameID(), curGame.getRoutes(), curRoute, getUser(request.getAuth()).getUserName());
    }

    public void addUser(userModel newUser){
        this.users.add(newUser);
    }

    public boolean userExists(String test) {
        for (userModel user : this.users) {
            if(test.equals(user.getUserName().getValue())){
                return true;
            }
        }
        return false;
    }

    public boolean userExists(userModel test) {
        for (userModel user : this.users) {
            if(test == user){
                return true;
            }
        }
        return false;
    }

    public boolean userExists(userIDModel test) {
        for (userModel user : this.users) {
            if(test == user.getUserID()){
                return true;
            }
        }
        return false;
    }

    public boolean userIDExists(String test) {
        for (userModel user : this.users) {
            if(test.equals(user.getUserID().getValue())){
                return true;
            }
        }
        return false;
    }

    public boolean playerIDExists(String test) {
        for (userModel user : this.users) {
            for (playerIDModel playerID : user.getPlayerIDs()) {
                if (test.equals(playerID.getValue())) {
                    return true;
                }
            }
        }
        return false;
    }

    public userModel getUser(String username) throws UserNotFoundException {
        for(userModel user : this.users) {
            if(user.getUserName().getValue().equals(username)) {
                return user;
            }
        }
        throw new UserNotFoundException("User not found!");
    }

    public userModel getUser(authTokenModel auth) throws UserNotFoundException {
        for(userModel user : this.users) {
            if(user.getAuthToken().getValue().equals(auth.getValue())) {
                return user;
            }
        }
        throw new UserNotFoundException("User not found!");
    }

    public boolean authTokenExists(authTokenModel auth) {
        for (userModel curUser : this.users) {
            if(curUser.getAuthToken().getValue().equals(auth.getValue()))
                return true;
        }
        return false;
    }

    public boolean authTokenExists(String newValue) {
        for (userModel curUser : this.users) {
            if(curUser.getAuthToken().getValue().equals(newValue))
                return true;
        }
        return false;
    }

    public boolean gameIDExists(String newValue) {
        for(gameModel game : this.games){
            if(game.getGameID().getValue().equals(newValue)){
                return true;
            }
        }
        return false;
    }

    public List<gameModel> getGames() {
        return games;
    }

    public gameModel getGameByID(gameIDModel id) {
        for(gameModel game: this.games){
            if(id.equals(game.getGameID())) {
                return game;
            }
        }
        return null;
    }

    public void addGame(gameModel newGame) {
        games.add(newGame);
    }

    public gameModel joinGame(JoinGameRequest request) throws GameNotFoundException,
            InvalidAuthorizationException, DuplicateException {
        for (gameModel curGame : this.games) {
            if(curGame.getGameID().getValue().equals(request.getModel().getGameID().getValue())) {
                curGame.addPlayer(this.makeNewPlayer(this.getUserByAuth(request.getAuth())));
                return curGame;
            }
        }
        throw new GameNotFoundException("Join game failed, game not found");
    }

    private playerModel makeNewPlayer(userModel userByAuth) {
        // player color gets changed based on their position in the game - so actually unneeded
        return new playerModel(userByAuth.getUserName(), false, false, playerColorEnum.BLUE);
    }

    private userModel getUserByAuth(authTokenModel auth) throws InvalidAuthorizationException {
        for (userModel curUser : this.users) {
            if(curUser.getAuthToken().getValue().equals(auth.getValue()))
                return curUser;
        }
        throw new InvalidAuthorizationException("User not found by auth token to find game!");
    }

    public gameModel startGame(StartGameRequest request) throws GameNotFoundException {
        for (gameModel curGame : this.games) {
            //if(curGame.getGameID().getValue().equals(request.getModel().getGameID().getValue())) {
            if(curGame.getGameID().equals(request.getGameID()) ) {
                curGame.startGame();
                return curGame;
            }
        }
        throw new GameNotFoundException("Game not found to start!");
    }
    
    public ChatboxData updateChatbox(UpdateChatboxRequest request) throws GameNotFoundException {
        for (gameModel curGame : this.games) {
            if(curGame.getGameID().getValue().equals(request.getGameID().getValue())) {
                curGame.updateChatbox(request.getMessage());
                return new ChatboxData(curGame.getChatbox(), curGame.getGameID());
                //ChatboxData data = new ChatboxData(curGame.getChatbox(), curGame.getGameID());
                //return data;
            }
        }
        throw new GameNotFoundException("Update chat failed, game not found");
    }


}

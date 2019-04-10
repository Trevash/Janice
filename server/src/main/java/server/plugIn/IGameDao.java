package server.plugIn;

import java.util.List;

public interface IGameDao {

    // TODO methods are tentative - decide how to create these methods
    void addGame(String gameID, String serializedGame);
    //String retrieveGame(String gameID); // retrieves the game in serialized form
    List<String> retrieveGames();
    //List<List<String>> retrieveDeltas()
    List<String> retrieveDeltas(String gameID); // returns genericCommands in serialized form
    void addDelta(String gameID, String serializedCommand);
    void ClearDB();
    // SOMETHING connectToDB()

}

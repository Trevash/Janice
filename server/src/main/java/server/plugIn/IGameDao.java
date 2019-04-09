package server.plugIn;

public interface IGameDao {

    // TODO methods are tentative - decide how to create these methods
    void addGame();
    String retrieveGame(); // retrieves the game in serialized form
    String[] retrieveDeltas(); // returns genericCommands in serialized form
    void addDelta(String serializedCommand);
    //void ClearDB();
    // SOMETHING connectToDB()


}

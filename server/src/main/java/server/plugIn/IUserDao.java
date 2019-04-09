package server.plugIn;

import java.util.List;

public interface IUserDao {

    void addUser(String serializedUser);
    void addAuthToken(String userID, String serializedAuthToken);
    List<String> getAuthTokens(String userID);
    // SOMETHING connectToDB();
}

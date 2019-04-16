package server.plugIn;

import java.util.List;

public interface IUserDao {

    void addUser(String userID, String serializedUser);
    List<String> getUsers();
    void clearDB();
    // SOMETHING connectToDB();
}

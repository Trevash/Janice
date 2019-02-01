package server.models;

public class userIDModel {
    String value = null;

    public userIDModel(String newValue) throws Exception {
        if(serverModel.getInstance().userIDExists(newValue)){
            throw new Exception("UserID exists!");
        }

        value = newValue;
    }

    public String getValue() {
        return value;
    }
}

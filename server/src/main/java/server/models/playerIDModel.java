package server.models;

public class playerIDModel {
    String value = null;

    playerIDModel(String newValue) throws Exception {
        if(serverModel.getInstance().playerIDExists(newValue)){
            throw new Exception("PlayerID exists!");
        }

        value = newValue;
    }

    public String getValue() {
        return value;
    }
}

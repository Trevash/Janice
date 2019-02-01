package server.models;

public class usernameModel {
    private String value = null;

    public usernameModel(String newValue) throws Exception {
        if(serverModel.getInstance().usernameExists(newValue)){
            throw new Exception("The username is already taken!");
        }
        else if(newValue == null || newValue.length() == 0) {
            throw new Exception("No username recieved!");
        }
        else if(!newValue.matches("[a-zA-Z0-9]+")){
            throw new Exception("Username used spaces or illegal characters!");
        }

        this.value = newValue;
    }

    public String getValue() {
        return value;
    }
}

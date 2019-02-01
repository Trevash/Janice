package clientClasses;

import classes.Command;
import classes.Serializer;

public class ServerProxy {
    private static ServerProxy scp;

    static ServerProxy getInstance() {
        if (scp == null){
            scp = new ServerProxy();
        }
        return scp;
    }

    private String className = "server.handlers";

    public String Login(String username, String password) throws Exception {
        String[] paramValues = {username, password};
        String[] paramTypes = {"java.lang.String", "java.lang.String"};
        Command commandObj = new Command("server.handlers.loginHandler", "login",paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        return ClientCommunicator.getInstance().sendResponse("command", commandObjStr);
    }

    public String Register(String username, String password) throws Exception {
        String[] paramValues = {username, password};
        String[] paramTypes = {"java.lang.String", "java.lang.String"};
        Command commandObj = new Command("server.handlers.registerHandler", "register", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        return ClientCommunicator.getInstance().sendResponse("command", commandObjStr);
    }
}

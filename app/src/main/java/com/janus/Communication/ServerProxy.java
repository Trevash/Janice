package com.janus.Communication;

import com.bignerdranch.android.shared.Command;
import com.bignerdranch.android.shared.Results;
import com.bignerdranch.android.shared.Serializer;

public class ServerProxy {
    private static ServerProxy scp;

    static ServerProxy getInstance() {
        if (scp == null){
            scp = new ServerProxy();
        }
        return scp;
    }

    private String className = "server.handlers";

    public Results Login(String username, String password) throws Exception {
        String[] paramValues = {username, password};
        String[] paramTypes = {"java.lang.String", "java.lang.String"};
        Command commandObj = new Command("server.handlers.loginHandler", "login",paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        return Serializer.getInstance().deserializeResults(ClientCommunicator.getInstance().sendResponse("command", commandObjStr));
    }

    public Results Register(String username, String password) throws Exception {
        String[] paramValues = {username, password};
        String[] paramTypes = {"java.lang.String", "java.lang.String"};
        Command commandObj = new Command("server.handlers.registerHandler", "register", paramTypes, paramValues);
        String commandObjStr = Serializer.getInstance().serializeObject(commandObj);
        return Serializer.getInstance().deserializeResults(ClientCommunicator.getInstance().sendResponse("command", commandObjStr));
    }
}

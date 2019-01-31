package server.handlers;

import classes.Command;
import classes.Results;
import classes.Serializer;

public class commandHandler extends handlerBase {
    @Override
    public String execute(String s) {
        Command command = Serializer.getInstance().deserializeObject(s);
        Results results = command.execute();
        String ss = Serializer.getInstance().serializeResults(results);
        System.out.println(ss);

        if(results.success){ return (String) results.data; }
        else{ return results.errorInfo; }
    }
}

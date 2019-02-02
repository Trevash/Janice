package server.handlers;

import com.bignerdranch.android.shared.Command;
import com.bignerdranch.android.shared.Results;
import com.bignerdranch.android.shared.Serializer;

public class commandHandler extends handlerBase {
    @Override
    public String execute(String s) {
        Command command = Serializer.getInstance().deserializeObject(s);
        Results results = command.execute();
        String ss = Serializer.getInstance().serializeResults(results);

        if(results.success){ return (String) results.data; }
        else{ return results.errorInfo; }
    }
}

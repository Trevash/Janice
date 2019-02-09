package server.handlers;

import com.bignerdranch.android.shared.GenericCommand;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.Serializer;

public class commandHandler extends handlerBase {
    @Override
    public String execute(String s) {
        GenericCommand command = Serializer.getInstance().deserializeCommand(s);
        Results results = (Results) command.execute();
        String ss = Serializer.getInstance().serializeObject(results);

        if(results.isSuccess()){ return (String) results.getData(); }
        else{ return (String) results.getData(); }
    }
}

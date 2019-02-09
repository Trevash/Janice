package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.LoginRequest;

public class CreateGameTask extends AsyncTask<Void, Void, Results> {
    public interface Caller {
        void onError(String s);
        void onCreateComplete(Results r);
    }

    private CreateGameTask.Caller caller;

    public CreateGameTask(CreateGameTask.Caller c) {
        caller = c;
    }

    @Override
    protected Results doInBackground(){
        ServerProxy proxy = ServerProxy.getInstance();
        try {
            Results res;
            proxy.connectClient();
            res = proxy.CreateGame();
            return res;
        } catch(Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());
            return new Results("Create Game", false, e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Results r) {
        if(r.isSuccess()) {
            caller.onCreateGameComplete(r);
        } else {
            caller.onError((String) r.getData());
        }
    }
}

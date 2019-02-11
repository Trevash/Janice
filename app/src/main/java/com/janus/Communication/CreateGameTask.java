package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.LoginRequest;
import com.bignerdranch.android.shared.models.authTokenModel;

public class CreateGameTask extends AsyncTask<authTokenModel, Void, Results> {
    public interface Caller {
        void onError(String s);
        void onCreateComplete(Results r);
    }

    private CreateGameTask.Caller caller;

    public CreateGameTask(CreateGameTask.Caller c) {
        caller = c;
    }

    @Override
    protected Results doInBackground(authTokenModel... authTokenModels){
        ServerProxy proxy = ServerProxy.getInstance();
        try {
            Results res;
            res = proxy.CreateGame(authTokenModels[0]);
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
            caller.onCreateComplete(r);
        } else {
            caller.onError((String) r.getData());
        }
    }
}

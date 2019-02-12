package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.requestObjects.JoinGameRequest;

public class JoinGameTask extends AsyncTask<JoinGameRequest, Void, Results> {

    public interface Caller {
        void onError(String s);
        void onJoinGameComplete(Results r);
    }

    private com.janus.Communication.JoinGameTask.Caller caller;

    public JoinGameTask(com.janus.Communication.JoinGameTask.Caller c) {
        caller = c;
    }

    @Override
    protected Results doInBackground(JoinGameRequest... request) {
        ServerProxy proxy = ServerProxy.getInstance();
        try {
            return proxy.JoinGame(request[0]);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
            return new Results("JoinGame", false, e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Results r) {
        if (r.isSuccess()) {
            caller.onJoinGameComplete(r);
        } else {
            caller.onError((String) r.getData(String.class));
        }
    }
}

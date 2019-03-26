package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;
import com.bignerdranch.android.shared.resultobjects.Results;

public class ReturnDestinationCardsTask extends AsyncTask<ReturnDestinationCardsRequest, Void, Results> {
    public interface Caller {
        void onError(String s);
    }

    private com.janus.Communication.ReturnDestinationCardsTask.Caller caller;

    public ReturnDestinationCardsTask(com.janus.Communication.ReturnDestinationCardsTask.Caller c) {
        caller = c;
    }

    @Override
    protected Results doInBackground(ReturnDestinationCardsRequest... requests) {
        ServerProxy proxy = ServerProxy.getInstance();
        try {
            Results res;
            res = proxy.returnDestinationCard(requests[0]);
            return res;
        } catch(Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());
            return new Results("Return Destination Cards", false, e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Results r) {
        //Do nothing
    }
}

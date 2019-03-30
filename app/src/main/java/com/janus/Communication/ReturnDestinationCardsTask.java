package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.models.DestinationCardModel;
import com.bignerdranch.android.shared.requestObjects.ReturnDestinationCardsRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.resultobjects.ReturnDestinationCardData;
import com.janus.ClientModel;

import java.util.List;

public class ReturnDestinationCardsTask extends AsyncTask<List<DestinationCardModel>, Void, Results> {
    //extends AsyncTask<ReturnDestinationCardsRequest, Void, Results> {
    public interface Caller {
        void onError(String s);
    }

    private com.janus.Communication.ReturnDestinationCardsTask.Caller caller;

    public ReturnDestinationCardsTask(com.janus.Communication.ReturnDestinationCardsTask.Caller c) {
        caller = c;
    }

    @Override
    //protected Results doInBackground(ReturnDestinationCardsRequest... requests) {
    protected Results doInBackground(List<DestinationCardModel>... lists) {
        final int SELECTED = 0;
        final int REJECTED = 1;
        //ServerProxy proxy = ServerProxy.getInstance();
        try {
            // Rerouted so that the client states are used: otherwise, they won't know to update themselves
            ClientModel.getInstance().getGame().returnRejectedDestinationCards(lists[SELECTED], lists[REJECTED]);
            //Results res;
            //res = proxy.returnDestinationCard(requests[0]);
            //return res;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
            return new Results("Return Destination Cards", false, e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Results r) {
        //Do nothing
    }
}

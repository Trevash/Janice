package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.janus.ClientFacade;
import com.janus.ClientModel;

public class DrawTrainCardTask extends AsyncTask<DrawTrainCardRequest, Void, Results> {
    public interface Caller {
        void onError(String s);
    }

    private com.janus.Communication.DrawTrainCardTask.Caller caller;
    private ClientFacade facade = ClientFacade.getInstance();

    public DrawTrainCardTask(com.janus.Communication.DrawTrainCardTask.Caller c) {
        caller = c;
    }

        @Override
        protected Results doInBackground(DrawTrainCardRequest... requests) {
            ServerProxy proxy = ServerProxy.getInstance();
            try {
                Results res = null;
                if(facade.userCanDrawLocomotive()) {
                    res = proxy.drawFirstTrainCard(requests[0]);
                } else {
                    res = proxy.drawSecondTrainCard(requests[0]);
                }
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

package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.requestObjects.DrawTrainCardRequest;
import com.bignerdranch.android.shared.resultobjects.Results;

public class DrawTrainCardTask extends AsyncTask<DrawTrainCardRequest, Void, Results> {
    public interface Caller {
        void onError(String s);
    }

    private com.janus.Communication.DrawTrainCardTask.Caller caller;

    public DrawTrainCardTask(com.janus.Communication.DrawTrainCardTask.Caller c) {
        caller = c;
    }

        @Override
        protected Results doInBackground(DrawTrainCardRequest... requests) {
            ServerProxy proxy = ServerProxy.getInstance();
            try {
                Results res = null;
                res = proxy.drawFirstTrainCard(requests[0]);
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

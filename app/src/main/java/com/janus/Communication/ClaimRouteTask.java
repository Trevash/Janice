package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.requestObjects.ClaimRouteRequest;
import com.bignerdranch.android.shared.resultobjects.Results;

public class ClaimRouteTask extends AsyncTask<ClaimRouteRequest, Void, Results> {

    public interface Caller {
        void onError(String s);
        void onClaimRouteComplete(Results r);
    }

    private com.janus.Communication.ClaimRouteTask.Caller caller;

    public ClaimRouteTask(com.janus.Communication.ClaimRouteTask.Caller c) {
        caller = c;
    }

    @Override
    protected Results doInBackground(ClaimRouteRequest... request) {
        ServerProxy proxy = ServerProxy.getInstance();
        try {
            return proxy.claimRoute(request[0]);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.print(e.getMessage());
            return new Results("ClaimRoute", false, e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Results r) {
        if (r.isSuccess()) {
            caller.onClaimRouteComplete(r);
        } else {
            caller.onError((String) r.getData(String.class));
        }
    }
}


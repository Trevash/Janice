package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.resultobjects.Results;

import com.janus.LoginRequest;


public class LoginTask extends AsyncTask<LoginRequest, Void, Results> {

    public interface Caller {
        void onError(String s);
        void onLoginComplete(Results r);
    }

    private Caller caller;
    private String requestType;

    public LoginTask(Caller c, String rt) {
        caller = c;
        requestType = rt;
    }

    @Override
    protected Results doInBackground(LoginRequest... r){
        ServerProxy proxy = ServerProxy.getInstance();
        try {
            Results res;
            if(requestType.equals("Login")) {
                proxy.connectClient();
                res = proxy.Login(r[0].getUsername(), r[0].getPassword());
            } else {
                proxy.connectClient();
                res = proxy.Register(r[0].getUsername(), r[0].getPassword());
            }

            if (!res.isSuccess()) {
                proxy.disconnectClient();
            }

            return res;
        } catch(Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());
            return new Results("Login", false, e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Results r) {
        if(r.isSuccess()) {
            caller.onLoginComplete(r);
        } else {
            caller.onError((String) r.getData());
        }
    }
}

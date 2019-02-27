package com.janus.Communication;

import android.os.AsyncTask;

import com.bignerdranch.android.shared.requestObjects.CreateGameRequest;
import com.bignerdranch.android.shared.requestObjects.UpdateChatboxRequest;
import com.bignerdranch.android.shared.resultobjects.Results;
import com.bignerdranch.android.shared.models.authTokenModel;
import com.janus.Presenter.ChatFragmentPresenter;

public class UpdateChatTask extends AsyncTask<UpdateChatboxRequest, Void, Results> {
    public interface Caller {
        void onError(String s);
        void onCreateComplete(Results r);
    }

    private com.janus.Communication.UpdateChatTask.Caller caller;

    public UpdateChatTask(com.janus.Communication.UpdateChatTask.Caller c) {
        caller = c;
    }

    @Override
    protected Results doInBackground(UpdateChatboxRequest... updateChatboxRequests) {
        ServerProxy proxy = ServerProxy.getInstance();
        try {
            Results res;
            res = proxy.updateChatbox(updateChatboxRequests[0]);
            return res;
        } catch(Exception e){
            e.printStackTrace();
            System.out.print(e.getMessage());
            return new Results("Update Chatbox", false, e.getMessage());
        }
    }

    @Override
    protected void onPostExecute(Results r) {
        //TODO: what to do on here?
        //Result holds the new chatbox data, holding the array of messages with strings and usernames.
    }
}
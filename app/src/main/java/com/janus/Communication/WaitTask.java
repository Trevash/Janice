package com.janus.Communication;

import android.os.AsyncTask;

public class WaitTask extends AsyncTask<Integer, Void, Integer> {

    public interface Caller {
        void onFinishWait(Integer demoState);
    }

    private Caller caller;

    public WaitTask(Caller c) {
        this.caller = c;
    }

    @Override
    protected Integer doInBackground(Integer... demoState) {
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }

        return demoState[0] + 1;
    }

    @Override
    protected void onPostExecute(Integer demoState) {
        caller.onFinishWait(demoState);
    }
}

package com.janus.Presenter;

import android.content.res.Resources;
import android.util.Log;
import android.view.View;

import com.bignerdranch.android.shared.Results;
import com.janus.Communication.LoginTask;
import com.janus.LoginRequest;
import com.janus.R;

public class LoginFragmentPresenter implements LoginTask.Caller{

    public interface View {
        void updateButtons(boolean isActive);
        void displayError(String message);
        void displaySuccess();
    }

    private String username = "";
    private String password = "";
    private View view;

    public LoginFragmentPresenter(View view) {
        this.view = view;
    }

    public void updateUsername(String username) {
        this.username = username;
        checkButtons();
    }

    public void updatePassword(String password) {
        this.password = password;
        checkButtons();
    }

    public void loginClicked() {
        view.updateButtons(false);
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginTask loginTask = new LoginTask(this, "Login");

        loginTask.execute(loginRequest);
    }

    public void registerClicked() {
        view.updateButtons(false);
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginTask loginTask = new LoginTask(this, "Register");

        loginTask.execute(loginRequest);
    }

    private void checkButtons() {
        if(username.equals("") || password.equals("")){
            view.updateButtons(false);
        }
        else {
            view.updateButtons(true);
        }
    }

    @Override
    public void onError(String s) {
        view.displayError(s);
        view.updateButtons(true);
    }

    @Override
    public void onLoginComplete(Results r) {
        view.displaySuccess();
    }
}

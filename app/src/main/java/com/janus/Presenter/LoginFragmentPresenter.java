package com.janus.Presenter;

import com.janus.ClientFacade;
import com.janus.Communication.LoginTask;
import com.bignerdranch.android.shared.requestObjects.LoginRequest;

public class LoginFragmentPresenter implements LoginTask.Caller, ClientFacade.Presenter {

    public interface View {
        void updateLoginButtons(boolean isActive);
        void displayLoginError(String message);
        void displayLoginSuccess();
    }

    private ClientFacade facade = ClientFacade.getInstance();
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
        view.updateLoginButtons(false);
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginTask loginTask = new LoginTask(this, "Login");

        loginTask.execute(loginRequest);
    }

    public void registerClicked() {
        view.updateLoginButtons(false);
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginTask loginTask = new LoginTask(this, "Register");

        loginTask.execute(loginRequest);
    }

    public void setFragment() {
        facade.setPresenter(this);
    }

    private void checkButtons() {
        if(username.equals("") || password.equals("")){
            view.updateLoginButtons(false);
        }
        else {
            view.updateLoginButtons(true);
        }
    }

    @Override
    public void onError(String s) {
        view.displayLoginError(s);
        view.updateLoginButtons(true);
    }

    @Override
    public void onLoginComplete() {
        view.displayLoginSuccess();
    }

    @Override
    public void updateUI() {}
}

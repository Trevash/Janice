package com.janus.Presenter;

import com.janus.ClientFacade;
import com.janus.Communication.LoginTask;
import com.bignerdranch.android.shared.requestObjects.LoginRequest;

/** LoginFragmentPresenter handles the communication between the LoginFragment
 * and the
 */
public class LoginFragmentPresenter implements LoginTask.Caller, ClientFacade.Presenter {

    /** View interface allows LoginFragmentPresenter to call
     * the following methods on its view
     */
    public interface View {
        void updateLoginButtons(boolean isActive);
        void displayLoginError(String message);
        void displayLoginSuccess();
    }

    /** Reference to the clientFacade class that updates the UI
     */
    private ClientFacade facade = ClientFacade.getInstance();
    private String username = "";
    private String password = "";
    /** References the presenter's view (the LoginFragment)
     */
    private View view;

    /**
     * Sets the view variable
     *
     * @param  view The view that this presenter is associated with (in this case the LoginFragment)
     * @pre given parameter (view) is the LoginFragment
     * @post LoginFragmentPresenter's view object is assigned to LoginFragment
     */
    public LoginFragmentPresenter(View view) {
        this.view = view;
    }

    /**
     * Sets the username in LoginFragmentPresenter
     * Also calls checkButtons(), another method in LoginFragmentPresenter
     * that updates whether or not the buttons are enabled on the LoginFragment.
     *
     * @param  username  String to be set as the username in LoginFragmentPresenter
     * @pre A string that is not null is given
     * @post this.username is assigned to username, checkButtons has been executed
     */
    public void updateUsername(String username) {
        this.username = username;
        checkButtons();
    }

    /**
     * Sets the password in LoginFragmentPresenter
     * Also calls checkButtons()
     *
     * @param  password  String to be set as the password in LoginFragmentPresenter
     * @pre A string that is not null is given
     * @post this.password is assigned to password, checkButtons has been executed
     */
    public void updatePassword(String password) {
        this.password = password;
        checkButtons();
    }

    /**
     * Deactivates the view's login and register buttons, produces a login request
     * using the user's username and password, starts a LoginTask, and then executes
     * the LoginTask using the login request.
     * @pre A username and password have been provided
     * @post Login/register buttons are disabled, and a login request has been
     *      * sent to the LoginTask.  The user is logged in or an error is returned.
     */
    public void loginClicked() {
        view.updateLoginButtons(false);
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginTask loginTask = new LoginTask(this, "Login");

        loginTask.execute(loginRequest);
    }

    /**
     * Similar functionality to loginClicked() but passes information to it
     * indicating that it should execute "register" rather than "login"
     * @pre A username and password have been provided
     * @post Login/register buttons are disabled, and a register request has been
     * sent to the LoginTask.  The user is registered or an error is returned.
     */
    public void registerClicked() {
        view.updateLoginButtons(false);
        LoginRequest loginRequest = new LoginRequest(username, password);
        LoginTask loginTask = new LoginTask(this, "Register");

        loginTask.execute(loginRequest);
    }

    /**
     * Sets the ClientFacade's current presenter to this presenter
     * so that it can receive calls from it.
     * @pre facade has been instantiated
     * @post The client facade's presenter reference is set to this presenter
     */
    public void setFragment() {
        facade.setPresenter(this);
    }

    /**
     * Disables the view buttons if the user has not entered a username or password.
     * Otherwise, it enables the view buttons.
     * @pre none
     * @post The login and register buttons are disabled if the user hasn't entered both.  Otherwise, they are enabled.
     */
    private void checkButtons() {
        if(username.equals("") || password.equals("")){
            view.updateLoginButtons(false);
        }
        else {
            view.updateLoginButtons(true);
        }
    }

    /**
     * Causes the view to display the login error, then enable the login and register buttons again
     *
     * @param  s Error message received from LoginTask
     * @pre An error message s is provided
     * @post An error message is passed to the view and the view's buttons are enabled
     */
    @Override
    public void onError(String s) {
        view.displayLoginError(s);
        view.updateLoginButtons(true);
    }

    /**
     * Causes the view to display a success message when LoginTask is successful
     * @pre Successful login
     * @post Login success message is displayed in the view
     */
    @Override
    public void onLoginComplete() {
        view.displayLoginSuccess();
    }

    /**
     * Empty required method due to this class implementing the Presenter class
     * in Client Facade.  This class does not update the UI due to updates to
     * the Client Model, but other presenters do.
     * @pre none
     * @post none
     */
    @Override
    public void updateUI() {}

    @Override
    public void endGame(){}

    public void lastRound(){}
}

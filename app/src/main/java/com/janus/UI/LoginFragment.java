package com.janus.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.janus.R;

public class LoginFragment extends Fragment {
    private MainActivity mMainActivity;

    private EditText mUserName;
    private String mUserNameString = "";

    private EditText mPassword;
    private String mPasswordString = "";

    private Button mLoginButton;
    private Button mRegisterButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        mMainActivity = (MainActivity) getActivity();

        mUserName = (EditText) v.findViewById(R.id.username_editText);
        mUserName.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                mUserNameString = s.toString();
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s){}
        });

        mPassword = (EditText) v.findViewById(R.id.password_editText);
        mPassword.addTextChangedListener(new TextWatcher(){
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){
                mPasswordString = s.toString();
                updateButtons();
            }

            @Override
            public void afterTextChanged(Editable s){}
        });

        mLoginButton = (Button) v.findViewById(R.id.login_button);
        mLoginButton.setEnabled(false);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loginButtonClicked();
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_button);
        mRegisterButton.setEnabled(false);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //egisterButtonClicked();
            }
        });

        return v;
    }

    /*private void loginButtonClicked() {
        if(!correctServerParams()){
            onError("Login Error");
        } else {
            LoginRequest lr = new LoginRequest();
            lr.setPassword(mPasswordString);
            lr.setUserName(mUserNameString);

            LoginTask task = new LoginTask(this, mServerHostString, mServerPortString);

            task.execute(lr);
        }
    }

    private void registerButtonClicked() {
        if(!correctServerParams()){
            onError("Register Error");
        } else {
            RegisterRequest rr = new RegisterRequest();
            rr.setPassword(mPasswordString);
            rr.setUserName(mUserNameString);
            rr.setFirstName(mFirstNameString);
            rr.setLastName(mLastNameString);
            rr.setEmail(mEmailString);
            if (mMaleButtonClicked) {
                rr.setGender("m");
            } else {
                rr.setGender("f");
            }

            RegisterTask task = new RegisterTask(this, mServerHostString, mServerPortString);

            task.execute(rr);
        }
    }

    @Override
    public void onLoginComplete(LoginResult r) {

        String personID = r.getPersonID();
        mModel.setUserPersonID(personID);

        String authToken = r.getAuthToken();
        DataSyncTask task = new DataSyncTask(this, mServerHostString, mServerPortString);
        task.execute(authToken);

        mMainActivity.onLogIn();
    }

    @Override
    public void onSyncComplete(SyncData sd) {

        mModel.setAncestors(sd.getPeople());
        mModel.setAncestorEvents(sd.getEvents());
        mModel.initialize();
        mFilter.initializeEventTypeFilters();

        //mMainActivity.onSyncComplete();
    }

    @Override
    public void onError(String s){
        int messageResId = 0;
        if(s.equals("Login Error")){
            messageResId = R.string.login_fail_toast;
        } else {
            messageResId = R.string.register_fail_toast;
        }
        Toast.makeText(getActivity(), messageResId, Toast.LENGTH_LONG).show();
    }*/

    private void updateButtons(){
        mLoginButton.setEnabled(true);
        mRegisterButton.setEnabled(true);

        if(mUserNameString.equals("") || mPasswordString.equals("")){
            mLoginButton.setEnabled(false);
            mRegisterButton.setEnabled(false);
            return;
        }
    }
}
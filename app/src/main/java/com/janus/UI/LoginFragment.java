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
import android.widget.Toast;

import com.janus.Presenter.LoginFragmentPresenter;
import com.janus.R;

public class LoginFragment extends Fragment implements LoginFragmentPresenter.View {

    public interface Context {
        void onSignIn();
    }

    private Context mContext;

    private LoginFragmentPresenter presenter;

    private EditText mUsername;
    private EditText mPassword;

    private Button mLoginButton;
    private Button mRegisterButton;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);

        presenter = new LoginFragmentPresenter(this);
        presenter.setFragment();

        mUsername = (EditText) v.findViewById(R.id.username_editText);
        mUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updateUsername(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mPassword = (EditText) v.findViewById(R.id.password_editText);
        mPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                presenter.updatePassword(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        mLoginButton = (Button) v.findViewById(R.id.login_button);
        mLoginButton.setEnabled(false);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.loginClicked();
            }
        });

        mRegisterButton = (Button) v.findViewById(R.id.register_button);
        mRegisterButton.setEnabled(false);
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.registerClicked();
            }
        });

        return v;
    }

    @Override
    public void updateLoginButtons(boolean isActive){
        mLoginButton.setEnabled(isActive);
        mRegisterButton.setEnabled(isActive);
    }

    @Override
    public void displayLoginError(String message) {
        Toast.makeText(getActivity(), "Failed connecting " + message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayLoginSuccess() {
        Toast.makeText(getActivity(), R.string.sign_in_welcome, Toast.LENGTH_LONG).show();
        mContext = (Context) getActivity();
        mContext.onSignIn();
    }
}
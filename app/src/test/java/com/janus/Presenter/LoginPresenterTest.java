package com.janus.Presenter;

import com.janus.UI.LoginFragment;

import org.junit.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LoginPresenterTest {
    private LoginFragmentPresenter presenter;
    private LoginFragmentMock mock;

    private class LoginFragmentMock implements LoginFragmentPresenter.View {
        private boolean buttonsActive = false;
        private String displayedError = "";
        private boolean displayLoginSuccessCalled = false;


        public void updateLoginButtons(boolean isActive) {
            this.buttonsActive = isActive;
        }

        public boolean getButtonsActive() {
            return buttonsActive;
        }

        public void displayLoginError(String message) {
            this.displayedError = message;
        }

        public String getDisplayedError() {
            return displayedError;
        }

        public void displayLoginSuccess() {
            displayLoginSuccessCalled = true;
        }

        public boolean getDisplaySuccessCalled() {
            return this.displayLoginSuccessCalled;
        }
    }

    @Before
    public void setup() {
        mock = new LoginFragmentMock();
        presenter = new LoginFragmentPresenter(mock);
    }

    @Test
    public void testUpdateUsername() {
        presenter.updateUsername("Fred");
        assertEquals(presenter.getUsername(), "Fred");
        assertFalse(mock.getButtonsActive());
    }

    @Test
    public void testUpdatePassword() {
        presenter.updatePassword("SuperSecretPassword");
        assertEquals(presenter.getPassword(), "SuperSecretPassword");
        assertFalse(mock.getButtonsActive());
    }

    @Test
    public void testButtonsActivate() {
        presenter.updateUsername("Fred");
        presenter.updatePassword("SuperSecretPassword");
        assertTrue(mock.getButtonsActive());
    }

    @Test
    public void testOnError() {
        presenter.onError("BAD ERROR");
        assertEquals(mock.getDisplayedError(), "BAD ERROR");
        assertTrue(mock.getButtonsActive());
    }

    @Test
    public void testOnLoginComplete() {
        presenter.onLoginComplete();
        assertTrue(mock.getDisplaySuccessCalled());
    }
}

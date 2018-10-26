package com.jacksw.espresso_login_demo;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

@RunWith(AndroidJUnit4.class)
public class LoginMockServerTest {

    private MockWebServer server;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();

        LoginAPIEndpoint.BASE_URL = server.url("/").toString();
    }

    @Test
    public void loginSuccess() {
        server.enqueue(new MockResponse().setResponseCode(200));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        LoginScreen loginScreen = new LoginScreen();
        loginScreen.inputUsername("username");
        loginScreen.inputPassword("password");
        loginScreen.clickLoginButton();

        WelcomeScreen welcomeScreen = new WelcomeScreen();
        welcomeScreen.showExpectedText("Welcome!");
    }

    @Test
    public void loginFail() {
        server.enqueue(new MockResponse().setResponseCode(404));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        LoginScreen loginScreen = new LoginScreen();
        loginScreen.inputUsername("username");
        loginScreen.inputPassword("password");
        loginScreen.clickLoginButton();
        loginScreen.showExpectedDialogText("Username or password incorrect please try again");
    }


}

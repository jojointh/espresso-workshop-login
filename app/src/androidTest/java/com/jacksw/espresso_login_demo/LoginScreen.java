package com.jacksw.espresso_login_demo;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

public class LoginScreen {

    public void inputUsername(String username) {
        onView(withId(R.id.username)).perform(typeText(username));
        onView(withId(R.id.username)).perform(closeSoftKeyboard());
    }

    public void inputPassword(String password) {
        onView(withId(R.id.password)).perform(typeText(password));
        onView(withId(R.id.password)).perform(closeSoftKeyboard());
    }

    public void clickLoginButton() {
        onView(withId(R.id.login_button)).perform(click());
    }

    public void showExpectedDialogText(String expectedDialogText) {
        onView(withText(expectedDialogText)).check(matches(isDisplayed()));
    }

}

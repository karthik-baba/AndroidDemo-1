package com.mytaxi.robotpages;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
public class LoginPage {
    private ViewInteraction userName;
    private ViewInteraction password;
    private ViewInteraction loginBtn;
    private ViewInteraction loginFailedNotification;

    public LoginPage()
    {
        this.userName=onView(withId(R.id.edt_username));
        this.password=onView(withId(R.id.edt_password));
        this.loginBtn=onView(withId(R.id.btn_login));
    }

    public LoginPage fn_LoginWithInvalidCredentials(String userName, String password)
    {
        this.userName.perform(clearText(),typeText(userName));
        this.password.perform(clearText(),typeText(password));
        closeSoftKeyboard();
        this.loginBtn.perform(click());
        return this;
    }
    public MyTaxiMainPage fn_Login(String userName, String password)
    {
        this.userName.perform(clearText(),typeText(userName),closeSoftKeyboard());
        this.password.perform(clearText(),typeText(password),closeSoftKeyboard());
        try {
            closeSoftKeyboard();
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.loginBtn.perform(click());


        return new MyTaxiMainPage();
    }
    public boolean fn_CheckFailureNotification()
    {
        try
        {
            this.loginFailedNotification = onView(withId(R.id.snackbar_text)).check(matches(isDisplayed()));
            return true;
        }
        catch(NoMatchingViewException e)
        {
            return false;
        }
    }

}

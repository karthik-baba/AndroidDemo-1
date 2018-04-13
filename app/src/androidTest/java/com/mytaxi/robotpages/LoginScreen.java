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
import static java.lang.Thread.sleep;

/**
 * Login Page Class
 * Contains the ui elements(ViewInteraction Object) within LoginScreen
 * Contains all possible methods which can be performed with the LoginScreen
 */
public class LoginScreen {
    private ViewInteraction userName;
    private ViewInteraction password;
    private ViewInteraction loginBtn;
    private ViewInteraction loginFailedNotification;

    public LoginScreen()
    {
        //Instantiate login page and locate the elements under test in Login Page
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        this.userName=onView(withId(R.id.edt_username));
        this.password=onView(withId(R.id.edt_password));
        this.loginBtn=onView(withId(R.id.btn_login));
    }

    /**
     * Method to login to myTaxi Demo app with invalid credentials.
     * Returns object of LoginScreen since the login will not be successful
     * @param userName
     * @param password
     * @return
     */
    public LoginScreen fn_LoginWithInvalidCredentials(String userName, String password)
    {
        this.userName.perform(clearText(),typeText(userName),closeSoftKeyboard());
        this.password.perform(clearText(),typeText(password),closeSoftKeyboard());
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.loginBtn.perform(click());
        return this;
    }

    /**
     * Method to login to myTaxi Demo app with valid credentials [provided during task description]
     * Returns object of MyTaxiMainPage - Landing page after logging into the app.
     * @param userName
     * @param password
     * @return
     */
    public MyTaxiMainScreen fn_Login(String userName, String password)
    {
        this.userName.perform(clearText(),typeText(userName),closeSoftKeyboard());
        this.password.perform(clearText(),typeText(password),closeSoftKeyboard());
        try {
            closeSoftKeyboard();
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.loginBtn.perform(click());


        return new MyTaxiMainScreen();
    }

    /**
     * Method to check the login failed notification displayed in the app
     * when logged in with invalid credentials
     * @return
     */
    public boolean fn_CheckFailureNotification()
    {
        try
        {
            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.loginFailedNotification = onView(withId(R.id.snackbar_text)).check(matches(isDisplayed()));
            return true;
        }
        catch(NoMatchingViewException e)
        {
            return false;
        }
    }

}

package com.mytaxi.robotpages;

import android.support.test.espresso.NoMatchingRootException;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;

import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNot.not;

/**
 * My Taxi Mage Page Class
 * Contains the ui elements(ViewInteraction Object) within landing page after logging into the app
 * Contains all possible methods which can be performed with the MyTaxiMainPage
 */
public class MyTaxiMainScreen {

    private ViewInteraction headerText;
    private ViewInteraction logoutLink;
    private ViewInteraction menuButton;
    private ViewInteraction searchBox;
    private ViewInteraction userNameInMyTaxiPage;
    private ViewInteraction logo;
    private ViewInteraction locationImgBtn;


    public MyTaxiMainScreen() {
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.headerText = onView(allOf(withText("mytaxi demo"),isDisplayed()));
        this.menuButton=onView(allOf(withContentDescription("Open navigation drawer"),isDisplayed()));
        this.searchBox=onView(allOf(withId(R.id.textSearch),isDisplayed()));

    }


    public MyTaxiMainScreen openSideMenu()
    {
        this.menuButton.perform(click());
        return this;
    }
    public MyTaxiMainScreen typeTextDriverName(String driveNameStartWith)
    {
        this.searchBox.perform(click());
        this.searchBox.perform(clearText(),typeTextIntoFocusedView(driveNameStartWith));
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
    public DriverProfileScreen selectDriver(MainActivity mActivity, String driver)
    {
        onView(withText(driver))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .check(matches(isDisplayed()));
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onView(withText(driver))
                .inRoot(withDecorView(not(is(mActivity.getWindow().getDecorView()))))
                .perform(scrollTo(),click());
        return new DriverProfileScreen();
    }
    public LoginScreen logOut()
    {
        this.logoutLink=onView(allOf(withText("Logout"),isDisplayed()));
        this.logoutLink.perform(click());
        return new LoginScreen();
    }

    /**
     * Checking if the Username is enabled in the sidebar menu
     * @param username
     * @return true if the username is present, false if the username is not
     *
     */
    public boolean verifyUserInfo(String username)
    {
        try
        {
            this.userNameInMyTaxiPage=onView(allOf(withId(R.id.nav_username),isDisplayed()));
            this.userNameInMyTaxiPage.check(matches(isEnabled()));
            return true;
        }
        catch(NoMatchingViewException |NoMatchingRootException e)
        {
            return false;
        }
        catch(AssertionError e)
        {
            return false;
        }

    }
    /**
     * Checking if the logo is enabled in the sidebar menu
     * @return true if the logo is present, false if the logo is not
     *
     */
    public boolean isLogoDisplayed()
    {
        try
        {
            this.logo=onView(allOf(withId(R.id.imageView),isDisplayed()));
            this.logo.check(matches(isEnabled()));
            return true;
        }

        catch(AssertionError | NoMatchingViewException |NoMatchingRootException e)
        {
            return false;
        }


    }

    /**
     * Checking if location button in myTaxi screen is clickable and enabled
     * @return
     */
    public boolean isLocationImgDisplayed()
    {
        try
        {
            this.locationImgBtn=onView(allOf(withId(R.id.fab),isDisplayed()));
            this.locationImgBtn.check(matches(isEnabled()));
            this.locationImgBtn.check(matches(isClickable()));
            return true;
        }

        catch(AssertionError | NoMatchingViewException |NoMatchingRootException e)
        {
            return false;
        }

    }

}

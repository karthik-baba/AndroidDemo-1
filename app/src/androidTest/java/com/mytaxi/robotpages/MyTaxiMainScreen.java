package com.mytaxi.robotpages;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;
import com.mytaxi.android_demo.R;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.robotpages.DriverProfileScreen;

import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
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


    public MyTaxiMainScreen fn_OpenMenu()
    {
        this.menuButton.perform(click());
        return this;
    }
    public MyTaxiMainScreen fn_TypeDriver(String driveNameStartWith)
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
    public DriverProfileScreen fn_SelectDriver(MainActivity mActivity, String driver)
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
    public LoginScreen fn_LogOut()
    {
        this.logoutLink=onView(allOf(withText("Logout"),isDisplayed()));
        this.logoutLink.perform(click());
        return new LoginScreen();
    }

}

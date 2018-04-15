package com.mytaxi.robotpages;

import android.support.test.espresso.NoMatchingRootException;
import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.ViewInteraction;

import com.mytaxi.android_demo.R;

import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static org.hamcrest.core.AllOf.allOf;

public class DriverProfileScreen {
    private ViewInteraction header;
    private ViewInteraction driverName;
    private ViewInteraction callBtn;
    private ViewInteraction imgDriverAvatar;
    private ViewInteraction imgDriverLocation;
    private ViewInteraction imgDriverDate;


    public DriverProfileScreen()
    {
        this.header=onView(allOf(withText("Driver Profile"),isDisplayed()));
        this.driverName=onView(allOf(withId(R.id.textViewDriverName),isDisplayed()));
        this.callBtn=onView(allOf(withId(R.id.fab),isDisplayed()));


    }
    public boolean verifyDriverName(String driverName)
    {
        try
        {
            this.driverName.check(matches(withText(driverName)));
            return true;
        }
        catch (AssertionError e)
        {
            return false;
        }
    }
    public MyTaxiMainScreen tapBackButton()
    {
        pressBack();
        try {
            sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new MyTaxiMainScreen();

    }

    public void tapCallButton()
    {
        this.callBtn.perform(click());
        try {
            sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isDriverAvatarImageDisplayed()
    {
        try
        {
            this.imgDriverAvatar=onView(allOf(withId(R.id.imageViewDriverAvatar),isDisplayed()));
            this.imgDriverAvatar.check(matches(isEnabled()));
            return true;
        }

        catch(AssertionError | NoMatchingViewException |NoMatchingRootException e)
        {
            return false;
        }
    }

    public boolean isDriverLocationImageDisplayed()
    {
        try
        {
            this.imgDriverLocation=onView(allOf(withId(R.id.imageViewDriverLocation),isDisplayed()));
            this.imgDriverLocation.check(matches(isEnabled()));
            return true;
        }

        catch(AssertionError | NoMatchingViewException |NoMatchingRootException e)
        {
            return false;
        }
    }

    public boolean isDriverDateImageDisplayed()
    {
        try
        {
            this.imgDriverDate=onView(allOf(withId(R.id.imageViewDriverDate),isDisplayed()));
            this.imgDriverDate.check(matches(isEnabled()));
            return true;
        }

        catch(AssertionError | NoMatchingViewException |NoMatchingRootException e)
        {
            return false;
        }
    }

    public boolean isCallButtonEnabled()
    {
        try
        {
            this.callBtn.check(matches(isEnabled()));
            return true;
        }
        catch(AssertionError  e)
        {
            return false;
        }

    }

}

package com.mytaxi.android_demo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.KeyEvent;


import com.jraska.falcon.FalconSpoonRule;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.robotpages.DriverProfileScreen;
import com.mytaxi.robotpages.LoginScreen;
import com.mytaxi.robotpages.MyTaxiMainScreen;
import com.mytaxi.testutils.ScreenCapture;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.matcher.ViewMatchers.withHint;
import static android.support.test.espresso.matcher.ViewMatchers.withInputType;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.sleep;
import static android.support.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UIEspressoTests {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Rule
    public final FalconSpoonRule falconSpoonRule = new FalconSpoonRule();

    @Before
    public void init() {
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }


    /**
     * UI test to check the app behaviour while logging in with
     * invalid credentials     *
     */
    @Test
    public void Test01_InvalidLoginTest() {

        LoginScreen loginObj = new LoginScreen();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_Page_-_Loaded");

        //Logging in with invalid credentials
        //Added wait for synchronization purpose.
        final LoginScreen failPage = loginObj.fn_LoginWithInvalidCredentials("karthik", "adfadef");


        //Checking if the 'Login failed' notification is displayed
        //And user still stays in Login Page
        Assert.assertEquals(true, failPage.fn_CheckFailureNotification());
        ScreenCapture.fn_shotWithoutWait(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_failed_message_displayed");

    }

    @Test
    public void Test02_ValidLoginTest() {
        LoginScreen loginObj = new LoginScreen();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_Page_-_Loaded");

        //Logging in with Valid credentials
        MyTaxiMainScreen myTaxiMainPageObj = loginObj.fn_Login("whiteelephant261", "video1");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"My_Taxi_Page_-_Loaded");

        //Typing 'Sar' as per the task details
        //Capturing the screenshot of the driver options displayed
        myTaxiMainPageObj.fn_TypeDriver("Sar");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Driver_suggestions_displayed");

        //Selecting the driver
        //Checking if the user is in Driver Profile Page
        //Capture Driver Profile Page
        DriverProfileScreen driverProfScreenObj = myTaxiMainPageObj.fn_SelectDriver(activityActivityTestRule.getActivity(), "Sarah Friedrich");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Driver_details_displayed");

        //Check if the driver name selected is displayed in Driver Profile Page
        Assert.assertEquals(true,driverProfScreenObj.fn_CheckDriverName("Sarah Friedrich"));

        //Tapping the call button
        driverProfScreenObj.fn_TapCallButton();
    }

    /**
     * Since the app was logged in successufully, loading the app again and checking if the app is logged in
     * Tapping on the menu button and logging out
     */
    @Test
    public void Test03_TearDown() {

        //On load of the app, check if the My Taxi Main screen is load [Not the login page again]
        MyTaxiMainScreen obj = new MyTaxiMainScreen();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Load_My_Taxi_Page");

        //Tap on the menu button
        obj.fn_OpenMenu();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"My_Taxi_Page_-_Menu_displayed");

        //Tap on Logout
        //Check if the user is in Login Page again
        obj.fn_LogOut();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_Page_-_Logged");

    }

}
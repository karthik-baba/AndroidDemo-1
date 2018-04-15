package com.mytaxi.android_demo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import static android.support.test.espresso.action.ViewActions.pressBack;
import com.jraska.falcon.FalconSpoonRule;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.robotpages.DriverProfileScreen;
import com.mytaxi.robotpages.LoginScreen;
import com.mytaxi.robotpages.MyTaxiMainScreen;
import com.mytaxi.testutils.ScreenCapture;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UIEspressoTests {
    @Rule
    public final FalconSpoonRule falconSpoonRule = new FalconSpoonRule();
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

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

        //Checking with emtyp password
        final LoginScreen failPage2 = loginObj.fn_LoginWithInvalidCredentials("whiteelephant261", "");
        Assert.assertEquals(true, failPage2.fn_CheckFailureNotification());
        ScreenCapture.fn_shotWithoutWait(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_failed_message_displayed");

        //Checking with empty username
        final LoginScreen failPage3 = loginObj.fn_LoginWithInvalidCredentials("", "video1");
        Assert.assertEquals(true, failPage3.fn_CheckFailureNotification());
        ScreenCapture.fn_shotWithoutWait(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_failed_message_displayed");

        //Checking with empty username & empty password
        final LoginScreen failPage4 = loginObj.fn_LoginWithInvalidCredentials("", "");
        Assert.assertEquals(true, failPage4.fn_CheckFailureNotification());
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
        myTaxiMainPageObj.typeTextDriverName("Sa");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Driver_suggestions_displayed");

        //Selecting the driver
        //Checking if the user is in Driver Profile Page
        //Capture Driver Profile Page
        DriverProfileScreen driverProfScreenObj = myTaxiMainPageObj.selectDriver(activityActivityTestRule.getActivity(), "Sarah Friedrich");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Driver_details_displayed");

        //Check if the driver name selected is displayed in Driver Profile Page
        Assert.assertEquals(true,driverProfScreenObj.verifyDriverName("Sarah Friedrich"));

        //Tapping the call button
        driverProfScreenObj.tapCallButton();
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
        obj.openSideMenu();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"My_Taxi_Page_-_Menu_displayed");

        //Tap on Logout
        //Check if the user is in Login Page again
        obj.logOut();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_Page_-_Logged");

    }

    @Test
    public void Test04_CheckUserInfoAndLogo()
    {
        LoginScreen loginObj = new LoginScreen();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_Page_-_Loaded");

        //Logging in with Valid credentials
        MyTaxiMainScreen myTaxiMainPageObj = loginObj.fn_Login("whiteelephant261", "video1");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"My_Taxi_Page_-_Loaded");

        //Opening menu
        myTaxiMainPageObj.openSideMenu();

        //Check if the username & logo is present in the side bar
        boolean checkUserInfo=myTaxiMainPageObj.verifyUserInfo("whiteelephant261");
        boolean checkLogo=myTaxiMainPageObj.isLogoDisplayed();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"My_Taxi_Page_-_UserInfo");

        //Loggingout
        //To make sure logout is performed before Assertion
        myTaxiMainPageObj.logOut();


        Assert.assertEquals(true,checkUserInfo);
        Assert.assertEquals(true,checkLogo);
    }

    @Test
    public void Test05_CheckLocationBtn()
    {
        LoginScreen loginObj = new LoginScreen();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_Page_-_Loaded");

        //Logging in with Valid credentials
        MyTaxiMainScreen myTaxiMainPageObj = loginObj.fn_Login("whiteelephant261", "video1");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"My_Taxi_Page_-_Loaded");

        boolean checkLocationBtn=myTaxiMainPageObj.isLocationImgDisplayed();

        //Open Menu
        myTaxiMainPageObj.openSideMenu();

        //Loggingout
        //To make sure logout is performed before Assertion
        myTaxiMainPageObj.logOut();

        //Asserting
        Assert.assertEquals(true,checkLocationBtn);

    }

    @Test
    public void Test06_CheckUIDriverProfilePage()
    {
        LoginScreen loginObj = new LoginScreen();

        //Logging in with Valid credentials
        MyTaxiMainScreen myTaxiMainPageObj = loginObj.fn_Login("whiteelephant261", "video1");

        //Typing 'Sar' as per the task details
        myTaxiMainPageObj.typeTextDriverName("Sa");

        //Selecting the driver
        //Checking if the user is in Driver Profile Page
        DriverProfileScreen driverProfScreenObj = myTaxiMainPageObj.selectDriver(activityActivityTestRule.getActivity(), "Sarah Friedrich");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Driver_details_displayed");

        //Checks
        boolean driverImage=driverProfScreenObj.isDriverAvatarImageDisplayed();
        boolean driverLocationImg=driverProfScreenObj.isDriverLocationImageDisplayed();
        boolean driverDateImg=driverProfScreenObj.isDriverDateImageDisplayed();
        boolean callBtnEnabled=driverProfScreenObj.isCallButtonEnabled();

        //Logging out before assertion
        MyTaxiMainScreen myTaxiMainScreenObj=driverProfScreenObj.tapBackButton();
        myTaxiMainPageObj.openSideMenu();
        myTaxiMainPageObj.logOut();

        Assert.assertEquals(true,driverImage);
        Assert.assertEquals(true,driverLocationImg);
        Assert.assertEquals(true,driverDateImg);
        Assert.assertEquals(true,callBtnEnabled);


    }
}
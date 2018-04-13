package com.mytaxi.android_demo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


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
import static java.lang.Thread.sleep;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMainActivity {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Rule
    public final FalconSpoonRule falconSpoonRule = new FalconSpoonRule();

    @Before
    public void init() {
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();
    }

    @Test
    public void Test01_InvalidLoginTest() {
        LoginScreen loginObj = new LoginScreen();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_Page_-_Loaded");

        final LoginScreen failPage = loginObj.fn_LoginWithInvalidCredentials("karthik", "adfadef");
        try
        {
            sleep(1000);
        }
        catch(InterruptedException e)
        {
            e.printStackTrace();
        }
        Assert.assertEquals(true, failPage.fn_CheckFailureNotification());
        ScreenCapture.fn_shotWithoutWait(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_failed_message_displayed");

    }

    @Test
    public void Test02_ValidLoginTest() {
        LoginScreen loginObj = new LoginScreen();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_Page_-_Loaded");
        MyTaxiMainScreen myTaxiMainPageObj = loginObj.fn_Login("whiteelephant261", "video1");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"My_Taxi_Page_-_Loaded");

        myTaxiMainPageObj.fn_TypeDriver("Sar");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Driver_suggestions_displayed");

        DriverProfileScreen driverProfScreenObj = myTaxiMainPageObj.fn_SelectDriver(activityActivityTestRule.getActivity(), "Sarah Friedrich");
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Driver_details_displayed");
        Assert.assertEquals(true,driverProfScreenObj.fn_CheckDriverName("Sarah Friedrich"));

        driverProfScreenObj.fn_TapCallButton();
    }

    @Test
    public void Test03_TearDown() {

        MyTaxiMainScreen obj = new MyTaxiMainScreen();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Load_My_Taxi_Page");

        obj.fn_OpenMenu();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"My_Taxi_Page_-_Menu_displayed");

        obj.fn_LogOut();
        ScreenCapture.fn_shot(falconSpoonRule,activityActivityTestRule.getActivity(),"Login_Page_-_Logged");

    }
}
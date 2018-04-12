package com.mytaxi.android_demo;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.MethodSorter;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static java.lang.Thread.*;
import static org.hamcrest.core.AllOf.allOf;


import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.robotpages.DriverProfileScreen;
import com.mytaxi.robotpages.LoginScreen;
import com.mytaxi.robotpages.MyTaxiMainScreen;

import junit.framework.Assert;

@RunWith(AndroidJUnit4.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMainActivity {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Before
    public void init() {
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();

    }

    @Test

    public void Test01_InvalidLoginTest() {
        LoginScreen loginObj = new LoginScreen();

        final LoginScreen failPage = loginObj.fn_LoginWithInvalidCredentials("karthik", "adfadef");
        Assert.assertEquals(true, failPage.fn_CheckFailureNotification());
    }

    @Test
    public void Test02_ValidLoginTest() {


        LoginScreen loginObj = new LoginScreen();
        MyTaxiMainScreen myTaxiMainPageObj = loginObj.fn_Login("whiteelephant261", "video1");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        myTaxiMainPageObj.fn_TypeDriver("Sar");
        DriverProfileScreen driverProfScreenObj = myTaxiMainPageObj.fn_SelectDriver(activityActivityTestRule.getActivity(), "Sarah Friedrich");

        System.out.println(driverProfScreenObj.fn_CheckDriverName("Sarah Friedrich"));
        driverProfScreenObj.fn_TapCallButton();


        //MyTaxiMainScreen myTaxiMainScreenObj2=driverProfScreenObj.fn_TapBackButton();
        //myTaxiMainScreenObj2.fn_OpenMenu();

        //myTaxiMainScreenObj2.fn_LogOut();

    }

    @Test
    public void Test03_TearDown() {

        MyTaxiMainScreen obj = new MyTaxiMainScreen();
        obj.fn_OpenMenu();
        obj.fn_LogOut();

    }


}
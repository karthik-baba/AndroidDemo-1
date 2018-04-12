package com.mytaxi.android_demo;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.robotpages.DriverProfileScreen;
import com.mytaxi.robotpages.LoginScreen;
import com.mytaxi.robotpages.MyTaxiMainScreen;
import junit.framework.Assert;
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
    }

    @Test
    public void Test03_TearDown() {
        MyTaxiMainScreen obj = new MyTaxiMainScreen();
        obj.fn_OpenMenu();
        obj.fn_LogOut();
    }
}
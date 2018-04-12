package com.mytaxi.android_demo;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;


import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


import com.mytaxi.android_demo.activities.MainActivity;
import com.mytaxi.robotpages.LoginPage;

import junit.framework.Assert;

@RunWith(AndroidJUnit4.class)
public class TestMainActivity {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    @Before
    public void init(){
        activityActivityTestRule.getActivity()
                .getSupportFragmentManager().beginTransaction();

    }

    @Test
    public void InvalidLoginTest(){

        LoginPage loginObj=new LoginPage();
        final LoginPage failPage = loginObj.fn_LoginWithInvalidCredentials("karthik", "adfadef");
        onView(withText("Login failed")).check(matches(isDisplayed()));
      //  Assert.assertEquals(true,failPage.fn_CheckFailureNotification());
    }




}
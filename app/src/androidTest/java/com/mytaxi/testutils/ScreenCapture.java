package com.mytaxi.testutils;

import android.app.Activity;
import android.support.test.rule.ActivityTestRule;

import com.jraska.falcon.FalconSpoonRule;
import com.mytaxi.android_demo.activities.MainActivity;

import static java.lang.Thread.sleep;

public class ScreenCapture {
    public static void fn_shot(FalconSpoonRule falconSpoonRule, Activity act, String tag)
    {
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        falconSpoonRule.screenshot(act,tag);
    }

    public static void fn_shotWithoutWait(FalconSpoonRule falconSpoonRule, Activity act, String tag) {
        falconSpoonRule.screenshot(act,tag);
    }
}

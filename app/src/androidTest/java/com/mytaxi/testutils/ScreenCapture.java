package com.mytaxi.testutils;

import android.app.Activity;

import com.jraska.falcon.FalconSpoonRule;

import static java.lang.Thread.sleep;

public class ScreenCapture {
    /**
     * Takes screenshot on the view which is displayed.
     * It waits for 4secs for synchronization purpose and then takes screenshot
     * @param falconSpoonRule
     * @param act - Activity
     * @param tag - Screenshot Name - Should not contain special characters
     */
    public static void fn_shot(FalconSpoonRule falconSpoonRule, Activity act, String tag)
    {
        try {
            sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        falconSpoonRule.screenshot(act,tag);
    }

    /**
     * Takes screenshot on the view which is displayed.
     * It taking screenshot without waiting
     * @param falconSpoonRule
     * @param act - Activity
     * @param tag - Screenshot Name - Should not contain special characters
     */
    public static void fn_shotWithoutWait(FalconSpoonRule falconSpoonRule, Activity act, String tag) {
        falconSpoonRule.screenshot(act,tag);
    }
}

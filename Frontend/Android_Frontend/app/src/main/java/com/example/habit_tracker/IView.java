package com.example.habit_tracker;

import org.json.JSONException;

/**
 *
 * @author Yuichi Hamamoto
 *
 */
public interface IView {
    /**
     * show on textView
     * @param s
     * Given string
     */
    public void showText (int n, String s) throws JSONException;

    /**
     * toast text
     * @param s
     * given string
     */
    public void toastText (String s);

    /**
     * switch activity
     * @param n
     * Given int
     */
    public void switchActivity(int n);
}

package com.test.before.interview.Component;
import android.graphics.Typeface;
import com.test.before.interview.Apps;

public class Utils {
    public static Typeface typefaceNormal(){
        Typeface tf = Typeface.createFromAsset(Apps.getInstance().getAssets(), "fonts/trebuc.ttf");
        return tf;
    }

    public static Typeface typefaceBold(){
        Typeface tf = Typeface.createFromAsset(Apps.getInstance().getAssets(), "fonts/trebucbd.ttf");
        return tf;
    }

    public static Typeface typefaceOblique(){
        Typeface tf = Typeface.createFromAsset(Apps.getInstance().getAssets(), "fonts/trebucbi.ttf");
        return tf;
    }
}

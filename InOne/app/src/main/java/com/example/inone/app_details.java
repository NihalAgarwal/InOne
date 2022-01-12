package com.example.inone;
import android.graphics.drawable.Drawable;
import android.util.Log;

public class app_details {

    private CharSequence appName;
    private CharSequence appPackageName;
    private Drawable appIcon;

      public app_details(CharSequence appName,CharSequence appPackageName,Drawable appIcon ){
        this.appName = appName;
        this.appPackageName = appPackageName;
        this.appIcon = appIcon;
    }

    public CharSequence getAppName() {
        return appName;
    }

    public CharSequence getAppPackageName() {
        return appPackageName;
    }

    public Drawable getAppIcon() {
        return appIcon;
    }
}

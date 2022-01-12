package com.example.inone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ArrayList<app_details> app_info;
    private PackageManager packageManager;
    private CharSequence appName;
    private CharSequence appPackageName;
    private Drawable appIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        loadApps();

    }
    public void weather_app(View v){
        Intent intent = new Intent(MainActivity.this, WeatherMain.class);
        Log.d("Error","Weather app");
        startActivity(intent);
    }
    public void pnrStatus(View v){
        Intent intent = new Intent(MainActivity.this, pnr_status.class);
        startActivity(intent);
    }

    public void loadApps() {
        try {
            packageManager = getPackageManager();
            app_info = new ArrayList<>();
            Intent intent = new Intent(Intent.ACTION_MAIN, null);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent, 0);
            Collections.sort(resolveInfoList, new ResolveInfo.DisplayNameComparator(packageManager));
            ArrayList<ResolveInfo> ab = new ArrayList<>();
            ab.add(resolveInfoList.get(0));

            for(int i= 1; i< resolveInfoList.size(); i++) {
                String prev = resolveInfoList.get(i - 1).loadLabel(packageManager).toString();
                String curr = resolveInfoList.get(i).loadLabel(packageManager).toString();
                if (!curr.equals(prev)) {
                    ab.add(resolveInfoList.get(i));
                    Log.d("Name1: ",curr);
                }
            }
            for (ResolveInfo resolveInfo : ab) {
                appName = resolveInfo.loadLabel(packageManager).toString();
                appPackageName = resolveInfo.activityInfo.packageName;
                appIcon = resolveInfo.activityInfo.loadIcon(packageManager);
                app_details ap = new app_details(appName, appPackageName,appIcon);
                app_info.add(ap);
                Log.d("app_info", ap.getAppName()+" "+ap.getAppPackageName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        initRecyclerView();
    }
        public void listener(String a) {
            try{
                Intent intent = getPackageManager().getLaunchIntentForPackage(a);
                if(intent != null){
                    startActivity(intent);
                }
                else{
                    throw new NullPointerException();
                }
                if (intent != null) {
                    startActivity(intent);
                }
            }catch (Exception e){
                Log.d("Error",a);
                Log.d("Error",e.toString());
            }
        }

    private void initRecyclerView(){
        Log.d("Recycler view ", "Recycler view called");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);   //latest edit
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, app_info);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}


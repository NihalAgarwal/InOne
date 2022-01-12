package com.example.inone;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.content.pm.PackageManager;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private ArrayList<app_details> appInfo = new ArrayList<>();
    private Context mcontext;
    private PackageManager packageManager;

    public RecyclerViewAdapter( Context mcontext, ArrayList<app_details> appInfo) {
        this.appInfo = appInfo;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d("onCreateviewHolder", "Methodalled");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.app_placing,parent,false);
        ViewHolder holder = new ViewHolder(view);
        Log.d("onCreateviewHolder", "Method finsihed");
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
            Log.d("onBind","On BindViewHolderCalled");
            int b= position *3;
            try {
                app_details ad1 = appInfo.get(b);
                app_details ad2 = appInfo.get(b + 1);
                app_details ad3 = appInfo.get(b + 2);
                holder.c1.setImageDrawable(ad1.getAppIcon());
                holder.c2.setImageDrawable(ad2.getAppIcon());
                holder.c3.setImageDrawable(ad3.getAppIcon());
                holder.t1.setText(ad1.getAppName());
                holder.t2.setText(ad2.getAppName());
                holder.t3.setText(ad3.getAppName());

                holder.c1.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        v.animate().scaleXBy(1f).scaleYBy(1f).alpha(0.5f).withStartAction(new Runnable(){
                            public void run(){

                            }
                        });

// EndAction
                        v.animate().scaleXBy(0f).scaleYBy(0f).alpha(1).withEndAction(new Runnable(){
                            public void run(){
                                try {
                                    String a = appInfo.get(position*3).getAppPackageName().toString();
                                    ((MainActivity)mcontext).listener(a);

                                }catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                        });
//
                    }
                });
                holder.c2.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        try {
                            String a = appInfo.get((position*3)+1).getAppPackageName().toString();
                            ((MainActivity)mcontext).listener(a);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });
                holder.c3.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        try {
                            String a = appInfo.get((position*3)+2).getAppPackageName().toString();
                            ((MainActivity)mcontext).listener(a);

                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                });

            } catch (Exception e){
                Log.d("Error",e.toString());
            }
    }

    @Override
        public int getItemCount() {
        return (int) Math.floor(appInfo.size()/3.0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView c1;
        CircleImageView c2;
        CircleImageView c3;
        TextView t1;
        TextView t2;
        TextView t3;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            c1 = itemView.findViewById(R.id.circleImageView);
            c2 = itemView.findViewById(R.id.circleImageView1);
            c3 = itemView.findViewById(R.id.circleImageView2);
            t1 = itemView.findViewById(R.id.textView);
            t2 = itemView.findViewById(R.id.textView2);
            t3 = itemView.findViewById(R.id.textView3);
        }
    }
}

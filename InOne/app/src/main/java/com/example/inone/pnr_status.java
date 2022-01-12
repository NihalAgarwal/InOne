package com.example.inone;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.androdocs.httprequest.HttpRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class pnr_status extends AppCompatActivity {

    EditText train_no;
    TextView  train_no1, train_name, position;
    public String number = "12232";
    private String days_ = "";

    public void pnr_check(View v){
        number = train_no.getText().toString();
        Log.d("1check","Called");
        new pnr_task().execute();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pnr_status);

        train_no = (EditText)findViewById(R.id.editText2);
        train_no1 = findViewById(R.id.textView8);
        train_name = findViewById(R.id.textView9);
        position = findViewById(R.id.textView11);
        findViewById(R.id.textView6).setVisibility(View.GONE);
        findViewById(R.id.textView7).setVisibility(View.GONE);

    }
    class pnr_task extends AsyncTask<String, Void, String> {
        protected void onPreExecute() {
            super.onPreExecute();
            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.constraintlayout).setVisibility(View.GONE);
//            findViewById(R.id.errorText).setVisibility(View.GONE);
        }
        @Override
        protected String doInBackground(String... args) {
            Log.d("1Calling ","Start");
            String response = HttpRequest.excuteGet("https://api.railwayapi.com/v2/name-number/train/"+number+"/apikey/z42p77gx0v/");
            Log.d("1Reaponse","get");
            return response;
        }

        @Override
        protected void onPostExecute(String result) {
            try{

                JSONObject jsonObj = new JSONObject(result);

                JSONObject train = jsonObj.getJSONObject("train");
                train_name.setText(train.getString("name"));
                train_no1.setText(train.getString("number"));
//                Log.d("response",train.getString("number"));
                String days = train.getString("days");
                JSONArray jarray = new JSONArray(days);

                for (int j = 0; j< jarray.length(); j++){
                    JSONObject obj = jarray.getJSONObject(j);
                    if(obj.getString("runs").equals("Y")){
                        days_ += obj.getString("code");
                        days_ += " ";
                    }
                    Log.d("days",days_);
                }
                position.setText(days_);
                findViewById(R.id.loader).setVisibility(View.GONE);
                findViewById(R.id.textView6).setVisibility(View.VISIBLE);
                findViewById(R.id.textView7).setVisibility(View.VISIBLE);
                findViewById(R.id.constraintlayout).setVisibility(View.VISIBLE);

            } catch (JSONException e) {
                Log.d("Errorin",e.toString());
                e.printStackTrace();
            }
        }

    }

}
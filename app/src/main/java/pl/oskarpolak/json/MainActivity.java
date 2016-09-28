package pl.oskarpolak.json;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


       new ConnectToWWW().execute();
    }

    private void readJSON(String s){

        try {
            JSONArray rootArray = new JSONArray(s);

            for(int i = 0; i <= rootArray.length()-1; i++){
                JSONObject object = rootArray.getJSONObject(i);

                Log.e("JSON", object.getString("guid"));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


    private class ConnectToWWW extends AsyncTask<Void, String, String>{

        @Override
        protected String doInBackground(Void... params) {
            HttpConnect connect = new HttpConnect();
            String ourWebsite = connect.makeCall("http://www.json-generator.com/api/json/get/bQFBDNeEpu?indent=2");
            return ourWebsite;
        }

        @Override
        protected void onPostExecute(String ourText) {
            readJSON(ourText);
        }
    }




}

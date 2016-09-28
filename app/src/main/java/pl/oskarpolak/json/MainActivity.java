package pl.oskarpolak.json;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        Intent i = new Intent(this, Main2Activity.class);
        startActivityForResult(i, 2);

        // Ta metoda uruchamia połączenie z internetem, pobiera źródło i czyta kod JSON.
      // new ConnectToWWW().execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 2 && resultCode == RESULT_OK){
            Toast.makeText(this, data.getExtras().getString("code"), Toast.LENGTH_LONG).show();
        }
    }

    private void readJSON(String s){

        try {
            JSONArray rootArray = new JSONArray(s);

            for(int i = 0; i <= rootArray.length()-1; i++){
                JSONObject object = rootArray.getJSONObject(i);

                  JSONArray friendsArray = object.getJSONArray("friends");

                    for(int o = 0; o < friendsArray.length(); o++) {
                          JSONObject friendType = friendsArray.getJSONObject(o);
                           Log.e("Friends", friendType.getString("name"));


                    }

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
            Log.e("JSON", ourWebsite);
            return ourWebsite;
        }

        @Override
        protected void onPostExecute(String ourText) {
            readJSON(ourText);
        }
    }




}

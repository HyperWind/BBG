package com.treehugers.gifted.treehuggers;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static java.net.Proxy.Type.HTTP;

public class ServerManager extends AsyncTask<String,Void,Void> {

    public OnFinishListener onFinish;
    private String method_type;
    private ArrayList<Tree> AllTrees = new ArrayList<>();
    private Context context;
    public static String FETCH_TREES = "FETCHING_TREES";
    public String response = "";
    public static String SERVER_ADRESS_FETCH_TREES = "http://192.168.137.1:8080/api/trees";

    public ServerManager(Context context){
        this.context = context;
    }

    public void setOnFinishListener(OnFinishListener listener){
        this.onFinish = listener;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if(onFinish!=null){
            onFinish.onFinish(1, AllTrees);
            Log.i("TEST", response);
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Void aVoid) {
        super.onCancelled(aVoid);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Void doInBackground(String... strings) {
        method_type = strings[0];
        if(method_type.equals(FETCH_TREES)){
            AllTrees = allTheTrees();
        }

        return null;
    }

    ArrayList<Tree> allTheTrees(){
        Log.i("TEST", "Starting fetching...");
        ArrayList<Tree> trees = new ArrayList<>();
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        try {
            URL url = new URL(SERVER_ADRESS_FETCH_TREES);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();


            InputStream stream = connection.getInputStream();

            reader = new BufferedReader(new InputStreamReader(stream));

            StringBuffer buffer = new StringBuffer();
            String line = "";

            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                Log.d("TEST: ", "> " + line);   //here u ll get whole response...... :-)

            }

            String response = buffer.toString();
            Log.i("TEST", "RESPONSE IS: " + response);
            try{
                JSONArray jsonArr = new JSONArray(response);

                for (int i = 0; i < jsonArr.length(); i++)
                {
                    JSONObject jsonObj = jsonArr.getJSONObject(i);
                    double longtitude = jsonObj.getDouble("longitude");
                    double latitude = jsonObj.getDouble("latitude");
                    boolean hugged = jsonObj.getBoolean("hugged");
                    trees.add(new Tree(longtitude,latitude,hugged));
                }
            }catch (Exception e){

            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return trees;
    }





}

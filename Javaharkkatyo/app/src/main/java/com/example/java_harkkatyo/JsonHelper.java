package com.example.java_harkkatyo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class JsonHelper {

    private static String JSON_URL = "https://rajapinnat.ymparisto.fi/api/jarvirajapinta/1.0/odata/Jarvi";
    private String lakename, kuntaname;
    private Double avgDepth, shoreLength, area;
    private ArrayList<Lake> lakes;

    private static JsonHelper jsonHelper;

    private JsonHelper(){

    }

    public static JsonHelper getInstance(){
        if (jsonHelper == null){
            jsonHelper = new JsonHelper();
            return jsonHelper;
        }
        else{
            return jsonHelper;
        }
    }



    protected String getJSON(){
        String response = null;
        try{
            URL url = new URL(JSON_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            while((line = bufferedReader.readLine()) != null ){
                sb.append(line).append("\n");
            }

            response = sb.toString();
            inputStream.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }




    protected ArrayList<Lake> ReadJSON(String s) throws JSONException {
        ArrayList<Lake> lakes = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("value");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                lakename = jsonObject1.getString("Nimi");
                kuntaname = jsonObject1.getString("KuntaNimi");

                if(jsonObject1.getString("SyvyysKeski") == "null"){ avgDepth = -1.0; }
                else { avgDepth = jsonObject1.getDouble("SyvyysKeski"); }

                if (jsonObject1.getString("Rantaviiva") == "null"){ shoreLength = -1.0; }
                else{ shoreLength = jsonObject1.getDouble("Rantaviiva"); }

                if (jsonObject1.getString("Vesiala") == "null"){ area = -1.0;}
                else{ area = jsonObject1.getDouble("Vesiala"); }

                Lake lake = new Lake();
                lake.setLakename(lakename);
                lake.setKuntaname(kuntaname);
                lake.setAvgDepth(avgDepth);
                lake.setShoreLength(shoreLength);
                lake.setArea(area);
                lakes.add(lake);

            }
            return lakes;
        } finally {
            return lakes;
        }
    }


}
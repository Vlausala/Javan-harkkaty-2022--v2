/*
 *Course: CT60A2411 Olio-ohjelmointi
 *Date: 29.4.2022
 *Group: Matti Lankinen, Valtteri Lausala, Jan-Peter Kauppinen
 */
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

/**
 * Class used to querying the jarvirajapinta OData-api.
 *
 */
public class JsonHelper {

    //OData-URI
    private static String JSON_URL = "https://rajapinnat.ymparisto.fi/api/jarvirajapinta/1.0/odata/Jarvi";
    private static JsonHelper jsonHelper;

    //Singleton- pattern
    private JsonHelper(){ }

    public static JsonHelper getInstance(){
        if (jsonHelper == null){
            jsonHelper = new JsonHelper();
            return jsonHelper;
        }
        else{
            return jsonHelper;
        }
    }


    /**
     * Method used to save data from api to raw string format
     * @return response Contents of the query in json format
     */
    protected String getJSON(){
        String response = null;
        try{
            //Connection to API is set
            URL url = new URL(JSON_URL);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            //Buffered-reader is set up
            InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            //StringBuilder is set up to build the response
            StringBuilder sb = new StringBuilder();
            String line = null;
            //String is built by reading line by line
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


    /**
     * This function parses json-formatted string response from the jarvirajapinta-API
     * @param s Parsable json-formatted string containing response from jarvirajapinta-API
     * @return ArrayList containing Lake-objects
     * @throws JSONException
     */
    protected ArrayList<Lake> ReadJSON(String s) throws JSONException {
        String lakename, kuntaname;
        Double avgDepth, shoreLength, area;
        ArrayList<Lake> lakes = new ArrayList<>();
        try {
            //Parsable JSONObject
            JSONObject jsonObject = new JSONObject(s);
            //Retrieving the correct array containing the data
            JSONArray jsonArray = jsonObject.getJSONArray("value");

            //Values are looped through
            for (int i = 0; i < jsonArray.length(); i++) {
                //Row is collected from the array
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                //Values are saved to strings
                lakename = jsonObject1.getString("Nimi");
                kuntaname = jsonObject1.getString("KuntaNimi");

                //Error handling for retrieving values
                if(jsonObject1.getString("SyvyysKeski") == "null"){ avgDepth = -1.0; }
                else { avgDepth = jsonObject1.getDouble("SyvyysKeski"); }

                //Error handling for retrieving values
                if (jsonObject1.getString("Rantaviiva") == "null"){ shoreLength = -1.0; }
                else{ shoreLength = jsonObject1.getDouble("Rantaviiva"); }

                //Error handling for retrieving values
                if (jsonObject1.getString("Vesiala") == "null"){ area = -1.0;}
                else{ area = jsonObject1.getDouble("Vesiala"); }

                //Lake-object is created
                Lake lake = new Lake();

                //Lake-object is initialized
                lake.setLakename(lakename);
                lake.setKuntaname(kuntaname);
                lake.setAvgDepth(avgDepth);
                lake.setShoreLength(shoreLength);
                lake.setArea(area);

                //Lake is added to arraylist
                lakes.add(lake);

            }
            return lakes;
        } finally {
            return lakes;
        }
    }


}
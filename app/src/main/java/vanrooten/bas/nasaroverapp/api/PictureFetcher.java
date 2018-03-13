package vanrooten.bas.nasaroverapp.api;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import vanrooten.bas.nasaroverapp.domain.Picture;
import vanrooten.bas.nasaroverapp.interfaces.OnPictureAvailable;

/**
 * Created by basva on 13/03/2018.
 */

public class PictureFetcher extends AsyncTask<String, Void, String> {

    // Global Declarations
    private OnPictureAvailable listener = null;
    private static final String TAG = "PictureFetcher";
    private static final String key = Key.getKey();

    public PictureFetcher(OnPictureAvailable listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {

        InputStream inputStream = null;
        int responseCode = -1;
        String pictureURL = params[0];
        String response = "";
        Log.d(TAG, "Variables were made.");

        try {
            URL url = new URL(pictureURL);
            URLConnection urlConnection = url.openConnection();
            Log.d(TAG, "Try variables were made.");
            Log.d(TAG, "URLConnection has been made.");

            if (!(urlConnection instanceof HttpURLConnection)) {
                return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");
            Log.d(TAG, "Everything was prepared to make the httpConnection");


            // Connect the httpConnection (Go to the internet and make a connection)
            httpConnection.connect();
            Log.d(TAG, "httpConnection.connect() was executed");


            // Checking if responseCode is OK.
            responseCode = httpConnection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
                Log.e(TAG, "doInBackground response = " + response);
            } else {
                Log.e(TAG, "Error, invalid response");
            }


            // Handling Internet-related exceptions.
        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackgroundMalformedURLException " + e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e(TAG, "doInBackgroundIOException " + e.getLocalizedMessage());
            return null;
        }

        // Returning JSON-response to onPostExecute
        return response;

    }

    public void onPostExecute(String response) {

        // If the response is empty, return nothing.
        if (response == null || response == ""){
            return;
        }


        JSONObject jsonObject;

        try  {
            // Filling jsonObject which stores and parses the api response sent by NASA.
            jsonObject = new JSONObject(response);

            JSONArray photos = jsonObject.getJSONArray("photos");

            for (int i = 1; i < photos.length(); i++) {
                JSONObject photo = photos.getJSONObject(i);
                JSONObject camera = photo.getJSONObject("camera");

                // Picture data
                int pictureId = photo.getInt("id");
                int pictureSol = photo.getInt("sol");
                String pictureURL = photo.getString("img_src");
                String pictureEarthDate = photo.getString("earth_date");

                // Camera data
                int cameraID = camera.getInt("id");
                String cameraShortName = camera.getString("name");
                String cameraFullName = camera.getString("full_name");

                Log.d(TAG, "Picture with ID " + pictureId + " and PictureURL " + pictureURL + " grabbed successfully.");

                // Adding gained info to Picture object.
                Picture picture = new Picture(pictureId);

                picture.setPictureSol(pictureSol);
                picture.setPictureURL(pictureURL);
                picture.setPictureEarthDate(pictureEarthDate);
                picture.setCameraID(cameraID);
                picture.setCameraShortName(cameraShortName);
                picture.setCameraFullName(cameraFullName);


                // Notifying listener that new picture is available.
                listener.onNewPictureAvailable(picture);
            }

            // Handling JSON-related exceptions.
        } catch (JSONException e) {
            Log.e(TAG, "onPostExecute JSONException: " + e.getLocalizedMessage());
        }
    }

    private static String getStringFromInputStream(InputStream is){
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {
            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            // Handling Exceptions
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

        // Returning InputStream as String
        return sb.toString();
    }

}

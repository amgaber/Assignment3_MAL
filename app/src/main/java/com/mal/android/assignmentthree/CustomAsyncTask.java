package com.mal.android.assignmentthree;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by toshiba1 on 7/26/2016.
 */
public class CustomAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = CustomAsyncTask.class.getSimpleName();

    private AsyncTaskListener asyncTaskListener;

    // Will contain the raw JSON response as a string.
    String facebookJsonString;

    MainListActivityFragment fragment = new MainListActivityFragment();


    public CustomAsyncTask(AsyncTaskListener activityContext) {
        this.asyncTaskListener = activityContext;
    }

    @Override
    protected String doInBackground(Void... voids) {

        //Setting Connection to the url
        HttpURLConnection urlConnection = null;

        // Read the input stream into a String
        BufferedReader reader = null;


        URL url = null;
        try {
            //Using Uri.Builder to connect to the URL="https://dl.dropboxusercontent.com/s/7rvknz9e6tfprun/facebookFeed.json"

            Uri.Builder builder = new Uri.Builder();

            builder.scheme("https")
                    .authority("dl.dropboxusercontent.com")
                    .appendPath("s")
                    .appendPath("7rvknz9e6tfprun")
                    .appendPath("facebookFeed.json")
                    .build();


            url = new URL(builder.toString());

            String urIBuilder = builder.build().toString();
            Log.v(TAG, "urIBuilder:: " + urIBuilder);

            // Create the request to   url, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            //Read inputstream into string
            InputStream is = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (is == null) {
                // Nothing to do.
                facebookJsonString = null;
                return facebookJsonString;
            }
            reader = new BufferedReader(new InputStreamReader(is));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            Log.v(TAG, "BUFFER Reader: " + buffer.toString());

            if (buffer.length() == 0) {
                return null;
            }

            facebookJsonString = buffer.toString();
            return facebookJsonString;
        } catch (MalformedURLException e) {

            fragment.message("URL needs to be fixed !" + e.getMessage().toString());
            Log.v(TAG, "URL exception:: " + e.getMessage().toString());
        } catch (ProtocolException e) {
            fragment.message("URL needs to be fixed !" + e.getMessage().toString());
            Log.v(TAG, "URL exception:: " + e.getMessage().toString());
        } catch (FileNotFoundException e) {
            fragment.message("FileNotFoundException !" + e.getMessage().toString());

            try {
                throw new FileNotFoundException("Failed to find file ,please check URL");
            } catch (FileNotFoundException e1) {
                fragment.message("FileNotFoundException !" + e.getMessage().toString());

            }
            Log.v(TAG, "FileNotFoundException: " + e.getMessage().toString());
        } catch (IOException e) {
            fragment.message("Something went wrong ! " + e.getMessage().toString());
            Log.v(TAG, "if the output doesn't return:: " + e.getMessage().toString());
            //If the code doesn't return facebookJsonString ,we can't parse it
            facebookJsonString = null;
            return facebookJsonString;
        } finally {
            //We have to close connection & reader after both finishing and catching error
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    fragment.message("Error closing stream ! " + e.getMessage().toString());
                    Log.e(TAG, "Error closing stream", e);
                }
            }
        }

        return facebookJsonString;
    }


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        asyncTaskListener.notifyUpdate(s);
    }
}

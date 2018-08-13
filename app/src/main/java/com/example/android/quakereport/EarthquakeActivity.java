/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity {

    //The only way to update the contents of the list is to update
    // the data set within the EarthquakeAdapter.
    // To access and modify the instance of the EarthquakeAdapter,
    // we need to make it a global variable in the EarthquakeActivity.



    // Set a global variable named mAdapter of the return type EarthquakeAdapter
    private EarthquakeAdapter mAdapter;

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    /** URL for earthquake data from the USGS dataset */
    private static final String USGS_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=6&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
/*        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();*/

        /*
        // Delete the placeholers
        earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2, 2016"));
        earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2, 2016"));
        earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2, 2016"));
        earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2, 2016"));
        earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2, 2016"));
        earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2, 2016"));
        earthquakes.add(new Earthquake("7.2","San Francisco","Feb 2, 2016"));*/

        /*// Don't need keyword new => because ArrayList here is a return type
        ArrayList<Earthquake> earthquakes = QueryUtils.extractEarthquakes();*/

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        /*// Create a new {@link ArrayAdapter} of earthquakes
        final EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);*/

        // Create a EarthquakeAdapter with a reference to the variable mAdapter created above
        // Meaning, A VARIABLE AND NEW OBJECT WORK WITH EACH OTHER
        // Adapter set on ListView, not ArrayList
        mAdapter = new EarthquakeAdapter (this, new ArrayList<Earthquake>());


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(mAdapter);



        // Start the AsyncTask to fetch the earthquake data
        // Passing the URL here
        EarthquakeAsyncTask task = new EarthquakeAsyncTask();
        task.execute(USGS_REQUEST_URL);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected earthquake
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Find the current earthquake that was clicked on
                Earthquake currentEarthquake = mAdapter.getItem(position);

                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri earthquakeUri = Uri.parse(currentEarthquake.getUrl());

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, earthquakeUri);

                // Send the intent to launch a new activity
                startActivity(websiteIntent);
            }
        });
    }

    //WHY 3rd parameter is List<Earthquake> not ArrayList<Earthquake>
    private class EarthquakeAsyncTask extends AsyncTask<String, Void, List<Earthquake>>{

        @Override
        protected List<Earthquake> doInBackground(String... urls) {



            if (urls.length < 1 || urls[0] == null){
                return null;
            }


            List<Earthquake> result = QueryUtils.fetchEarthquakeData(urls [0]);
            return result;
        }

        // VERY HARD TO GRASP HERE! because of new method and logic
        @Override
        protected void onPostExecute(List<Earthquake> data /*earthquakes*/) {

            //Clear the adapter of previous earthquake data
            mAdapter.clear();

            // If there is a valid list of {@link Earthquake}s, then add them to the adapter's
            if (data != null && !data.isEmpty()){
                mAdapter.addAll(data);
            }
        }
    }
}

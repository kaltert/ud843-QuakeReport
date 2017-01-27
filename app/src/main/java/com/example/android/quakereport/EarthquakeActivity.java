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
import android.widget.ListView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    /**
     * Default request for USGS, earthquakes around Kosovo.
     */
    private static final String DEFAULT_REQUEST = "http://earthquake.usgs.gov/fdsnws/event/1/" +
            " query?format=geojson&starttime=10160101&endtime=20170101&minmagnitude=5" +
            "&latitude=42.6629&longitude=21.1655" +
            "&maxradiuskm=100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Start Task to get earthquakes from USGS
        GetEarthquakesTask getEarthquakesTask = new GetEarthquakesTask();
        getEarthquakesTask.execute(DEFAULT_REQUEST);

    }

    /**
     * Helper method for starting a a web-browser,
     * to display  earthquake details.
     */
    public void openWebPage(String url) {
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * Helper method for updating UI with the data from USGS web-site
     */
    private void updateUI(final ArrayList<Earthquake> earthquakes) {

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        EarthquakeAdapter adapter = new EarthquakeAdapter(this, earthquakes);


        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        // Set the item click listener on the {@link ListView}
        // so that the listener can respond on item click events
        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long rowId) {
                // Clicked earthquake
                Earthquake clickedEarthquake = earthquakes.get(position);

                // Details url for the selected earthquake
                String detailsUrl = clickedEarthquake.getDetails();

                // Open details web-page for the clicked earthquake
                openWebPage(detailsUrl);
            }
        });
    }

    /**
     * Getting Earthquakes Task, consists of crating an URl, making a HTTP request
     * getting the response and using it to make a list of earthquakes
     */
    private class GetEarthquakesTask extends AsyncTask<String, Void, ArrayList<Earthquake>> {
        @Override
        protected ArrayList<Earthquake> doInBackground(String... strings) {
            // List of earthquakes
            ArrayList<Earthquake> earthquakes;
            // Get earthquakes form USGS
            earthquakes = QueryUtils.getEarthquakes(strings[0]);

            return earthquakes;
        }

        @Override
        protected void onPostExecute(ArrayList<Earthquake> earthquakes) {
            if (earthquakes == null) {
                return;
            }
            // Update user interface if there erthquakes from USGS web-site.
            updateUI(earthquakes);
        }
    }
}

package com.primedev.kpitracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    ListView listViewLocations;
    Button addLocationButton;
    JSONAdapter mJSONAdapter;

    private static final String QUERY_URL = "https://secure-forest-6434.herokuapp.com/api/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, tempValues);
        */

        mJSONAdapter = new JSONAdapter(this, getLayoutInflater());

        listViewLocations = (ListView) findViewById(R.id.listview_locations);
        listViewLocations.setAdapter(mJSONAdapter);
        listViewLocations.setOnItemClickListener(this);

        //Toast.makeText(getApplicationContext(), "Welcome back!", Toast.LENGTH_LONG).show();

        addLocationButton = (Button) findViewById(R.id.add_location_button);

        addLocationButton.setOnClickListener(this);


        getLocationList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

        // ListView Clicked item index
        int itemPosition = position;

        JSONObject jsonObject = (JSONObject) mJSONAdapter.getItem(position);
        String locationId = jsonObject.optString("id", "didn't work!");
        String locationName = jsonObject.optString("name","Didn't work!");
        String locationAddress = jsonObject.optString("address","Didn't work!");
        String locationZipCode = jsonObject.optString("zip_code","Didn't work!");
        String locationNotes = jsonObject.optString("notes","Didn't work!");

        // ListView Clicked item value
        //String  itemValue = (String) listViewLocations.getItemAtPosition(position);

        // Show Alert
        Toast.makeText(this,
                "Click! "+locationAddress, Toast.LENGTH_LONG)
                .show();

        Intent detailIntent = new Intent(this,DetailActivity.class);

        detailIntent.putExtra("locationId", locationId);
        detailIntent.putExtra("locationName", locationName);
        detailIntent.putExtra("locationAddress", locationAddress);
        detailIntent.putExtra("locationZipCode", locationZipCode);
        detailIntent.putExtra("locationNotes",locationNotes);

        startActivity(detailIntent);


    }

    @Override
    public void onClick(View view) {

        // create an Intent to take you over to a new DetailActivity
        Intent addLocationIntent = new Intent(this, AddLocationActivity.class);

        // pack away the data about the cover
        // into your Intent before you head out
        //detailIntent.putExtra("coverID", coverID);

        startActivity(addLocationIntent);

    }

    public void getLocationList() {

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(QUERY_URL + "locations",
                new JsonHttpResponseHandler() {
                    @Override
                    public void onStart(){
                        Toast.makeText(getApplicationContext(), "Starting Request", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONArray jsonResponse) {

                        // 11. Dismiss the ProgressDialog
                        //mDialog.dismiss();

                        // Display a "Toast" message
                        // to announce your success
                        Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();

                        // update the data in your custom method.

                        if(jsonResponse.length() != 0) {
                            mJSONAdapter.updateData(jsonResponse);
                        }

                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers,
                                          Throwable throwable, org.json.JSONArray errorResponse) {


                        // Display a "Toast" message
                        // to announce the failure
                        Toast.makeText(getApplicationContext(), "Error: " + statusCode + " " + throwable.getMessage(), Toast.LENGTH_LONG).show();

                        // Log error message
                        // to help solve any problems
                        Log.e("kpi_tracker", statusCode + " " + throwable.getMessage());
                    }


                });

    }
}

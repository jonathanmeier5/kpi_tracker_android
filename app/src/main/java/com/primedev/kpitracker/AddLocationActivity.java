package com.primedev.kpitracker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;


public class AddLocationActivity extends ActionBarActivity {

    EditText editLocationTitle;
    EditText editLocationAddress;
    EditText editLocationZipCode;
    EditText editLocationNotes;

    Button addLocationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        //listViewLocations = (ListView) findViewById(R.id.listview_locations);

        editLocationTitle = (EditText) findViewById(R.id.edit_location_title);
        editLocationAddress = (EditText) findViewById(R.id.edit_location_address);

        addLocationButton = (Button) findViewById(R.id.add_location_button);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_location, menu);
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
}

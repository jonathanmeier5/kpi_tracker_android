package com.primedev.kpitracker;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;


public class DetailActivity extends ActionBarActivity {

    TextView locNameView;
    TextView locAddressView;
    TextView locZipCodeView;
    TextView locNotesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        locNameView = (TextView) findViewById(R.id.location_name);
        locAddressView = (TextView) findViewById(R.id.location_address);
        locZipCodeView = (TextView) findViewById(R.id.location_zip_code);
        locNotesView = (TextView) findViewById(R.id.location_notes);

        String locationName = this.getIntent().getExtras().getString("locationName");
        String locationAddress = this.getIntent().getExtras().getString("locationAddress");
        String locationZipCode = this.getIntent().getExtras().getString("locationZipCode");
        String locationNotes = this.getIntent().getExtras().getString("locationNotes");

        Toast.makeText(this,
                locationAddress, Toast.LENGTH_SHORT)
                .show();

        Toast.makeText(this,
                locationZipCode, Toast.LENGTH_SHORT)
                .show();

        Toast.makeText(this,
                locationNotes, Toast.LENGTH_SHORT)
                .show();

        locNameView.setText(locationName);
        locAddressView.setText(locationAddress);
        locZipCodeView.setText(locationZipCode);
        locNotesView.setText(locationNotes);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_detail, menu);
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

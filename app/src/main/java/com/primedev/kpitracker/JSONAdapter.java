package com.primedev.kpitracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

/**
 * Created by house on 1/16/16.
 */
public class JSONAdapter extends BaseAdapter {

    Context mContext;
    LayoutInflater mInflater;
    JSONArray mJsonArray;

    public JSONAdapter(Context context, LayoutInflater inflater) {

        mContext = context;
        mInflater = inflater;
        mJsonArray = new JSONArray();

    }


    @Override
    public int getCount() {
        return mJsonArray.length();
    }

    @Override
    public Object getItem(int position) {
        return mJsonArray.optJSONObject(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {

        ViewHolder holder;

        if (convertView == null) {

            convertView = mInflater.inflate(R.layout.row_location, null);

            holder = new ViewHolder();
            holder.locNameView = (TextView) convertView.findViewById(R.id.location_name);
            holder.locAddressView = (TextView) convertView.findViewById(R.id.location_address);

            convertView.setTag(holder);
        }

        else {

            holder = (ViewHolder) convertView.getTag();
        }

        JSONObject jsonObject = (JSONObject) getItem(position);

        String locationName = "";
        String locationAddress = "";

        if (jsonObject.has("name")) {
            locationName = jsonObject.optString("name");
        }

        if (jsonObject.has("address")) {
            locationAddress = jsonObject.optString("address");
        }

        holder.locNameView.setText(locationName);
        holder.locAddressView.setText(locationAddress);

        return convertView;
    }

    public void updateData(JSONArray jsonArray) {
        // update the adapter's dataset
        mJsonArray = jsonArray;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        public TextView locNameView;
        public TextView locAddressView;
    }
}

/*
 *Course: CT60A2411 Olio-ohjelmointi
 *Date: 29.4.2022
 *Group: Matti Lankinen, Valtteri Lausala, Jan-Peter Kauppinen
 */
package com.example.java_harkkatyo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * <h1>UserComment list adapter</h1>
 * A class meant to help display information from Arraylist<Lake> datatype
 * to listview.
 */
public class AdapterLake extends ArrayAdapter {
    private Activity activity;
    private ArrayList<Lake> lakes;
    private static LayoutInflater inflater = null;

    /**
     * This is the constructor of the class
     * @param activity the activity-class where the adapter will be initialized
     * @param textViewResourceId Resource-id of the listview-layout that will be used
     * @param lakes Arraylist containing the Lake-objects.
     */
    public AdapterLake (Activity activity, int textViewResourceId,ArrayList<Lake> lakes) {
        super(activity, textViewResourceId, lakes);
        try {
            this.activity = activity;
            this.lakes = lakes;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return lakes.size();
    }

    public Object getItem(int position) {
        return lakes.get(position);
    }



    public long getItemId(int position) {
        return position;
    }

    /**
     * Class used for initializing individual list-items displayed
     */
    public static class ViewHolder {
        public TextView lakename;
        public TextView kuntaname;
        public TextView avgDepth;
        public TextView shoreLenght;
        public TextView area;
    }


    /**
     * This method creates individual views for the listview.
     * AdapterComment class runs this automatically after the object creation
     * @param position this is the position of the arraylist-item which is currently created
     * @param convertView
     * @param parent
     * @return
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.rowlayout, null);
                holder = new ViewHolder();

                //viewholder is initialized with the proper TextViews
                holder.lakename = (TextView) vi.findViewById(R.id.lakename);
                holder.kuntaname = (TextView) vi.findViewById(R.id.kuntaname);
                holder.avgDepth = (TextView) vi.findViewById(R.id.avgDepth);
                holder.shoreLenght = (TextView) vi.findViewById(R.id.shorelength);
                holder.area = (TextView) vi.findViewById(R.id.area);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }


            /*Textviews of the list-item are initialized with the
             * information contained in the class*/
            holder.lakename.setText(lakes.get(position).getLakename());
            holder.kuntaname.setText(lakes.get(position).getKuntaname());
            holder.avgDepth.setText( lakes.get(position).getAvgDepth());
            holder.shoreLenght.setText(lakes.get(position).getShoreLength());
            holder.area.setText(lakes.get(position).getArea());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return vi;
    }
}



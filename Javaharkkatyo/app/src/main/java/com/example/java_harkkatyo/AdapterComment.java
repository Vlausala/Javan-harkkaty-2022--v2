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
 * A class meant to help display information from Arraylist<UserComment> datatype
 * to listview.
 */
public class AdapterComment extends ArrayAdapter {

    private Activity activity;
    private ArrayList<UserComment> comments;
    private static LayoutInflater inflater = null;

    /**
     * This is the constructor of the class
     * @param activity The activity-class where the adapter will be initialized
     * @param textViewResourceId Resource-id of the listview-layout that will be used
     * @param comments1 Arraylist containing the UserComment- objects.
     */
    public AdapterComment (Activity activity, int textViewResourceId, ArrayList<UserComment> comments1){
        super(activity, textViewResourceId, comments1);
        try{
            this.activity = activity;
            this.comments = comments1;
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getCount() {
        return comments.size();
    }

    public Object getItem(int position) {
        return comments.get(position);
    }



    public long getItemId(int position) {
        return position;
    }


    /**
     * Class used for initializing individual list-items displayed
     */
    public static class ViewHolder {
        public TextView lakename;
        public TextView comment;
    }

    /**
     * This method creates individual views for the listview.
     * AdapterComment class runs this automatically after the object creation
     * @param position this is the position of the arraylist-item which is currently created
     * @param convertView
     * @param parent
     * @return Returns the list item from the current Arraylist-item
     */
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final AdapterComment.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.rowlayout2, null);
                holder = new ViewHolder();


                //viewholder is initialized with the proper TextViews
                holder.lakename = (TextView) vi.findViewById(R.id.commentLake);
                holder.comment = (TextView) vi.findViewById(R.id.CommentComment);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }
            /*Textviews of the list-item are initialized with the
             * information contained in the class*/
            holder.lakename.setText(comments.get(position).getLakeName());
            holder.comment.setText(comments.get(position).getComment());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return vi;
    }

}

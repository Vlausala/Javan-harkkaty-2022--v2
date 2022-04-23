package com.example.java_harkkatyo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterComment extends ArrayAdapter {

    private Activity activity;
    private ArrayList<UserComment> comments;
    private static LayoutInflater inflater = null;

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



    public static class ViewHolder {
        public TextView lakename;
        public TextView comment;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        final AdapterComment.ViewHolder holder;
        try {
            if (convertView == null) {
                vi = inflater.inflate(R.layout.rowlayout2, null);
                holder = new ViewHolder();

                holder.lakename = (TextView) vi.findViewById(R.id.commentLake);
                holder.comment = (TextView) vi.findViewById(R.id.CommentComment);

                vi.setTag(holder);
            } else {
                holder = (ViewHolder) vi.getTag();
            }

            holder.lakename.setText(comments.get(position).getLakeName());
            holder.comment.setText(comments.get(position).getComment());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return vi;
    }






}

package com.myroom.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myroom.R;

import java.util.List;

/**
 * Created by Jobin on Sep 03.
 */
    public class MembersListAdapter extends ArrayAdapter<String> {
        List<String> data;
        private final Activity context;


        static class ViewHolder {
            public TextView text;
            public ImageView image;
        }

        public MembersListAdapter(Activity context, List<String> names) {
            super(context, R.layout.members_list_row, names);
            this.context = context;
            this.data = names;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View rowView = convertView;
            // reuse views
            if (rowView == null) {
                LayoutInflater inflater = context.getLayoutInflater();
                rowView = inflater.inflate(R.layout.members_list_row, null);
                // configure view holder
                ViewHolder viewHolder = new ViewHolder();
                viewHolder.text = (TextView) rowView.findViewById(R.id.membername);
                viewHolder.image = (ImageView) rowView
                        .findViewById(R.id.img_user);
                rowView.setTag(viewHolder);
            }

            // fill data
            ViewHolder holder = (ViewHolder) rowView.getTag();
            String s = data.get(position);
            holder.text.setText(s);
            return rowView;
        }
    }

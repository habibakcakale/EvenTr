package com.special;

import java.util.ArrayList;

import com.special.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class ListAdapter extends BaseAdapter {
	
	   ViewHolder viewHolder;

        private ArrayList<ListItem> mItems = new ArrayList<ListItem>();
        private Context mContext;
		private int mScreenWidth;

        public ListAdapter(Context context, ArrayList<ListItem> list, Integer screenWidth) {
            mContext = context;
            mItems = list;
            mScreenWidth = screenWidth;           
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            
            if(convertView==null){
                
                // inflate the layout
            	LayoutInflater vi = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.fragment_list_item, null);
                 
                // well set up the ViewHolder
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) v.findViewById(R.id.item_title);
                viewHolder.descr = (TextView) v.findViewById(R.id.item_description);
                viewHolder.image = (ImageView) v.findViewById(R.id.item_image);
                viewHolder.numbertxt = (TextView) v.findViewById(R.id.item_numbertext);
 
                // store the holder with the view.
                v.setTag(viewHolder);
                 
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
                viewHolder.image.setImageResource(0);
            }
            final String item = mItems.get(position).getTitle();
            final String desc = mItems.get(position).getDesc();
            final int imageid = mItems.get(position).getImageId();
            final String number = mItems.get(position).getNr();
            final String numbertext = mItems.get(position).getNrTxt();

            viewHolder.image.setImageResource(imageid);
            viewHolder.title.setText(item);
            viewHolder.descr.setText(desc);
            viewHolder.number.setText(number);
            viewHolder.numbertxt.setText(numbertext);

            return v;
        }
        
        static class ViewHolder {
        	  TextView title;
        	  TextView descr;
        	  TextView number;
        	  TextView numbertxt;
        	  ImageView image;
        	  Bitmap b;
        	  int position;
        }

    }
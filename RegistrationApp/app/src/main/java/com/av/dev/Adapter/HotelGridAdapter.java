package com.av.dev.Adapter;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.av.dev.Activity.MainActivity;
import com.av.dev.Core.AppController;
import com.av.dev.Core.REngine;
import com.av.dev.Core.RSharedPreferences;
import com.av.dev.Core.RSingleton;
import com.av.dev.Fragment.BookDetailsFragment;
import com.av.dev.Object.HotelObject;
import com.av.dev.Object.SharedPreferencesObject;
import com.av.dev.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by CodeSyaona on 06/07/2017.
 */

public class HotelGridAdapter extends RecyclerView.Adapter<HotelGridAdapter.ViewHolder> {

    //    private String[] mDataset;
    private ArrayList<HotelObject> mData = new ArrayList<>();

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextTitle;
        public ImageView mImageBanner;
        public LinearLayout mBackground;


        public ViewHolder(View v) {
            super(v);

            mImageBanner = (ImageView) v.findViewById(R.id.imgbackground);
            mTextTitle = (TextView) v.findViewById(R.id.txtname);
            mBackground = v.findViewById(R.id.gridbackground);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public HotelGridAdapter(ArrayList<HotelObject> myDataset) {
        mData = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public HotelGridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {
        // create a new view
//        TextView v = (TextView) LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.custom_list_hotel, parent, false);
//        // set the view's size, margins, paddings and layout parameters
//
//        ViewHolder vh = new ViewHolder(v);
//        return vh;

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_grid_hotel, parent, false);

        return new ViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final HotelObject row = mData.get(position);
        holder.mTextTitle.setText(row.getHotelName());
        holder.mTextTitle.setTextColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedTextColor)));

        /* set image to banner */
        Picasso.with(AppController.getInstance())
                .load(row.getImgBanner())
                .fit()
                .into(holder.mImageBanner);

        holder.mBackground.setBackgroundColor(Color.parseColor(RSharedPreferences.getSomeStringValue(AppController.getInstance(), SharedPreferencesObject.selectedLayoutBackground)));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RSingleton.setHotelId(row.getHotelId());
                REngine.switchFragment(MainActivity.INSTANCE, new BookDetailsFragment(), MainActivity.INSTANCE.getFrameLayout());

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mData.size();
    }

}

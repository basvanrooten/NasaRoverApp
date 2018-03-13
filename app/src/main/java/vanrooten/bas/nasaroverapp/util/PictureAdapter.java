package vanrooten.bas.nasaroverapp.util;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import vanrooten.bas.nasaroverapp.DetailView;
import vanrooten.bas.nasaroverapp.R;
import vanrooten.bas.nasaroverapp.domain.Picture;

/**
 * Created by Bas van Rooten on 13-3-2018.
 * AVANS HOGESCHOOL 2125132
 */

public class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.ViewHolder> {

    private static final String TAG = "PictureAdapter";
    private ArrayList<Picture> mDataset;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // The View will hold the list row items
        public View view;

        // Introducing the objects who will hold the content
        public TextView mImageID;
        public ImageView mImage;

        public ViewHolder(View view) {
            super(view);
            this.view = view;
            this.view.setOnClickListener(this);

            // Binding mImageID and mImage to the elements in custom_row.xml
            mImageID = (TextView) view.findViewById(R.id.scroll_picture_id);
            mImage = (ImageView) view.findViewById(R.id.scroll_picture);

        }

        @Override
        public void onClick(View view) {

            // When the RecyclerView item is clicked, send the Picture object to the DetailView and show it
            int position = getAdapterPosition();
            Picture picture = mDataset.get(position);

            Intent startDetailView = new Intent(
                    view.getContext().getApplicationContext(),
                    DetailView.class);

            startDetailView.putExtra("PICTURE", picture);

            view.getContext().startActivity(startDetailView);
            Log.d(TAG, "Picture with ID " + picture.getPictureID() + "clicked.. Started DetailView");
        }
    }

    // PictureAdapter Constructor
    public PictureAdapter(Context mContext, ArrayList<Picture> mDataset) {
        this.mContext = mContext;
        this.mDataset = mDataset;
    }



    // Creating new views and returning viewHolder
    @Override
    public PictureAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        Log.d(TAG, "Inflated the custom_row layout into the layoutInflater.");

        return new ViewHolder(view);
    }


    // Override existing content and fill new Data from the mDataset
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.mImageID.setText(mDataset.get(position).getCameraID());
        Log.d(TAG, "ImageID " + mDataset.get(position).getPictureID() + "loaded into the ViewHolder");
        Picasso.get().load(mDataset.get(position).getPictureURL()).into(holder.mImage);
        Log.d(TAG, "Image " + mDataset.get(position).getPictureID() + "loaded into the ViewHolder");

    }


    // Return the size of mDataset
    @Override
    public int getItemCount() {
        return mDataset.size();
    }




}

package vanrooten.bas.nasaroverapp.presentation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import vanrooten.bas.nasaroverapp.R;
import vanrooten.bas.nasaroverapp.api.Key;
import vanrooten.bas.nasaroverapp.api.PictureFetcher;
import vanrooten.bas.nasaroverapp.domain.Picture;
import vanrooten.bas.nasaroverapp.interfaces.OnPictureAvailable;
import vanrooten.bas.nasaroverapp.util.PictureAdapter;

public class MainActivity extends AppCompatActivity implements OnPictureAvailable {
    private static final String TAG = "MainActivity";
    private String selectedCamera = "mast";
    private int page = 1;
    private String mParamsString = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=" + Key.getKey() + "&earth_date=2018-3-1";
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<Picture> mPictureArrayList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate was triggered");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String[] params = { mParamsString };
        PictureFetcher pictureFetcher = new PictureFetcher(this);
        pictureFetcher.execute(params);
        Log.d(TAG, "Initiated PictureFetcher with mParamsString " + mParamsString + " and Key " + Key.getKey());

        mRecyclerView = (RecyclerView) findViewById(R.id.mainListView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        Log.d(TAG, "mPictureArrayList is filled with " + mPictureArrayList.size() + " items");

        mAdapter = new PictureAdapter(this, mPictureArrayList);
        mRecyclerView.setAdapter(mAdapter);
        Log.d(TAG, "Everything is ready for the recyclerView. Here we go :)");


    }

    @Override
    public void onNewPictureAvailable(Picture picture) {
        Log.d(TAG, "onNewPictureAvailable was triggered by PictureID " + picture);
        mPictureArrayList.add(picture);
        Log.d(TAG, "Added Picture " + picture.getPictureID() + " to the mPictureArrayList");
        mAdapter.notifyDataSetChanged();

    }
}

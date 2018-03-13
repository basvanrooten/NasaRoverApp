package vanrooten.bas.nasaroverapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import vanrooten.bas.nasaroverapp.api.Key;
import vanrooten.bas.nasaroverapp.api.PictureFetcher;
import vanrooten.bas.nasaroverapp.domain.Picture;
import vanrooten.bas.nasaroverapp.interfaces.OnPictureAvailable;

public class MainActivity extends AppCompatActivity implements OnPictureAvailable {
    private static final String TAG = "MainActivity";
    private String selectedCamera = "mast";
    private String mParamsString = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&camera=" + selectedCamera + "&api_key=" + Key.getKey() + "&total_photos=100";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate was triggered");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] params = { mParamsString };
        PictureFetcher pictureFetcher = new PictureFetcher(this);

        pictureFetcher.execute(params);
        Log.d(TAG, "Initiated PictureFetcher with mParamsString " + mParamsString + " and Key " + Key.getKey());
    }

    @Override
    public void onNewPictureAvailable(Picture picture) {
        Log.d(TAG, "onNewPictureAvailable was triggered by PictureID " + picture);

    }
}

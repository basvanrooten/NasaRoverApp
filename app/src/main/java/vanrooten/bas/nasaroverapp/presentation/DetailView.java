package vanrooten.bas.nasaroverapp.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import vanrooten.bas.nasaroverapp.R;
import vanrooten.bas.nasaroverapp.domain.Picture;

public class DetailView extends AppCompatActivity {


    private static final String TAG = "DetailView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Picture picture = (Picture) getIntent().getSerializableExtra("PICTURE");

        ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        TextView textView = (TextView) findViewById(R.id.detail_text);

        Picasso.get().load(picture.getPictureURL()).into(imageView);
        textView.setText(picture.getCameraFullName());

    }

}

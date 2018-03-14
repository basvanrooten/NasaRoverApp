package vanrooten.bas.nasaroverapp.presentation;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ontbee.legacyforks.cn.pedant.SweetAlert.SweetAlertDialog;
import com.squareup.picasso.Picasso;

import vanrooten.bas.nasaroverapp.R;
import vanrooten.bas.nasaroverapp.domain.Picture;

public class DetailView extends AppCompatActivity {


    private static final String TAG = "DetailView";
    private SweetAlertDialog pDialog;
    private Picture picture;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        picture = (Picture) getIntent().getSerializableExtra("PICTURE");

        ImageView imageView = (ImageView) findViewById(R.id.detail_image);
        TextView textView = (TextView) findViewById(R.id.detail_text);

        Picasso.get().load(picture.getPictureURL()).into(imageView);
        Log.d(TAG, "Loaded picture " + picture.getPictureID() + " into ImageView.");
        textView.setText(picture.getCameraFullName());

        this.context = this;


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.d(TAG, "FAB is pressed.");
                showDetails();
            }
        });

    }

    public void showDetails() {
        // Creating new Dialog
        pDialog = new SweetAlertDialog(this.context);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#434343"));
        pDialog.getProgressHelper().setRimColor(Color.parseColor("#434343"));
        pDialog.setTitleText("Picture Information");


        // String builder with picture information
        StringBuilder sb = new StringBuilder("Picture ID: " + picture.getPictureID());
        sb.append("\n");
        sb.append("Picture SOL: " + picture.getPictureSol());
        sb.append("\n");
        sb.append("Picture Earth Date: " + picture.getPictureEarthDate());
        sb.append("\n");
        sb.append("Camera ID: " + picture.getCameraID());
        sb.append("\n");
        sb.append("Camera Full Name: " + picture.getCameraFullName());
        sb.append("\n");
        sb.append("Camera Short Name: " + picture.getCameraShortName());

        Log.d(TAG, "Finished building string. Result: " + sb.toString());

        // Set sb output as Dialog undertitle.
        pDialog.setContentText(sb.toString());

        pDialog.setCancelable(true);
        pDialog.show();
    }

}

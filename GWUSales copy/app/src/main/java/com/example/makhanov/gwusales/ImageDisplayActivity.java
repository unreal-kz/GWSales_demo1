package com.example.makhanov.gwusales;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.parse.GetCallback;
import com.parse.GetDataCallback;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ImageDisplayActivity extends AppCompatActivity{

    //private Integer images[];
    //private GoogleApiClient googleClient;
    //private Location lastLocation;
    Button displayButton;
    ImageView image;
    byte myarray[];

   /* protected synchronized void buildGoogleApiClient() {
        googleClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_display);

        displayButton = (Button)findViewById(R.id.buttonDisplay);
        displayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery query = new ParseQuery("Items");
                query.getInBackground("pQSdbGvhel", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {

                        final ParseFile fileObject = (ParseFile) parseObject.get("ItemPic");
                        fileObject.getDataInBackground(new GetDataCallback() {
                            @Override
                            public void done(byte[] bytes, ParseException e) {
                                if (e == null) {
                                    Log.d("test",
                                            "We've got data in data.");
                                    // Decode the Byte[] into
                                    // Bitmap
                                    Bitmap bmp = null;
                                    try {
                                        bmp = BitmapFactory
                                                .decodeByteArray(
                                                        fileObject.getData(), 0,
                                                        fileObject.getData().length);
                                    } catch (ParseException e1) {
                                        e1.printStackTrace();
                                    }

                                    image = (ImageView)findViewById(R.id.imageViewFromParse);
                                    image.setImageBitmap(bmp);
                                } else {
                                    Log.d("test",
                                            "There was a problem downloading the data.");
                                }
                            }
                        });
                    }
                });
            }
        });

    }

    /*@Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    protected void onStart() {
        super.onStart();
        googleClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        googleClient.disconnect();
    }

    @Override
    public void onConnectionSuspended(int i) {

    }*/
}

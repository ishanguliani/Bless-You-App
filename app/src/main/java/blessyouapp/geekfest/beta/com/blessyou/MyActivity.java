package blessyouapp.geekfest.beta.com.blessyou;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ibm.mobile.services.core.IBMBluemix;
import com.ibm.mobile.services.data.IBMData;


public class MyActivity extends Activity implements LocationListener, GoogleMap.OnMarkerClickListener {


    //initialize the IBM SDK variables
    private static final String applicationId = "7643c711-45d1-47ea-b5ed-78f4887b65ed" ;
    private static final String applicationSecret = "136ac50453548c0ae0bb704e73686c26dc5d57ca" ;
    private static final String applicationRoute = "http://testsapp.mybluemix.net" ;

            //current location marker
    Marker TP ;

    //store emergency phone number
//    long number = 9871801898L;
    String number = "09899099233";
    String address = "T-43, Main Road, Near Prime Tower, New Delhi, Delhi 110020" ;
    String police = "01126816677" ;
    //link the buttons
    Button ambulance_btn = null;
    Button hospital_btn = null;
    Button police_btn = null;

    //retrieve the id of the previously pressed tag
    String previous_id = null ;

    //initialize TAG for logging
    private String TAG = "blessyou" ;
    //add marker on the current location
    static final LatLng TutorialsPoint = new LatLng(28.536363 , 77.270563);
    static final LatLng hosp1 = new LatLng(28.540163, 77.271962);
    static final LatLng hosp2 = new LatLng(28.537752, 77.275939);
    static final LatLng hosp3 = new LatLng(28.532873, 77.267010);
    static final LatLng hosp4 = new LatLng(28.539483, 77.259388);

    static final LatLng hosp5 = new LatLng(28.541355, 77.283411);
    static final LatLng hosp6 = new LatLng(28.527488, 77.276388);
    static final LatLng hosp7 = new LatLng(28.528600, 77.279831);
    static final LatLng hosp8 = new LatLng(28.538545, 77.260530);
    static final LatLng hosp9 = new LatLng(28.539399, 77.259313);
    static final LatLng hosp10 = new LatLng(28.540182, 77.258991);

    static final String call1 = "09899099233";
    static final String call2 = "01141727209";
    static final String call3 = "01126460176";
    static final String call4 = "09811030001";
    static final String call5 = "01126925858";
    static final String call6 = "01129942071";
    static final String call7 = "01126817481";
    static final String call8 = "18002081188";
    static final String call9 = "01141654321";
    static final String call10 = "01126430583";

    static final String add1 = "T-43, Main Road, Near Prime Tower, New Delhi, Delhi 110020" ;
    static final String add2 = "B-12/2, 1st Floor, Okhla Industrial Area, Phase-ii, Okhla Industrial Area, New Delhi, Delhi 110020" ;
    static final String add3 = "SRG Colony, Govindpuri, New Delhi, Delhi (state) 110019" ;
    static final String add4 = "J-3/235, D.D.A. Flats, Kalkaji, New Delhi, Delhi 110019" ;
    static final String add5 = "Sarita Vihar, Mathura Road, New Delhi, Delhi 110076" ;
    static final String add6 = "B-1/E-25, Okhla I, Phase I, Okhla Industrial Area, New Delhi, Delhi 110020" ;
    static final String add7 = "c-146, Okhla I, Phase I, Okhla Industrial Area, New Delhi, Delhi 110020" ;
    static final String add8 = "CC-28, Nehru Enclave C Block Kalkaji Road, Kalkaji, New Delhi, Delhi 110019" ;
    static final String add9 = "E-97, Near Deshbandhu College, Kalkaji, Delhi 110019" ;
    static final String add10 = "Near Kalkaji Market, Kalkaji, New Delhi, Delhi 110019" ;






//    static final LatLng hosp11 = new LatLng(28.527488, 77.276388);

    private GoogleMap googleMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //make the activity full screen

       requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activitymain);

        //link buttons
         ambulance_btn = (Button) findViewById(R.id.ambulance_button);
         hospital_btn = (Button) findViewById(R.id.hospital_button);
         police_btn = (Button) findViewById(R.id.police_button);


        //add the initial map marker to the current location
        try {
            if (googleMap == null) {
                googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            }

            googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(28.536363 , 77.270563), 14.0f));

            //set the marker listener
            googleMap.setOnMarkerClickListener(this);

            setMapMarkers("void");

           // setCircleOnMap();

        } catch (Exception e) {
            e.printStackTrace();
        }


        //link the buttons





        // CALLING AMBULANCE
        ambulance_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    //String uri = "tel:"+number;
                    String uri = "tel:"+"102";
                    //String uri = "tel:9871801898";
                    Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
                    startActivity(dialIntent);
                }catch(Exception e) {
                    Toast.makeText(getApplicationContext(), "Your call has failed...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        //CALLING HOSPITAL
        hospital_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    //String uri = "tel:"+number.getText().toString();
                    String uri = "tel:" + number;
                    Intent dialIntent = new Intent(Intent.ACTION_CALL, Uri.parse(uri));
                    startActivity(dialIntent);
                }catch(Exception e) {
                    Toast.makeText(getApplicationContext(),"Your call has failed...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

        //CALLING POLICE
        police_btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    //String uri = "tel:"+number.getText().toString();
                    String uri = "tel:"+police;
                    Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri.parse(uri));
                    startActivity(dialIntent);
                }catch(Exception e) {
                    Toast.makeText(getApplicationContext(),"Your call has failed...",
                            Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });


        //initialiaze the SDK
        IBMBluemix.initialize(MyActivity.this, applicationId, applicationSecret, applicationRoute);
        //IBMCloudCode.initializeService();
        IBMData.initializeService();
        //IBMPush.initializeService();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

          //  googleMap.clear();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


   void setCircleOnMap()    {

       // Instantiates a new CircleOptions object and defines the center and radius
       CircleOptions circleOptions = new CircleOptions()
               .center(new LatLng(28.536363 , 77.270563))
               .radius(1000)
               .fillColor(R.color.white)
               .strokeColor(R.color.DarkMagenta)
               .strokeWidth(7)
               .visible(true); // In meters

// Get back the mutable Circle
       Circle circle = googleMap.addCircle(circleOptions);

       Log.i(TAG, "Circle Set");
   }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public boolean onMarkerClick(Marker marker) {


            Log.i(TAG, "Id is = " + marker.getId()) ;

            setMapMarkers(marker.getId());


            if(!marker.getId().equals("m0".toString())) marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.htick));

        if(marker.getId().equals("m1".toString())) {
            number = call1;
            address = add1 ;
        }
        else if(marker.getId().equals("m2".toString())) {
            number = call2;
            address = add2 ;
        }
        else if(marker.getId().equals("m3".toString())) {
            number = call3;
            address = add3 ;
        }
        else if(marker.getId().equals("m4".toString())) {
            number = call4;
            address = add4 ;
        }
        else if(marker.getId().equals("m5".toString())) {
            number = call5;
            address = add5 ;
        }
        else if(marker.getId().equals("m6".toString())) {
            number = call6;
            address = add6 ;
        }
        else if(marker.getId().equals("m7".toString())) {
            number = call7;
            address = add7 ;
        }
        else if(marker.getId().equals("m8".toString())) {
            number = call8;
            address = add8 ;
        }
        else if(marker.getId().equals("m9".toString())) {
            number = call9;
            address = add9;
        }
        else if(marker.getId().equals("m10".toString())) {
            number = call10;
            address = add10 ;
        }

        return false;
    }


    void setMapMarkers(String key)    {


        //id = 0
        Marker TP = googleMap.addMarker(new MarkerOptions().
                position(TutorialsPoint).
                title("My Location").
                snippet("Site of Accident")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.here))
                .visible(true));
        TP.showInfoWindow();


        //jeewan jyoti 1
        Marker TP1 = googleMap.addMarker(new MarkerOptions().
                position(hosp1)
                .title("Jeewan Jyoti Hospital")
                .snippet("600metres[*Nearest]")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.near))
                .visible(true));

        TP1.showInfoWindow();

        //Alcon Meditech 2
        Marker TP2 = googleMap.addMarker(new MarkerOptions().
                position(hosp2)
                .title("Alcon Meditech I Private Limited")
                .snippet("1100metres")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.h)).
                        visible(true));

        //Ballabh 3
        Marker TP3 = googleMap.addMarker(new MarkerOptions().
                position(hosp3)
                .title("Ballabh Hospital")
                .snippet("700metres")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.h))
                .visible(true));



        //I&D 4
        Marker TP4 = googleMap.addMarker(new MarkerOptions().
                position(hosp4)
                .title("I&D Hospital Solution")
                .snippet("1500metres")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.h)).
                        visible(true));

        //Indraprastha 5
        Marker TP5 = googleMap.addMarker(new MarkerOptions().
                position(hosp5)
                .title("Indraprastha Apollo Hospitals")
                .snippet("2600metres")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.h)).
                        visible(true));

        //Aeon 6
        Marker TP6 = googleMap.addMarker(new MarkerOptions().
                position(hosp6)
                .title("Aeon Medical Private Limited")
                .snippet("1800metres")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.h)).
                        visible(true));

        //Aastra 7
        Marker TP7 = googleMap.addMarker(new MarkerOptions().
                position(hosp7)
                .title("Astra Zeneca Pharma Limited")
                .snippet("1700metres")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.h)).
                        visible(true));

        //Aapka 8
        Marker TP8 = googleMap.addMarker(new MarkerOptions().
                position(hosp8)
                .title("Aapka Urgicare Pvt. Ltd.")
                .snippet("1400metres")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.h)).
                        visible(true));

        //Tech 9
        Marker TP9 = googleMap.addMarker(new MarkerOptions().
                position(hosp9)
                .title("Tech HealthCare Solutions Pvt. Ltd.")
                .snippet("1500metres")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.h)).
                        visible(true));

        //ESI 10
        Marker TP10 = googleMap.addMarker(new MarkerOptions().
                position(hosp10)
                .title("ESI Dispensary")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.h))
                .snippet("1600metres").
                        visible(true));



    }

}

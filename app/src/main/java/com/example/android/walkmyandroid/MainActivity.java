/*
 * Copyright 2017 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.walkmyandroid;

import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.pm.PackageManager;
// import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
// import com.google.android.gms.tasks.OnSuccessListener;

/* DOC STEP 4.1 - Set up the UI and method stubs
If your app relies heavily on device location, using the getLastLocation()
method may not be sufficient, because getLastLocation() relies on a location
request from a different app and only returns the last value stored in the
provider.

The user has no way of knowing that the app is making location requests, except
for a tiny icon in the status bar. In this step, you use an animation (included
in the starter code) to add a more obvious visual cue that the device's location
is being tracked. You also change the button text to show the user whether
location tracking is on or off.
DOC END STEP 4.1 */

public class MainActivity extends AppCompatActivity
    // TODO STEP 3.3.5
    implements FetchAddressTask.OnTaskCompleted
    // TODO END STEP 3.3.5
{
    private static final String TAG = MainActivity.class.getSimpleName();

    // TODO STEP 4.5.2
    private static final String TRACKING_LOCATION_KEY = "TRACKING_LOCATION_KEY";
    // TODO END STEP 4.5.2

    // TODO STEP 2.2.1a
    private Button mLocationButton;
    // TODO END STEP 2.2.1a

    /* DOC STEP 2.2.4
    This constant is used to identify the permission request when the results
    come back in the onRequestPemissionsResult() method. It can be any integer
    greater than 0.
    DOC END STEP 2.2.4 */
    // TODO STEP 2.2.4
    private static final int REQUEST_LOCATION_PERMISSION = 101;
    // TODO END STEP 2.2.4

    // TODO STEP 2.3.2
    /* DOC STEP 3.3.7a
    This has been replaced by STEP 3.3.7
    DOC END STEP 3.3.7a */
    // private Location mLastLocation;
    private TextView mLocationTextView;
    // TODO END STEP 2.3.2

    // TODO STEP 2.3.4
    private FusedLocationProviderClient mFusedLocationClient;
    // TODO END STEP 2.3.4

    // TODO STEP 4.1.1
    private AnimatorSet mRotateAnim;
    // TODO END STEP 4.1.1

    // TODO STEP 4.1.8a
    private boolean mTrackingLocation = false;
    // TODO END STEP 4.1.8a

    // TODO STEP 4.3a
    private LocationCallback mLocationCallback;
    // TODO END STEP 4.3a

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO STEP 2.2.1b
        mLocationButton = findViewById(R.id.button_location);
        mLocationButton.setOnClickListener(new View.OnClickListener() {

            /* DOC STEP 4.1.8b
            his has been replaced by STEP 3.3.7
            DOC END STEP 4.1.8b */
//            // TODO STEP 2.2.2
//            @Override
//            public void onClick(View view) {
//                getLocation();
//            }
//            // TODO END STEP 2.2.2

            // TODO STEP 4.1.8b
            @Override
            public void onClick(View view) {
                if (!mTrackingLocation) {
                    startTrackingLocation();
                } else {
                    stopTrackingLocation();
                }
            }
            // TODO END STEP 4.1.8b
        });
        // TODO END STEP 2.2.1b

        // TODO STEP 2.3.3
        mLocationTextView = findViewById(R.id.textview_location);
        // TODO END STEP 2.3.3

        // TODO STEP 2.3.5
        mFusedLocationClient =
                LocationServices.getFusedLocationProviderClient(this);
        // TODO END STEP 2.3.5

        // TODO STEP 4.1.2
        ImageView androidImageView = findViewById(R.id.imageview_android);
        mRotateAnim = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.rotate);
        mRotateAnim.setTarget(androidImageView);
        // TODO END STEP 4.1.2

        /* DOC STEP 4.3 - Create the LocationCallback object
        When your app requests a location update, the fused location provider
        invokes the LocationCallback.onLocationResult() callback method. The
        incoming argument contains a list of Location objects containing the
        location's latitude and longitude.
        DOC END STEP 4.3. */

        // TODO STEP 4.3b
        mLocationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                // TODO STEP 4.4.3
                // If tracking is turned on, reverse geocode into an address
                if (mTrackingLocation) {
                    new FetchAddressTask(MainActivity.this, MainActivity.this)
                            .execute(locationResult.getLastLocation());
                }
                // TODO END STEP 4.4.3
            }
        };
        // TODO END STEP 4.3b

        // TODO STEP 4.5.4
        if (savedInstanceState != null) {
            mTrackingLocation = savedInstanceState.getBoolean(
                    TRACKING_LOCATION_KEY);
        }
        // TODO END STEP 4.5.4
    }

    /* DOC STEP 4.4.6/7/8
    Right now, the app continues to request location updates until the user
    clicks the button, or until the Activity is destroyed. To conserve power,
    stop location updates when your Activity is not in focus (in the paused
    state) and resume location updates when the Activity regains focus.
    DOC END STEP 4.4.6/7/8 */

    // TODO STEP 4.4.6
    @Override
    protected void onResume() {
        super.onResume();

        // TODO STEP 4.4.7
        if (mTrackingLocation) {
            startTrackingLocation();
        }
        // TODO END STEP 4.4.7
    }

    @Override
    protected void onPause() {
        // TODO STEP 4.4.8
        if (mTrackingLocation) {
            stopTrackingLocation();
            mTrackingLocation = true;
        }
        // TODO END STEP 4.4.8

        super.onPause();
    }
    // TODO END STEP 4.4.6

    /* DOC STEP 4.5 - Make the tracking state persistent
    If you run the app and rotate the device, the app resets to its initial
    state. The mTrackingLocation boolean is not persistent across configuration
    changes, and it defaults to false when the Activity is recreated. This means
    the UI defaults to the initial state.

    In this step, you use the saved instance state to make mTrackingLocation
    persistent so that the app continues to track location when there is a
    configuration change.
    DOC END STEP 4.5 */

    // TODO STEP 4.5.1
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // TODO STEP 4.5.3
        outState.putBoolean(TRACKING_LOCATION_KEY, mTrackingLocation);
        // TODO END STEP 4.5.3
        super.onSaveInstanceState(outState);
    }
    // TODO END STEP 4.5.1

    /* DOC STEP 2.2 - Request permission at runtime
    Starting with Android 6.0 (API level 23), it's not always enough to include
    a permission statement in the manifest. For "dangerous" permissions, you
    also have to request permission programmatically, at runtime.
    DOC END STEP 2.2 */

    /* DOC STEP 4.1.5a
    This has been replaced by STEP 4.1.5
    DOC END STEP 4.1.5a */
//    // TODO STEP 2.2.3
//    private void getLocation() {
//        /* DOC STEP 2.2.3
//        Check for the ACCESS_FINE_LOCATION permission.
//        DOC END STEP 2.2.3 */
//
//        if (ActivityCompat.checkSelfPermission(this,
//                Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]
//                            {Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQUEST_LOCATION_PERMISSION);
//        } else {
//            Log.d(TAG, "getLocation: permissions granted");
//
//            /* DOC STEP 2.3 - Get the last known location
//            The getLastLocation() method doesn't actually make a location
//            request. It simply returns the location most recently obtained by
//            the FusedLocationProviderClient class.
//
//            If no location has been obtained since the device was restarted, the
//            getLastLocation() method may return null. Usually, the
//            getLastLocation() method returns a Location object that contains a
//            timestamp of when this location was obtained.
//            DOC END STEP 2.3 */
//
//            /* DOC STEP 2.3.6
//            The getLastLocation() method returns a Task that results in a
//            Location object (after the Task's onSuccess() callback method is
//            called, signifying that the Task was successful).
//            DOC END STEP 2.3.6 */
//
//            // TODO STEP 2.3.6
//            mFusedLocationClient.getLastLocation().addOnSuccessListener(
//                    new OnSuccessListener<Location>() {
//                        @Override
//                        public void onSuccess(Location location) {
//                            if (location != null) {
//                                /* DOC STEP 3.3.7b
//                                This has been replaced by STEP 3.3.7
//                                DOC END STEP 3.3.7b */
////                                mLastLocation = location;
////                                mLocationTextView.setText(
////                                        getString(R.string.location_text,
////                                                mLastLocation.getLatitude(),
////                                                mLastLocation.getLongitude(),
////                                                mLastLocation.getTime()));
//                                // TODO STEP 3.3.7
//                                // Start the reverse geocode AsyncTask
//                                new FetchAddressTask(MainActivity.this,
//                                        MainActivity.this).execute(location);
//                                // TODO END STEP 3.3.7
//                            } else {
//                                /* DOC STEP 2.4 - Testing location on an
//                                   emulator
//                                When you test the app on an emulator, the
//                                getLastLocation() method might return null,
//                                because the fused location provider doesn't
//                                update the location cache after the device is
//                                restarted.
//                                DOC END STEP 2.4 */
//                                mLocationTextView.setText(R.string.no_location);
//                            }
//                        }
//                    });
//            // TODO END STEP 2.3.6
//            // TODO STEP 3.3.8
//            mLocationTextView.setText(getString(R.string.address_text,
//                    getString(R.string.loading), System.currentTimeMillis()));
//            // TODO END STEP 3.3.8
//        }
//    }
//    // TODO END STEP 2.2.3

    // TODO STEP 4.1.5a
    private void startTrackingLocation() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_LOCATION_PERMISSION);
        } else {
            Log.d(TAG, "getLocation: permissions granted");

            // TODO STEP 4.4.1
            mFusedLocationClient.requestLocationUpdates(getLocationRequest(),
                    mLocationCallback, null /* Looper */);
            // TODO END STEP 4.4.1

            // TODO STEP 4.1.9
            mTrackingLocation = true;
            mRotateAnim.start();
            mLocationButton.setText(R.string.stop_tracking_location);
            // TODO END STEP 4.1.9
        }
    }
    // TODO END STEP 4.1.5a

    // TODO STEP 4.1.6
    /**
     * Method that stops tracking the device. It removes the location
     * updates, stops the animation and reset the UI.
     */
    private void stopTrackingLocation() {
        // TODO STEP 4.1.10
        if (mTrackingLocation) {
            // TODO STEP 4.4.2
            mFusedLocationClient.removeLocationUpdates(mLocationCallback);
            // TODO END STEP 4.4.2

            mTrackingLocation = false;
            mLocationButton.setText(R.string.start_tracking_location);
            mLocationTextView.setText(R.string.textview_hint);
            mRotateAnim.end();
        }
        // TODO END STEP 4.1.10
    }
    // TODO END STEP 4.1.6

    // TODO STEP 2.2.5
    @Override
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public void onRequestPermissionsResult(int requestCode,
            @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
        case REQUEST_LOCATION_PERMISSION:
            // If the permission is granted, get the location,
            // otherwise, show a Toast
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                /* DOC STEP 4.1.5b
                This has been replaced by STEP 4.1.5b
                DOC END STEP 4.1.5b */
//                getLocation();
                // TODO STEP 4.1.5b
                startTrackingLocation();
                // TODO END STEP 4.1.5b
            } else {
                Toast.makeText(this,
                        R.string.location_permission_denied,
                        Toast.LENGTH_SHORT).show();
            }
            break;
        }
    }
    // TODO END STEP 2.2.5

    // TODO STEP 3.3.6
    @Override
    public void onTaskCompleted(String result) {
        // TODO STEP 4.4.4
        if (!mTrackingLocation) {
            return;
        }
        // TODO END STEP 4.4.4

        // Update the UI
        mLocationTextView.setText(getString(R.string.address_text,
                result, System.currentTimeMillis()));
    }
    // TODO END STEP 3.3.6

    /* DOC STEP 4.2 - Create the LocationRequest object
    The LocationRequest object contains setter methods that determine the
    frequency and accuracy of location updates.
    DOC END STEP 4.2 */

    // TODO STEP 4.2.
    private LocationRequest getLocationRequest() {
        LocationRequest locationRequest = new LocationRequest();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        return locationRequest;
    }
    // TODO END STEP 4.2
}

package com.example.android.walkmyandroid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

/* DOC STEP 3 - Get the location as an address
Although latitude and longitude are useful for calculating distance or
displaying a map position, in many cases the address of the location is more
useful.

The process of converting from latitude/longitude to a physical address is
called reverse geocoding. The getFromLocation() method provided by the Geocoder
class accepts a latitude and longitude and returns a list of addresses. The
method is synchronous and requires a network connection. It may take a long time
to do its work, so do not call it from the main user interface (UI) thread of
your app.
DOC END STEP 3 */

/* DOC STEP 3.1
In this task, you subclass an AsyncTask to perform reverse geocoding off the
main thread. When the AsyncTask completes its process, it updates the UI in the
onPostExecute() method.

Using an AsyncTask means that when your main Activity is destroyed when the
device orientation changes, you will not longer be able to update the UI.
DOC END STEP 3.1 */

// TODO STEP 3.1 - Create an AsyncTask subclass
public class FetchAddressTask extends AsyncTask<Location, Void, String> {
    private final String TAG = FetchAddressTask.class.getSimpleName();

    @SuppressLint("StaticFieldLeak")
    private Context mContext;

    // TODO STEP 3.3.3
    private OnTaskCompleted mListener;
    // TODO STEP 3.3.3

    FetchAddressTask(Context applicationContext,
            // TODO STEP 3.3.3b
            OnTaskCompleted listener
            // TODO END STEP 3.3.3b
    ) {
        mContext = applicationContext;

        // TODO STEP 3.3.3c
        mListener = listener;
        // TODO END STEP 3.3.3c
    }

    @Override
    protected String doInBackground(Location... params) {
        // TODO STEP 3.2.1
        Geocoder geocoder = new Geocoder(mContext, Locale.getDefault());
        // TODO END STEP 3.2.1

        // TODO STEP 3.2.2
        Location location = params[0];
        // TODO END STEP 3.2.2

        // TODO STEP 3.2.3
        List<Address> addresses;
        String resultMessage;
        // TODO END STEP 3.2.3

        // TODO STEP 3.2.4
        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    // In this sample, get just a single address
                    1);

            /* DOC STEP 3.2.7
            You need to catch the case where Geocoder is not able to find the
            address for the given coordinates.
            DOC END STEP 3.2.7 */

            // TODO STEP 3.2.7
            if (addresses == null || addresses.size() == 0) {
                resultMessage = mContext.getString(R.string.no_address_found);
                Log.e(TAG, resultMessage);
            }
            // TODO END STEP 3.2.7

            // TODO STEP 3.2.8
            else {
                // If an address is found, read it into resultMessage
                Address address = addresses.get(0);
                ArrayList<String> addressParts = new ArrayList<>();

                // Fetch the address lines using getAddressLine,
                // join them, and send them to the thread
                for (int i = 0; i <= address.getMaxAddressLineIndex(); i++) {
                    addressParts.add(address.getAddressLine(i));
                }

                resultMessage = TextUtils.join("\n", addressParts);
            }
            // TODO END 3.2.8
        }
        // TODO END STEP 3.2.4

        /* DOC STEP 3.2.5
        IOException exceptions are thrown if there is a network error or a
        problem with the Geocoder service.
        DOC END STEP 3.2.5 */

        // TODO STEP 3.2.5
        catch (IOException ioException) {
            // Catch network or other I/O problems
            resultMessage = mContext
                    .getString(R.string.service_not_available);
            Log.e(TAG, resultMessage, ioException);
        }
        // TODO END STEP 3.2.5

        // TODO STEP 3.2.6
        catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values
            resultMessage = mContext
                    .getString(R.string.invalid_lat_long_used);
            Log.e(TAG, resultMessage + ". " +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }
        // TODO END STEP 3.2.6

        // TODO STEP 3.2.9
        return resultMessage;
        // TODO END STEP 3.2.9
    }

    @Override
    protected void onPostExecute(String address) {
        // TODO STEP 3.3.4
        mListener.onTaskCompleted(address);
        // TODO END STEP 3.3.4
        super.onPostExecute(address);
    }

    // TODO STEP 3.3.2
    public interface OnTaskCompleted {
        void onTaskCompleted(String result);
    }
    // TODO END STEP 3.3.2
}
// TODO END STEP 3.1
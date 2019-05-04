package dim.aviv.moviesapp;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.gson.Gson;
import com.google.zxing.Result;

import org.json.JSONObject;

import java.util.ArrayList;

import dim.aviv.moviesapp.models.TheMovie;
import dim.aviv.moviesapp.persistence.TheMovieRepository;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

/**
 * Created by אביב on 03/05/2019.
 */

public class SimpleScannerActivity extends Activity implements ZXingScannerView.ResultHandler {
    private ZXingScannerView mScannerView;

    private static final int VERIFY_PERMISSIONS_REQUEST = 1;
    private static final String TAG = "SimpleScannerActivity";
    private TheMovieRepository mMovieRepository;
    private ArrayList<String> test = new ArrayList<String>();
    private String whattt;
    private int count = 0;



    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        mScannerView = new ZXingScannerView(this);   // Programmatically initialize the scanner view
        setContentView(mScannerView);                // Set the scanner view as the content view


        if(checkPermissionsArray(Permissions.PERMISSIONS)){
        //    setupViewPager();

            Log.d(TAG, "onCreate:  gogogogo");

        }else{
            verifyPermissions(Permissions.PERMISSIONS);
        }
        //     setupBottomNavigationView();





        if(checkPermissions(Permissions.CAMERA_PERMISSION[0])){

            Log.d(TAG, "onCreate: 00000");
        }else{
            Log.d(TAG, "onCreate: 1111111111");
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
        mScannerView.startCamera();          // Start camera on resume
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();           // Stop camera on pause
    }

    @Override
    public void handleResult(Result rawResult) {
        // Do something with the result here
        Log.v(TAG, rawResult.getText()); // Prints scan results
        Log.v(TAG, rawResult.getBarcodeFormat().toString()); // Prints the scan format (qrcode, pdf417 etc.)

//         test = getIntent().getStringArrayListExtra("nameslist");
//        mMovieRepository= new TheMovieRepository(this);
//         whattt = rawResult.getText();
//
//        Log.d(TAG, "onCreate: what" + whattt);
//
//        try{
//        try {
//
//            JSONObject obj = new JSONObject(whattt);
//
//            Log.d("My App", obj.toString());
//
//            Gson gson = new Gson();
//            TheMovie themovie = gson.fromJson(obj.toString(), TheMovie.class);
//
//
//            for(int i=0;i<test.size();i++){
//                if (themovie.getTitle() == test.get(i)){
//                    count++;
//                }
//            }
//            if(count==0){
//                mMovieRepository.insertMovieTask(themovie);
//            }
//
//
//            Log.d(TAG, "onCreate: themoviee" + themovie.toString());
//        } catch (Throwable t) {
//            Log.e("My App", "Could not parse malformed JSON: \"" + whattt + "\"");
//        }
//
//        //    tempmovie = new TheMovie(whattt);
//
//        //       Log.d(TAG, "onCreate: wow!!" + tempmovie.toString());
//        //     Log.d(TAG, "onCreate: wow!!" + tempmovie);
//
//    }catch (RuntimeException e){
//        Log.e(TAG, "onCreate: RuntimeException" + e.getMessage() );
//    }


     //
//        for(Details details: String.valueOf(r)) {
//
//        }
       // TheMovie trymovie = new TheMovie(rawResult.getText().getT);

        Intent intent = new Intent(SimpleScannerActivity.this, MovieListActivity.class);
//        Bundle args = new Bundle();
//        args.putString("result",rawResult.getText());
        intent.putExtra("result", rawResult.getText());

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        // If you would like to resume scanning, call this method below:
      //  mScannerView.resumeCameraPreview(this);
    }

    public boolean checkPermissionsArray(String [] permissions){

        for(int i=0; i<permissions.length;i++){
            String check = permissions[i];
            if(!checkPermissions(check)){
                return false;
            }
        }
        return true;
    }

    public void verifyPermissions(String[] permissions){

        ActivityCompat.requestPermissions(
                SimpleScannerActivity.this,
                permissions,
                VERIFY_PERMISSIONS_REQUEST
        );
    }

    public boolean checkPermissions(String permission){
        int permissionRequest = ActivityCompat.checkSelfPermission(SimpleScannerActivity.this,permission);

        if(permissionRequest != PackageManager.PERMISSION_GRANTED){
            return false;
        }
        else{
            return true;
        }
    }
}

package dim.aviv.moviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;





/**
 * Created by אביב on 02/05/2019.
 */

public class TheScanner extends AppCompatActivity {


    private static final String TAG = "TheScanner";
    private String scanContent, scanFormat;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






//        Log.d(TAG, "onCreate: blablalbla");
//        IntentIntegrator scanIntegrator = new IntentIntegrator(TheScanner.this);
//        scanIntegrator.setPrompt("Scan");
//        scanIntegrator.setBeepEnabled(true);
//        //The following line if you want QR code
//        scanIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
//        scanIntegrator.setCaptureActivity(SplashActivity.class);
//        scanIntegrator.setOrientationLocked(true);
//        scanIntegrator.setBarcodeImageEnabled(true);
//        scanIntegrator.initiateScan();
//
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (scanningResult != null) {
//            if (scanningResult.getContents() != null) {
//                scanContent = scanningResult.getContents().toString();
//                scanFormat = scanningResult.getFormatName().toString();
//            }
//
//            Toast.makeText(this,scanContent+"   type:"+scanFormat,Toast.LENGTH_SHORT).show();
//
//        }else{
//            Toast.makeText(this,"Nothing scanned",Toast.LENGTH_SHORT).show();
//        }
//    }
//}



    }
}
//        try {
//
//
//            Log.d(TAG, "onCreate: bobobo");
//            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
//            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes
//
//            startActivityForResult(intent, 0);
//
//        } catch (Exception e) {
//
//            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
//            Intent marketIntent = new Intent(Intent.ACTION_VIEW,marketUri);
//            startActivity(marketIntent);
//
//        }
//    }
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
////        Bitmap bitmap;
////        bitmap = (Bitmap) data.getExtras().get("data");
//        if (requestCode == 0) {
//
//            if (resultCode == RESULT_OK) {
//                String contents = data.getStringExtra("SCAN_RESULT");
//                Log.d(TAG, "onActivityResult: " + contents.toString());
//                Log.d(TAG, "onActivityResult: " + contents);
//                Intent intent = new Intent(TheScanner.this, MovieListActivity.class);
//                intent.putExtra("Scan_result", contents);
//                startActivity(intent);
//            }
//            if(resultCode == RESULT_CANCELED){
//                //handle cancel
//            }
//        }
//    }
//
//}


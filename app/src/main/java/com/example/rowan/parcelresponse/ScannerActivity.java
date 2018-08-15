package com.example.rowan.parcelresponse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView scannerView;
    private static final String TAG = "ScannerActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(ScannerActivity.this);
        setContentView(scannerView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(ScannerActivity.this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {
      //  Intent intent = new Intent(ScannerActivity.this,TrackActivity.class);

       // intent.putExtra("code",result.getText());

     //   startActivity(intent);

        Log.d(TAG, "handleResult: "+result.getText());
        ApiCall.getId(ScannerActivity.this,result.getText());


        scannerView.resumeCameraPreview(ScannerActivity.this);
        finish();
    }
}

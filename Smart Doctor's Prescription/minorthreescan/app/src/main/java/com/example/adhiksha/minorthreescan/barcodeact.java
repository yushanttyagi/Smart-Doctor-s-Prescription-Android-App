package com.example.adhiksha.minorthreescan;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class barcodeact extends AppCompatActivity implements ZXingScannerView.ResultHandler {
ZXingScannerView zXingScannerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_barcodeact);
        zXingScannerView=new ZXingScannerView(this);
        setContentView(zXingScannerView);
    }

    @Override
    public void handleResult(Result result) {
        MainActivity.textView.setText(result.getText());
        onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }
}

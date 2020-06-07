package com.example.adhiksha.minorthreescan;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    SurfaceView surfaceView; public static TextView textView;
    SparseArray<Barcode> barcodeSparseArray;
    CameraSource cameraSource;
    BarcodeDetector barcodeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView) findViewById(R.id.textView);
        Button button=(Button) findViewById(R.id.button);
        Button button2=(Button) findViewById(R.id.button2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),barcodeact.class);
                startActivity(intent);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(textView.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"no link found",Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(textView.getText().toString()));
                    startActivity(i);
                }
            }
        });
    }
}

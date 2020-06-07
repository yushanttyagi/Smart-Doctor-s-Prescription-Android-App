package com.example.adhiksha.minorthree;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class qrimage extends AppCompatActivity {
Button button4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrimage);
        Intent intent=getIntent();
        final String mess=intent.getStringExtra("nameofqr");
        final ImageView imageView=(ImageView) findViewById(R.id.imageView);
        button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //button.setVisibility(View.INVISIBLE);
                //button3.setVisibility(View.INVISIBLE);
                //textView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.VISIBLE);
                if(mess!=null) {
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();
                    BitMatrix bitMatrix = null;
                    try {
                        bitMatrix = multiFormatWriter.encode(mess.trim(), BarcodeFormat.QR_CODE, 500, 500);

                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitMap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitMap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}

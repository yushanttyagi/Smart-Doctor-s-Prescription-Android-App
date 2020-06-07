package com.example.adhiksha.minorthree;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class detailper extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailper);
        //Intent intent=getIntent();
        //String url = intent.getStringExtra("details");
        Button button=(Button) findViewById(R.id.button6);
        TextView textView=(TextView) findViewById(R.id.textView);
        textView.setText(MainActivity.details.get(detailsact.a));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW,Uri.parse(MainActivity.address.get(detailsact.a).toString()));
                startActivity(i);
            }

        });
    }
}

package com.example.adhiksha.minorthree;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.io.DataOutput;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button button,fetch,button2,button3,button7,button8;private MediaRecorder recorder;EditText editText1,editText2;
    TextView textView;boolean flagg=false;
    private String filename=null;private static  final String LOG_TAG="Record_log";
    StorageReference reference;ProgressDialog progressDialog;
    public static ArrayList<String> names=new ArrayList<String>();SharedPreferences sharedPreferences;
    public static ArrayList<String> details=new ArrayList<String>();
    public static ArrayList<String> address=new ArrayList<String>();
    public static  String audio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button=(Button) findViewById(R.id.button);
        button8=(Button) findViewById(R.id.button8);
        button2=(Button) findViewById(R.id.button2);
        button3=(Button) findViewById(R.id.button3);
        button7=(Button) findViewById(R.id.button7);
        textView=(TextView) findViewById(R.id.textView2);
        textView.setSelected(true);
        editText1=findViewById(R.id.editText);
        editText2=findViewById(R.id.editText3);
        reference=FirebaseStorage.getInstance().getReference();
        progressDialog=new ProgressDialog(MainActivity.this);
        filename=Environment.getExternalStorageDirectory().getAbsolutePath();
        filename+="/recorded_ytt.mp3";
        Random random=new Random();
        int abc=random.nextInt(10001-0)+0;
        editText1.setText(String.valueOf(abc));
       /* button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction()==MotionEvent.ACTION_DOWN){
                    Toast.makeText(getApplicationContext(),"started recording",Toast.LENGTH_SHORT).show();
                    startRecording();
                }else if(event.getAction()==MotionEvent.ACTION_UP){
                    Toast.makeText(getApplicationContext(),"stopped recording",Toast.LENGTH_SHORT).show();
                    stopRecording();
                }
                return false;
            }
        });*/
       editText2.setText("Name:\nAge:\nGender:\nAddress:");
       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               boolean flag=false;
               if (editText1.getText().toString().isEmpty()){
                   Toast.makeText(getApplicationContext(), "please enter a patient id", Toast.LENGTH_SHORT).show();
               }
               else {
                   for (int i = 0; i < names.size(); i++) {
                       if (names.get(i).equals(editText1.getText().toString())) {
                           flag=true;Toast.makeText(getApplicationContext(), "patient id already exists", Toast.LENGTH_SHORT).show();
                           break;
                       }
                   }
                   if(!flag){
                       flagg=true;
                       Toast.makeText(getApplicationContext(), "started recording", Toast.LENGTH_SHORT).show();
                       startRecording();
                   }
               }
//               Toast.makeText(getApplicationContext(), "started recording", Toast.LENGTH_SHORT).show();
//               startRecording();
           }
       });
       button8.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(flagg) {
                   flagg=false;
                   Toast.makeText(getApplicationContext(), "stopped recording", Toast.LENGTH_SHORT).show();
                   stopRecording();
               }
               else{
                   Toast.makeText(getApplicationContext(), "please start the recording first", Toast.LENGTH_SHORT).show();
               }
           }
       });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(MainActivity.this,qrimage.class);
                intent.putExtra("nameofqr",audio);
                startActivity(intent);
            }
        });

        //address.add("this is address");
        //names.add("yushant");
        //details.add("age:19\nsex:male\naddress:new delhi");
        sharedPreferences=this.getSharedPreferences("com.example.sharedpreferences",Context.MODE_PRIVATE);

        /*try {
            sharedPreferences.edit().putString("names",sharedpreferences.serialize(names)).apply();
            sharedPreferences.edit().putString("details",sharedpreferences.serialize(details)).apply();
            sharedPreferences.edit().putString("addr",sharedpreferences.serialize(address)).apply();

        } catch (IOException e) {
            e.printStackTrace();
        }*/
        address.add("hello");
        try {
            names=(ArrayList<String>) sharedpreferences.deserialize(sharedPreferences.getString("names",sharedpreferences.serialize(new ArrayList<>())));
            details=(ArrayList<String>) sharedpreferences.deserialize(sharedPreferences.getString("details",sharedpreferences.serialize(new ArrayList<>())));
            address=(ArrayList<String>) sharedpreferences.deserialize(sharedPreferences.getString("addr",sharedpreferences.serialize(new ArrayList<>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("nameee is ",names.toString());
        Log.i("details are ",details.toString());
        Log.i("address are ",address.toString());
        //address.add("hell");
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean flag=false;
                if (editText1.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "please enter a patient id", Toast.LENGTH_SHORT).show();
                }
                else {
                    for (int i = 0; i < names.size(); i++) {
                        if (names.get(i).equals(editText1.getText().toString())) {
                            flag=true;Toast.makeText(getApplicationContext(), "patient id already exists", Toast.LENGTH_SHORT).show();
                            break;
                        }
                    }
                    if(!flag){
                        String name=editText1.getText().toString();
                        String detail=editText2.getText().toString();
                        names.add(name);details.add(detail);
                        try {
                            sharedPreferences.edit().putString("names",sharedpreferences.serialize(names)).apply();
                            sharedPreferences.edit().putString("details",sharedpreferences.serialize(details)).apply();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(MainActivity.this,"entered successfully",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,detailsact.class);
                startActivity(intent);
            }
        });
    }

    private void startRecording() {

        recorder = new MediaRecorder();
        recorder.reset();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setOutputFile(filename);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);

        try {
            recorder.prepare();
            recorder.start();

        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }


    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
        upload();
    }
    public void upload(){
        progressDialog.setMessage("started");progressDialog.show();
        final StorageReference file=reference.child("Audio").child(editText1.getText().toString());
        Uri uri= Uri.fromFile(new File(filename));
        file.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"finished",Toast.LENGTH_SHORT).show();
                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Uri downloadUrl = uri;
                        audio=downloadUrl.toString();address.add(audio);
                        try {
                            sharedPreferences.edit().putString("addr",sharedpreferences.serialize(address)).apply();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        //Toast.makeText(getApplicationContext(),audio,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}

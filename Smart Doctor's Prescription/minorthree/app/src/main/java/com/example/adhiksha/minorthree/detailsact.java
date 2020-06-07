package com.example.adhiksha.minorthree;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class detailsact extends AppCompatActivity {
public static String patient="",dett="";public static int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsact);
        Button button=(Button) findViewById(R.id.button5);
        final EditText editText=(EditText) findViewById(R.id.editText2);
        final ListView listView=(ListView) findViewById(R.id.listview);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editText.getText().toString().equals("password")){
                    ArrayAdapter arrayAdapter=new ArrayAdapter(detailsact.this,android.R.layout.simple_list_item_1,MainActivity.names);
                    listView.setAdapter(arrayAdapter);
                }
                else{
                    Toast.makeText(detailsact.this,"wrong password",Toast.LENGTH_SHORT).show();
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                a=position;
                Intent intent=new Intent(detailsact.this,detailper.class);
                startActivity(intent);
            }
        });

    }
}

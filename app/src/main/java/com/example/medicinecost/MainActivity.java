package com.example.medicinecost;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.medicinecost.DBMS.DBMS;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Layout;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Button search_button;
    private Button update_local_db;
    private Button insert_data;
    private Layout layout;
    private DatabaseReference medicine_data;
    private DBMS dbms;
    private  ProgressDialog progressDialog;
    private Button local_db;
    private Button delete_data;
    private static double count;
    private static double progress;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        View layout = findViewById(R.id.top);
        text = (TextView) layout.findViewById(R.id.enter_medicine_name);
        local_db = (Button) findViewById(R.id.add_data_to_database2);
        search_button = (Button) layout.findViewById(R.id.search_button);
        update_local_db = (Button) findViewById(R.id.update_local_database);
        insert_data = (Button) findViewById(R.id.add_data_to_database);
        delete_data = (Button) findViewById(R.id.delete_data_button);

        dbms = new DBMS(this);
        progressDialog = new ProgressDialog(this);

        local_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), local_database_data.class);
                startActivity(intent);
            }
        });


        medicine_data = FirebaseDatabase.getInstance().getReference().child("0");


        insert_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Insert.class);
                startActivity(intent);
            }
        });


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Search field is empty!",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent intent = new Intent(getApplicationContext(), Searched_item.class);
                    intent.putExtra("string", text.getText().toString());
                    startActivity(intent);
                }
            }
        });

        update_local_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count = 0;
                progressDialog.setCancelable(false);
                progressDialog.setTitle("Downloading 500KB Data");
                progressDialog.setMessage("Syncing..... could take 2-3 min based on processor");
                progressDialog.setProgressStyle(0);
                progressDialog.show();
                dbms.deleteTable();
                medicine_data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Iterator<DataSnapshot> iterator = snapshot.getChildren().iterator();
                        while (iterator.hasNext()){
                            count++;
                            progress = count/(double) snapshot.getChildrenCount();
                            progress = 100*progress;
                            progressDialog.setProgress((int)count);
                            //Log.d("count",progress+"");
                            Medicine medicine = iterator.next().getValue(Medicine.class);
                            assert medicine != null;
                            dbms.updateContact(medicine.getName(),medicine.getCode(),medicine.getDis(),medicine.getEach(),medicine.getCost());

                        }
                        progressDialog.cancel();
                        Toast.makeText(MainActivity.this,"Local Database updated",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        progressDialog.cancel();
                        Toast.makeText(MainActivity.this,"Error while syncing",Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        delete_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), deleteActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {



        super.onStart();
    }
}
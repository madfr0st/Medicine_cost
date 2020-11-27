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
import android.view.View;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private TextView text;
    private Button search_button;
    private Button update_local_db;
    private Button insert_data;
    private Layout layout;
    private DatabaseReference medicine_data;
    private DBMS dbms;
    private ProgressDialog progressDialog;
    private Button local_db;
    
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
        dbms = new DBMS(this);
        progressDialog = new ProgressDialog(this);

        local_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),Data_from_local_DB.class);
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
                Intent intent = new Intent(getApplicationContext(), Searched.class);
                startActivity(intent);
            }
        });

        update_local_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Updating local Database from Cloud");
                progressDialog.setCancelable(false);
                progressDialog.show();
                medicine_data.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                            Medicine medicine = postSnapshot.getValue(Medicine.class);

                            assert medicine != null;
                            dbms.updateContact(medicine.getName(),medicine.getCode(),medicine.getDis(),medicine.getEach(),medicine.getCost());

                        }
                        progressDialog.cancel();
                        Toast.makeText(MainActivity.this,"Local Database updated",Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(MainActivity.this,"Error while syncing",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });


    }

    @Override
    protected void onStart() {



        super.onStart();
    }
}
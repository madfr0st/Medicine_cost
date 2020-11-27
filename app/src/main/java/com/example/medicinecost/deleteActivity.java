package com.example.medicinecost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class deleteActivity extends AppCompatActivity {

    private DatabaseReference medicine_data;
    private Button delete_button;
    private TextView medicine_code;
    private ProgressDialog progressDialog;
    private static boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);


        medicine_data = FirebaseDatabase.getInstance().getReference().child("0");
        medicine_code = (TextView) findViewById(R.id.delete_input_field);
        delete_button = (Button) findViewById(R.id.button2);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("deleting data from cloud");
        progressDialog.setCancelable(false);


        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(medicine_code.getText().length()==0){
                    Toast.makeText(getApplicationContext(),"Input field is empty",Toast.LENGTH_LONG).show();
                }
                else{
                    progressDialog.show();
                    medicine_data.addValueEventListener(new ValueEventListener() {

                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot data : snapshot.getChildren()){
                                if(data.child("code").getValue().toString().equals(medicine_code.getText().toString())){
                                    check = true;
                                    data.getRef().removeValue();
                                }
                            }
                            progressDialog.cancel();
                            if(check){
                                Toast.makeText(getApplicationContext(),"Medicine data is deleted.",Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"No Medicine found with that code.",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(getApplicationContext(),"Something is wrong.",Toast.LENGTH_LONG).show();
                        }
                    });
                }

            }
        });


    }
}
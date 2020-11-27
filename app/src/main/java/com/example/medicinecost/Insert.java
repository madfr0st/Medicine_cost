package com.example.medicinecost;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Insert extends AppCompatActivity {


    private TextView name;
    private TextView cost;
    private TextView dis;
    private TextView each;
    private TextView code;

    private Button button;

    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Uploading data to cloud and syncing local database");
        databaseReference = FirebaseDatabase.getInstance().getReference().child("0");
        name = (TextView) findViewById(R.id.editTextTextPersonName);
        code = (TextView) findViewById(R.id.editTextTextPersonName2);
        dis = (TextView) findViewById(R.id.editTextTextPersonName3);
        each = (TextView) findViewById(R.id.editTextTextPersonName4);
        cost = (TextView) findViewById(R.id.editTextTextPersonName5);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().length()==0 || code.getText().length()==0  || dis.getText().length()==0  || each.getText().length()==0
                        || cost.getText().length()==0 ){
                    Toast.makeText(Insert.this,"Some fields are empty",Toast.LENGTH_LONG).show();
                }
                else{
                    databaseReference.child(code.getText().toString()).setValue(new Medicine(name.getText().toString(),Integer.parseInt(code.getText().toString()),dis.getText().toString(),

                            Integer.parseInt(each.getText().toString()),cost.getText().toString()));
                    Toast.makeText(Insert.this,"If every thing works fine then data has been added to cloud",Toast.LENGTH_LONG).show();
                }
            }
        });






    }
}
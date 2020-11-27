package com.example.medicinecost;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.ObservableSnapshotArray;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Searched extends AppCompatActivity {
    private TextView text;
    private Button search_button;
    private RecyclerView mRecyclerView;
    private DatabaseReference medicine_data;
    private FirebaseRecyclerAdapter firebaseRecyclerAdapter;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched);

        mRecyclerView = (RecyclerView) findViewById(R.id.chatList);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
    @Override
    public void onStart() {

        medicine_data = FirebaseDatabase.getInstance().getReference().child("0");

        FirebaseRecyclerOptions<Medicine> options = new FirebaseRecyclerOptions.
                Builder<Medicine>().
                setQuery(medicine_data, Medicine.class).build();


        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Medicine,chatlistholder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull chatlistholder chatlistholder, int i, @NonNull Medicine medicine) {
                chatlistholder.setText(medicine.getName(),medicine.getCode(),medicine.getDis(),medicine.getEach(),medicine.getCost());
                Log.d("int",String.valueOf(i));
            }


            @NonNull
            @Override
            public chatlistholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View v = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.user_single_layout, parent, false);

                return new chatlistholder(v);

            }
        };

        firebaseRecyclerAdapter.startListening();
        mRecyclerView.setAdapter(firebaseRecyclerAdapter);

        super.onStart();
    }
    public static class chatlistholder extends RecyclerView.ViewHolder {

        View mView;

        public chatlistholder(@NonNull View itemView) {
            super(itemView);

            mView = itemView;
        }

        public void setText(String name, int code, String dis, int each, String cost) {
            TextView text1 = (TextView) mView.findViewById(R.id.textView1);
            text1.setText(name);
            TextView text2 = (TextView) mView.findViewById(R.id.textView2);
            text2.setText(dis);
            TextView text3 = (TextView) mView.findViewById(R.id.textView3);
            text3.setText(Integer.toString(code));
            TextView text4 = (TextView) mView.findViewById(R.id.textView4);
            text4.setText(cost);

        }



    }

}
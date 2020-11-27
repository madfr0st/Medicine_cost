package com.example.medicinecost;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicinecost.DBMS.DBMS;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class Searched_item extends AppCompatActivity {
    private TextView text;
    private Button search_button;
    private RecyclerView recyclerView;
    private View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searched);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        String string = getIntent().getExtras().getString("string");
        Log.d("check",string);
        DBMS dbms = new DBMS(this);
        local_database_data.MyListAdapter adapter = new local_database_data.MyListAdapter(
                Medicine.searchItem(dbms.getAllMeDicine(),string));

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }

    public static class MyListAdapter extends RecyclerView.Adapter<local_database_data.MyListAdapter.medicinelistholder>{

        private ArrayList<Medicine> listdata;

        // RecyclerView recyclerView;
        public MyListAdapter(ArrayList<Medicine> listdata) {
            this.listdata = listdata;

        }

        @NonNull
        @Override
        public local_database_data.MyListAdapter.medicinelistholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.user_single_layout, parent, false);
            return new local_database_data.MyListAdapter.medicinelistholder(listItem);
        }

        @Override
        public void onBindViewHolder(@NonNull local_database_data.MyListAdapter.medicinelistholder holder, int position) {
            holder.setText(listdata.get(position).getName(),listdata.get(position).getCode(),
                    listdata.get(position).getDis(),listdata.get(position).getEach(),listdata.get(position).getCost(),position);
        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }

    }

}
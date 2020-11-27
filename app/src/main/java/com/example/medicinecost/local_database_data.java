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
import android.widget.TextView;

import com.example.medicinecost.DBMS.DBMS;

import java.util.ArrayList;

public class local_database_data extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_from_local__d_b);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        ArrayList<Medicine> list = new DBMS(this).getAllMeDicine();


        MyListAdapter adapter = new MyListAdapter(list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    public static class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.medicinelistholder>{

        private ArrayList<Medicine> listdata;

        // RecyclerView recyclerView;
        public MyListAdapter(ArrayList<Medicine> listdata) {
            this.listdata = listdata;

        }

        @NonNull
        @Override
        public medicinelistholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View listItem= layoutInflater.inflate(R.layout.user_single_layout, parent, false);
            return new medicinelistholder(listItem);
        }

        @Override
        public void onBindViewHolder(@NonNull medicinelistholder holder, int position) {
            holder.setText(listdata.get(position).getName(),listdata.get(position).getCode(),
                    listdata.get(position).getDis(),listdata.get(position).getEach(),listdata.get(position).getCost(),position+1);
        }

        @Override
        public int getItemCount() {
            return listdata.size();
        }

        public static class medicinelistholder extends RecyclerView.ViewHolder {

            View mView;

            public medicinelistholder(@NonNull View itemView) {
                super(itemView);

                mView = itemView;
            }

            public void setText(String name, int code, String dis, int each, String cost,int a) {
                TextView textView1 = (TextView) mView.findViewById(R.id.textView8);
                textView1.setText(a+".");
                TextView textView = (TextView) mView.findViewById(R.id.textView17);
                textView.setText(Integer.toString(each));
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

}
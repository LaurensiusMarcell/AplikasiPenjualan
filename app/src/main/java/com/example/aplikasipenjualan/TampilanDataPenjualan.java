package com.example.aplikasipenjualan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TampilanDataPenjualan extends AppCompatActivity implements PenjualanAdaptor.datalistener {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampilan_data_penjualan);

        recyclerView = findViewById(R.id.masterdatabarang);
        MyRecyclerView();
        GetData();
    }

    private DatabaseReference reference;
    private ArrayList<Barang> dataBarangs;

    private void GetData() {
        reference = FirebaseDatabase.getInstance().getReference();
        reference.child("Penjualan").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                dataBarangs = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Barang stock = snapshot.getValue(Barang.class);
                    stock.setKey(snapshot.getKey());
                    dataBarangs.add(stock);
                }

                adapter = new PenjualanAdaptor(dataBarangs, TampilanDataPenjualan.this);

                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void MyRecyclerView() {
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    public void onDeleteData(Barang data, int position) {

    }
}
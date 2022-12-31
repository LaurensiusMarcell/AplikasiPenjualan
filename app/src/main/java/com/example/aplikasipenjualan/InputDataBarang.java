package com.example.aplikasipenjualan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InputDataBarang extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText xnokode,xnamabar,xjumbar,xhargabar;
    Spinner xsatbar;
    Button tblSimpan, tblView;
    DatabaseReference dbref;
    Barang barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_data_barang);

        xnokode=findViewById(R.id.no_barang);
        xnamabar=findViewById(R.id.nama_barang);
        xjumbar=findViewById(R.id.jumlah_barang);
        xhargabar=findViewById(R.id.harga_barang);
        tblSimpan=findViewById(R.id.tombolsimpan);
        tblView=findViewById(R.id.tomboltampil);

        //Ini untuk satuan barang
        xsatbar=findViewById(R.id.satuan_barang);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.satuanbrg, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        xsatbar.setAdapter(adapter);
        xsatbar.setOnItemSelectedListener(this);

        barang=new Barang();
        dbref= FirebaseDatabase.getInstance().getReference().child("DataBarang");

        tblSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                barang.setKode_bar(xnokode.getText().toString().trim());
                barang.setNama_bar(xnamabar.getText().toString().trim());
                barang.setSat_bar(xsatbar.getSelectedItem().toString().trim());
                barang.setJum_bar(xjumbar.getText().toString().trim());
                barang.setHrg_bar(xhargabar.getText().toString().trim());

                dbref.push().setValue(barang);
            }
        });
        tblView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputDataBarang.this, TampilDataBarang.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
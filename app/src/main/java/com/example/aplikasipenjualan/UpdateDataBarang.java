package com.example.aplikasipenjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UpdateDataBarang extends AppCompatActivity {

    private EditText kodBaru;
    private EditText namaBaru;
    private EditText satBaru;
    private EditText jumBaru;
    private EditText harBaru;
    private Button btnupdate, btndelete;
    private DatabaseReference database;
    private String cekKode, cekNama, cekSatuan, cekJumlah, cekHarga;
    Barang barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_data_barang);

        kodBaru=findViewById(R.id.new_kode);
        namaBaru=findViewById(R.id.new_nama);
        satBaru=findViewById(R.id.new_satuan);
        jumBaru=findViewById(R.id.new_jumlah);
        harBaru=findViewById(R.id.new_harga);
        btnupdate=findViewById(R.id.tblupdate);
        btndelete=findViewById(R.id.tbldelete);

        barang=new Barang();
        database= FirebaseDatabase.getInstance().getReference();
        getData();

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteBarang(barang);
            }
        });

        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekKode=kodBaru.getText().toString();
                cekNama=namaBaru.getText().toString();
                cekSatuan=satBaru.getText().toString();
                cekJumlah=jumBaru.getText().toString();
                cekHarga=harBaru.getText().toString();

                if (isEmpty(cekNama) || isEmpty(cekNama)){
                    Toast.makeText(UpdateDataBarang.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    Barang setbarang=new Barang();
                    setbarang.setKode_bar(kodBaru.getText().toString());
                    setbarang.setNama_bar(namaBaru.getText().toString());
                    setbarang.setSat_bar(satBaru.getText().toString());
                    setbarang.setJum_bar(jumBaru.getText().toString());
                    setbarang.setHrg_bar(harBaru.getText().toString());
                    updateBarang(setbarang);
                }
            }
        });
    }

    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    private void getData(){
        final String getkode = getIntent().getExtras().getString("dataKode");
        final String getnama = getIntent().getExtras().getString("dataNama");
        final String getsatuan = getIntent().getExtras().getString("dataSatuan");
        final String getjumlah = getIntent().getExtras().getString("dataJumlah");
        final String getharga = getIntent().getExtras().getString("dataHarga");
        kodBaru.setText(getkode);
        namaBaru.setText(getnama);
        satBaru.setText(getsatuan);
        jumBaru.setText(getjumlah);
        harBaru.setText(getharga);
    }

    private void updateBarang(Barang barang){
        String getKey=getIntent().getExtras().getString("getPrimaryKey");
        database.child("DataBarang").child(getKey).setValue(barang).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                kodBaru.setText("");
                namaBaru.setText("");
                satBaru.setText("");
                jumBaru.setText("");
                harBaru.setText("");
                Toast.makeText(UpdateDataBarang.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void deleteBarang(Barang barang){
        String getKey=getIntent().getExtras().getString("getPrimaryKey");
        database.child("DataBarang").child(getKey).setValue(barang).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                kodBaru.setText("");
                namaBaru.setText("");
                satBaru.setText("");
                jumBaru.setText("");
                harBaru.setText("");
                Toast.makeText(UpdateDataBarang.this, "Data berhasil dihapus", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
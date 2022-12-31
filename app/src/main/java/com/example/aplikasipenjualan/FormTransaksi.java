package com.example.aplikasipenjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FormTransaksi extends AppCompatActivity {

    TextView kodTetap, namaTetap, satTetap,jumTetap;
    EditText jualBaru, harBaru, totalBaru;
    Button btnjual, btnbatal;
    DatabaseReference database;
    private String cekKode, cekNama, cekSatuan, cekJumlah, cekJual, cekHarga, cekTotal;
    Barang barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_transaksi);

        kodTetap=findViewById(R.id.new_kode);
        namaTetap=findViewById(R.id.new_nama);
        satTetap=findViewById(R.id.new_satuan);
        jumTetap=findViewById(R.id.new_jumlah);
        jualBaru=findViewById(R.id.new_jual);
        harBaru=findViewById(R.id.new_harga);
        totalBaru=findViewById(R.id.new_total);
        btnjual=findViewById(R.id.tbljual);
        btnbatal=findViewById(R.id.tblbatal);

        barang=new Barang();
        database= FirebaseDatabase.getInstance().getReference();
        getData();

        jualBaru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String jumlahstok=jumTetap.getText().toString().trim();
                String jumlahjual=jualBaru.getText().toString().trim();
                String hargabarang=harBaru.getText().toString().trim();

                double js=Double.parseDouble(jumlahstok);
                double jj=Double.parseDouble(jumlahjual);
                double hb=Double.parseDouble(hargabarang);
                double total=(jj*hb);

                if(jj >= js){
                    Toast.makeText(FormTransaksi.this, "Jumlah Barang Tidak Cukup", Toast.LENGTH_SHORT).show();
                }else {
                    totalBaru.setText(""+total);
                }

            }
        });

        btnjual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekKode=kodTetap.getText().toString();
                cekNama=namaTetap.getText().toString();
                cekSatuan=satTetap.getText().toString();
                cekJumlah=jumTetap.getText().toString();
                cekJual=jualBaru.getText().toString();
                cekHarga=harBaru.getText().toString();
                cekTotal=totalBaru.getText().toString();

                if (isEmpty(cekNama) || isEmpty(cekNama)){
                    Toast.makeText(FormTransaksi.this, "Data tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
                }else {
                    Barang setbarang=new Barang();
                    setbarang.setKode_bar(kodTetap.getText().toString());
                    setbarang.setNama_bar(namaTetap.getText().toString());
                    setbarang.setSat_bar(satTetap.getText().toString());
                    setbarang.setJum_bar(jumTetap.getText().toString());
                    setbarang.setJual_bar(jualBaru.getText().toString());
                    setbarang.setHrg_bar(harBaru.getText().toString());
                    setbarang.setTotal_bar(totalBaru.getText().toString());
                    transaksiBarang(setbarang);
                }

                //Intent intent = new Intent(FormTransaksi.this, TampilanDataPenjualan.class);
                //startActivity(intent);
            }
        });
    }

    private boolean isEmpty(String s) {
        return TextUtils.isEmpty(s);
    }

    private void getData() {
        final String getkode = getIntent().getExtras().getString("dataKode");
        final String getnama = getIntent().getExtras().getString("dataNama");
        final String getsatuan = getIntent().getExtras().getString("dataSatuan");
        final String getjumlah = getIntent().getExtras().getString("dataJumlah");
        final String getjual = getIntent().getExtras().getString("dataJual");
        final String getharga = getIntent().getExtras().getString("dataHarga");
        final String gettotal = getIntent().getExtras().getString("dataTotal");
        kodTetap.setText(getkode);
        namaTetap.setText(getnama);
        satTetap.setText(getsatuan);
        jumTetap.setText(getjumlah);
        jualBaru.setText(getjual);
        harBaru.setText(getharga);
        totalBaru.setText(gettotal);
    }

    private void transaksiBarang(Barang barang){
        String getKey=getIntent().getExtras().getString("getPrimaryKey");
        database.child("Penjualan").child(getKey).setValue(barang).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                jualBaru.setText("");
                harBaru.setText("");
                totalBaru.setText("");
                Toast.makeText(FormTransaksi.this, "Data berhasil diubah", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
package com.example.aplikasipenjualan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout xinput,xstock,xtransaksi,laporan1,tentang1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xinput=findViewById(R.id.inputbarang);
        xstock=findViewById(R.id.stockbarang);
        xtransaksi=findViewById(R.id.transaksi);
        laporan1=findViewById(R.id.xlaporan);
        tentang1=findViewById(R.id.xtentang);

        xinput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, InputDataBarang.class);
                startActivity(intent);
            }
        });
        xstock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, TampilDataBarang.class);
                startActivity(intent);
            }
        });
        xtransaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, TampilDataTransaksi.class);
                startActivity(intent);
            }
        });
        laporan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, TampilanDataPenjualan.class);
                startActivity(intent);
            }
        });
        tentang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, TentangSaya.class);
                startActivity(intent);
            }
        });
    }
}
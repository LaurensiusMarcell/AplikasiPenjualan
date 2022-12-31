package com.example.aplikasipenjualan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TransaksiAdaptor extends RecyclerView.Adapter<TransaksiAdaptor.ViewHolder> {

    private final ArrayList<Barang> listbarang;
    private final Context context;

    public TransaksiAdaptor(ArrayList<Barang> listbarang, Context context) {
        this.listbarang=listbarang;
        this.context=context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView hkode, hnama, hsatuan, hjumlah, hharga;
        private LinearLayout ListItem;
        ViewHolder(View itemView) {
            super(itemView);
            hkode=itemView.findViewById(R.id.kobar);
            hnama=itemView.findViewById(R.id.nabar);
            hsatuan=itemView.findViewById(R.id.satbar);
            hjumlah=itemView.findViewById(R.id.jumbar);
            hharga=itemView.findViewById(R.id.habar);
            ListItem=itemView.findViewById(R.id.masterdatabarang);
        }
    }

    @Override
    public TransaksiAdaptor.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_format_tampilan_transaksi, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(TransaksiAdaptor.ViewHolder holder, final int position) {
        final String kode = listbarang.get(position).getKode_bar();
        final String nama = listbarang.get(position).getNama_bar();
        final String satuan = listbarang.get(position).getSat_bar();
        final String jumlah = listbarang.get(position).getJum_bar();
        final String harga = listbarang.get(position).getHrg_bar();

        holder.hkode.setText("Kode                                 : "+kode);
        holder.hnama.setText("Nama Barang                 : "+nama);
        holder.hsatuan.setText("Satuan                              : "+satuan);
        holder.hjumlah.setText("Jumlah                             : "+jumlah);
        holder.hharga.setText("Harga                               : "+harga);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Bundle bundle=new Bundle();
                bundle.putString("dataKode",listbarang.get(position).getKode_bar());
                bundle.putString("dataNama",listbarang.get(position).getNama_bar());
                bundle.putString("dataSatuan",listbarang.get(position).getSat_bar());
                bundle.putString("dataJumlah",listbarang.get(position).getJum_bar());
                bundle.putString("dataJual",listbarang.get(position).getJual_bar());
                bundle.putString("dataHarga",listbarang.get(position).getHrg_bar());
                bundle.putString("dataTotal",listbarang.get(position).getTotal_bar());

                bundle.putString("getPrimaryKey",listbarang.get(position).getKey());
                Intent intent=new Intent(v.getContext(), FormTransaksi.class);
                intent.putExtras(bundle);
                context.startActivity(intent);
                return true;
            }
        });
    }

    @Override
    public int getItemCount(){ return listbarang.size(); }

    public interface datalistener {
        void onDeleteData(Barang data, int position);
    }
    datalistener listener;
}

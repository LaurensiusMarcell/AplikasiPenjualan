package com.example.aplikasipenjualan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class PenjualanAdaptor extends RecyclerView.Adapter<PenjualanAdaptor.ViewHolder> {

    private final ArrayList<Barang> listbarang;
    private final Context context;

    public PenjualanAdaptor(ArrayList<Barang> listbarang, Context context) {
        this.listbarang=listbarang;
        this.context=context;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView xkode, xnama, xsatuan, xjumlah, xjual, xharga, xtotal;
        private LinearLayout ListItem;
        ViewHolder(View itemView) {
            super(itemView);
            xkode=itemView.findViewById(R.id.kobar);
            xnama=itemView.findViewById(R.id.nabar);
            xsatuan=itemView.findViewById(R.id.satbar);
            xjumlah=itemView.findViewById(R.id.jumbar);
            xjual=itemView.findViewById(R.id.jualbar);
            xharga=itemView.findViewById(R.id.habar);
            xtotal=itemView.findViewById(R.id.totalbar);
            ListItem=itemView.findViewById(R.id.masterdatabarang);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View V = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_format_tampilan_penjualan, parent, false);
        return new ViewHolder(V);
    }

    @Override
    public void onBindViewHolder(PenjualanAdaptor.ViewHolder holder, final int position) {
        final String kode = listbarang.get(position).getKode_bar();
        final String nama = listbarang.get(position).getNama_bar();
        final String satuan = listbarang.get(position).getSat_bar();
        final String jumlah = listbarang.get(position).getJum_bar();
        final String jual = listbarang.get(position).getJual_bar();
        final String harga = listbarang.get(position).getHrg_bar();
        final String total = listbarang.get(position).getTotal_bar();

        holder.xkode.setText("" + kode);
        holder.xnama.setText("" + nama);
        holder.xsatuan.setText("" + satuan);
        holder.xjumlah.setText("" + jumlah);
        holder.xjual.setText("" + jual);
        holder.xharga.setText("" + harga);
        holder.xtotal.setText("" + total);
    }

    @Override
    public int getItemCount() {
        return listbarang.size();
    }

    public interface datalistener {
        void onDeleteData(Barang data, int position);
    }
    datalistener listener;
}

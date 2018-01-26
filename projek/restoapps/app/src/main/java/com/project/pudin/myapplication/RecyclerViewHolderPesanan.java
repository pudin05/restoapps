package com.project.pudin.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by WIN10 test on 12/6/2017.
 */

public class RecyclerViewHolderPesanan extends RecyclerView.ViewHolder {
    TextView title,listPesanan,totBiaya,status; //deklarasi textview
    CardView cv;
    ImageView image;

    public RecyclerViewHolderPesanan(View itemView) {
        super(itemView);
        cv= (CardView) itemView.findViewById(R.id.cvP);
        title = (TextView) itemView.findViewById(R.id.titleP);
        //menampilkan text dari widget CardView pada id daftar_judul
        listPesanan= (TextView) itemView.findViewById(R.id.listPesanan);
        //menampilkan text deskripsi dari widget CardView pada id daftar_deskripsi
        totBiaya= (TextView) itemView.findViewById(R.id.totBiaya);
        //menampilkan gambar atau icon pada widget Cardview pada id daftar_icon
        status=(TextView) itemView.findViewById(R.id.statuspesanan);
        image=(ImageView) itemView.findViewById(R.id.imageView);
    }
}

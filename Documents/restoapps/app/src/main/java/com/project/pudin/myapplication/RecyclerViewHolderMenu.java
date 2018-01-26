package com.project.pudin.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * Created by WIN10 test on 12/6/2017.
 */

public class RecyclerViewHolderMenu extends RecyclerView.ViewHolder {
    TextView tv1,tv2,discount; //deklarasi textview
    ImageView imageView;  //deklarasi imageview
    CardView cv;

    public RecyclerViewHolderMenu(View itemView) {
        super(itemView);
        cv= (CardView) itemView.findViewById(R.id.card_view);
        tv1= (TextView) itemView.findViewById(R.id.daftar_judul1);
        //menampilkan text dari widget CardView pada id daftar_judul
        tv2= (TextView) itemView.findViewById(R.id.daftar_deskripsi1);
        //menampilkan text deskripsi dari widget CardView pada id daftar_deskripsi
        discount = (TextView)itemView.findViewById(R.id.discount);
        imageView= (ImageView) itemView.findViewById(R.id.daftar_icon1);
        //menampilkan gambar atau icon pada widget Cardview pada id daftar_icon
    }
}

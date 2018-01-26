package com.project.pudin.myapplication;

import android.content.Context;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.project.pudin.myapplication.util.myValue;
import com.project.pudin.myapplication.util.pesananUser;


public class RecyclerAdapterPesanan extends RecyclerView.Adapter<RecyclerViewHolderPesanan> {
    private RecyclerViewHolderPesanan rvhp;
    LayoutInflater inflater;
    String[] listPesanan,idPesanan,status;
    Animation fade;

    public RecyclerAdapterPesanan(Context ctx,pesananUser pu){
        inflater = LayoutInflater.from(ctx);
        listPesanan = pu.getListP().split(",");
        idPesanan = pu.getIdPesanan().split(",");
        status = pu.getStatus().split(",");
        fade = AnimationUtils.loadAnimation(ctx,R.anim.fade);
    }

    @Override
    public RecyclerViewHolderPesanan onCreateViewHolder(ViewGroup parent, int viewType) {
        View fragment = inflater.inflate(R.layout.item_list_pesanan,parent,false);
        rvhp = new RecyclerViewHolderPesanan(fragment);
        return rvhp;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderPesanan holder, int position) {
        myValue mv = new myValue(Integer.parseInt(status[position]));
        holder.title.setText(idPesanan[position]);
        holder.listPesanan.setText(listPesanan[position]);
        holder.status.setText(mv.getStatuspesanan());
        holder.image.setImageResource(mv.setMyImage());
        holder.image.setAnimation(fade);
    }


    @Override
    public int getItemCount() {
        return listPesanan.length;
    }


}

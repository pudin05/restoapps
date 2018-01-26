package com.project.pudin.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.project.pudin.myapplication.util.IdMakUser;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Request;
import com.squareup.picasso.RequestHandler;
import com.squareup.picasso.Target;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;

/**
 * Created by WIN10 test on 12/6/2017.
 */

public class RecyclerAdapterMenu extends RecyclerView.Adapter<RecyclerViewHolderMenu> {
    Activity context;
    private String[] id,name,price,promo,status,image;
    private LayoutInflater inflater;
    private ConstraintLayout clbg;
    private RecyclerViewHolderMenu viewHolder;
    private RecyclerView rcv;
    private EditText pors;
    View v1,v2,v3;
    FloatingActionButton cart,profile;

    public RecyclerAdapterMenu(Activity context, ConstraintLayout clbg, RecyclerView recyclerView, EditText pors, FloatingActionButton cart,FloatingActionButton profile){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.id = SharedPref.getInstance(context).getMenu().getId().split(",");
        this.name = SharedPref.getInstance(context).getMenu().getNamaMakanan().split(",");
        this.price = SharedPref.getInstance(context).getMenu().getHarga().toString().split(",");
        this.promo = SharedPref.getInstance(context).getMenu().getPromo().split(",");
        this.status = SharedPref.getInstance(context).getMenu().getStatus().split(",");
        this.image = SharedPref.getInstance(context).getMenu().getImage().split(",");
        this.clbg = clbg;
        this.rcv = recyclerView;
        this.pors = pors;
        this.cart = cart;
        this.profile = profile;
    }

    public RecyclerViewHolderMenu onCreateViewHolder(ViewGroup parent, int viewType) {
        View il = inflater.inflate(R.layout.item_list_menu, parent, false);
        viewHolder=new RecyclerViewHolderMenu(il);
        return viewHolder;
    }

    public void onBindViewHolder(final RecyclerViewHolderMenu holder, final int position) {
        //=============Text dan Gambar=======================================
        v1 = holder.tv1;
        v2 = holder.tv2;
        v3 = holder.imageView;
        holder.tv1.setText(name[position]);
        holder.tv2.setText("Rp."+price[position]);
        holder.tv1.setOnClickListener(clickListener);
        holder.tv2.setOnClickListener(clickListener);
        holder.imageView.setOnClickListener(clickListener);
        final Picasso.Builder pb = new Picasso.Builder(context); //gambar
        if(!status[position].equals("0")) {
            holder.tv1.setTag(holder);
            holder.tv2.setTag(holder);
            holder.imageView.setTag(holder);
            pb.build().load(URLs.ROOT_URL+image[position]).into(holder.imageView, new Callback() {
                @Override
                public void onSuccess() {
                    pb.build().load(URLs.ROOT_URL+image[position]).into(holder.imageView);
                }

                @Override
                public void onError() {
                    pb.build().cancelRequest(holder.imageView);
                    holder.imageView.setImageResource(R.drawable.noimage);
                }
            }); //gambar
        } else{
            System.out.println("else Posisi"+position+" = "+status[position]);
            holder.tv1.setEnabled(false);
            holder.tv2.setEnabled(false);
            holder.imageView.setEnabled(false);
            pb.build().load(URLs.ROOT_URL+"img/oos.jpg").into(holder.imageView);
        }

        //===========================================================================
        if(!promo[position].equals("")){
            Integer harga = Integer.valueOf(price[position]);
            Integer discounted0 = harga*Integer.valueOf(promo[position])/100;
            Integer discounted2 = harga-discounted0;
            holder.tv2.setBackgroundResource(R.drawable.strikethrough);
            holder.discount.setText(discounted2.toString());
        } else if(!promo[position].equals("0")){
            holder.discount.setVisibility(View.GONE);
        }
        else{
            holder.discount.setVisibility(View.GONE);
        }
    }

    View.OnClickListener clickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//member aksi saat cardview diklik berdasarkan posisi tertentu
            RecyclerViewHolderMenu vholder = (RecyclerViewHolderMenu) v.getTag();
            int position = vholder.getAdapterPosition();
            pors.setText("1");
            IdMakUser idMakUser = new IdMakUser(id[position]);
            SharedPref.getInstance(context).saveIdMak(idMakUser);
            clbg.setVisibility(View.VISIBLE);
            clbg.bringToFront();
            context.setTitle("Masukan Porsi");
            cart.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
        }
    };


    public int getItemCount() {
        return name.length;
    }
}

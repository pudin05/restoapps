package com.project.pudin.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.pudin.myapplication.util.oldValueUser;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by WIN10 test on 12/15/2017.
 */

public class RecyclerAdapterKeranjang extends RecyclerView.Adapter<RecyclerViewHolderKeranjang>{
    private String[] nama,porsi,idMak;
    private Activity activity;
    private LayoutInflater inflater;
    private RecyclerViewHolderKeranjang viewHolderKeranjang;
    private ConstraintLayout cl;
    private EditText porsTxt;
    private Button myordr,ordr;

    public RecyclerAdapterKeranjang(Activity activity, ConstraintLayout cl, EditText porsTxt,Button myordr,Button ordr){
        this.activity = activity;
        inflater = LayoutInflater.from(activity);
        this.nama = SharedPref.getInstance(activity).getKeranjang().getNama().split(",");
        this.porsi = SharedPref.getInstance(activity).getKeranjang().getPorsi().split(",");
        this.idMak = SharedPref.getInstance(activity).getKeranjang().getIdmak().split(",");
        this.cl = cl;
        this.porsTxt = porsTxt;
        this.myordr = myordr;
        this.ordr = ordr;
    }

    @Override
    public RecyclerViewHolderKeranjang onCreateViewHolder(ViewGroup parent, int viewType) {
        View il;
        if(nama.length<=1 && nama[0].length()<2){
            il = inflater.inflate(R.layout.head_layout,null);
        } else {
           il  = inflater.inflate(R.layout.item_list_keranjang, null);
        }
        viewHolderKeranjang = new RecyclerViewHolderKeranjang(il);
        return viewHolderKeranjang;
    }

    @Override
    public void onBindViewHolder(RecyclerViewHolderKeranjang holder, int position) {
        if(nama.length<=1 && nama[0].length()<2) {

        } else {
            holder.tv1.setText(nama[position]);
            holder.tv2.setText(porsi[position] + " Porsi.");
            holder.tv1.setOnClickListener(onClickListener);
            holder.tv2.setOnClickListener(onClickListener);
            holder.tv3.setOnClickListener(onClickListener);
            holder.del.setOnClickListener(deleteBtnListener);
            holder.del.setTag(holder);
            holder.tv1.setTag(holder);
            holder.tv2.setTag(holder);
            holder.tv3.setTag(holder);
        }
        if(nama[0].length()>2) {
            Random rand = new Random();
            int color = Color.argb(125, rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
            holder.tv3.setText(getFirstLetter(nama[position]));
            holder.tv3.setBackgroundColor(color);
        }
    }

    private String getFirstLetter(String nama){
        String y = "";
        if(nama.length()>2) {
            String[] x = nama.split(" ");

            for (int i = 0; i < x.length; i++) {
                if (i == 0) {
                    y += x[i].substring(0, 1).toUpperCase();
                } else {
                    y += x[i].substring(0, 1).toLowerCase();
                }
            }
        }
        return y;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerViewHolderKeranjang holder = (RecyclerViewHolderKeranjang)view.getTag();
            int position = holder.getAdapterPosition();
            SharedPref.getInstance(activity).saveOldValue(new oldValueUser(idMak[position],porsi[position]));
            cl.setVisibility(View.VISIBLE);
            cl.bringToFront();
            myordr.setVisibility(View.GONE);
            ordr.setVisibility(View.GONE);
            porsTxt.setText(porsi[position]);
            Toast.makeText(activity,"Posisi"+position , Toast.LENGTH_SHORT).show();
        }
    };

    private View.OnClickListener deleteBtnListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerViewHolderKeranjang holder = (RecyclerViewHolderKeranjang)view.getTag();
            final int position = holder.getAdapterPosition();
            StringRequest delete = new StringRequest(Request.Method.POST, URLs.URL_DELETECART, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(activity, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> param = new HashMap<>();
                    param.put("username",SharedPref.getInstance(activity).getUser().getUsername());
                    param.put("idmak",idMak[position]);
                    param.put("porsi",porsi[position]);
                    return param;
                }
            };VolleySingleton.getInstance(activity).addToRequestQueue(delete);
            activity.finish();
            activity.startActivity(activity.getIntent());
        }
    };

    @Override
    public int getItemCount() {
        return nama.length;
    }
}

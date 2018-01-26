package com.project.pudin.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.pudin.myapplication.util.IdMakUser;
import com.project.pudin.myapplication.util.keranjangUser;
import com.project.pudin.myapplication.util.oldValueUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class keranjang extends AppCompatActivity {
    RecyclerView recyclerView;
    Integer pors;
    EditText porsi;
    TextView total,stat;
    String listPesanan;
    Button myOrder,btnOrder;
    ConstraintLayout cl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keranjang);
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        cl = (ConstraintLayout)findViewById(R.id.clbg);
        porsi = (EditText)findViewById(R.id.porsiTXT);
        final Button plus = (Button)findViewById(R.id.plusPorsi);
        final Button min = (Button)findViewById(R.id.minPorsi);
        final Button update = (Button)findViewById(R.id.btnUpdate);
        final ProgressBar loading = (ProgressBar)findViewById(R.id.loading);
        myOrder = (Button)findViewById(R.id.myOrder);
        total = (TextView)findViewById(R.id.total);
        stat = (TextView)findViewById(R.id.statKeranjang);

        SharedPref.getInstance(getApplicationContext()).nullKeranjang();

        myOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("pitek");
                startActivity(new Intent(getApplicationContext(),PesananActivity.class));
            }
        });

        pors = new Integer(porsi.getText().toString());
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pors = new Integer(porsi.getText().toString());
                pors++;
                porsi.setText(pors.toString());
            }
        });
        min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pors = new Integer(porsi.getText().toString());
                pors--;
                if(pors<0){
                    pors = 0;
                }
                porsi.setText(pors.toString());
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String oldData = SharedPref.getInstance(getApplicationContext()).getOldValue().getIdmak()+'|'+SharedPref.getInstance(getApplicationContext()).getOldValue().getPorsi();
                String value = SharedPref.getInstance(getApplicationContext()).getOldValue().getIdmak()+'|'+porsi.getText().toString();
                strReqUpdate(oldData,value);
                finish();
                startActivity(getIntent());
            }
        });

        loading.setVisibility(View.VISIBLE);
        Button blocker = (Button)findViewById(R.id.blocker);
        cl.bringToFront();
        cl.getBackground().setAlpha(120);
        blocker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myOrder.setVisibility(View.VISIBLE);
                btnOrder.setVisibility(View.VISIBLE);
                cl.setVisibility(View.GONE);
            }
        });
        strReqCart(cl,porsi);
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        strReqCart(cl,porsi);
    }

    public void strReqCart(final ConstraintLayout cl,final EditText porsi){
        StringRequest readKeranjang = new StringRequest(Request.Method.POST, URLs.URL_READCART, new Response.Listener<String>() {
            final EditText az = porsi;
            final ConstraintLayout cx = cl;
            @Override
            public void onResponse(String response) {
                try {

                    final TextView tot = (TextView)findViewById(R.id.total);
                    final JSONObject jsonGetInfo = new JSONObject(response);
                    final int t = jsonGetInfo.getInt("total");
                    keranjangUser KUser = new keranjangUser(jsonGetInfo.getString("idmak"),jsonGetInfo.getString("nama"),jsonGetInfo.getString("porsi"));
                    listPesanan = jsonGetInfo.getString("listPesanan");
                    SharedPref.getInstance(getApplicationContext()).saveKeranjang(KUser);
                    if(jsonGetInfo.getBoolean("error")==true){
                        Toast.makeText(keranjang.this, jsonGetInfo.getString("message"), Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        fetchCart(cx,az);
                        tot.post(new Runnable() {
                            @Override
                            public void run() {
                                tot.setText("Total Biaya : Rp."+t);
                            }
                        });
                        System.out.println("Total "+jsonGetInfo.getInt("total"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(keranjang.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(keranjang.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String,String>();
                param.put("username",SharedPref.getInstance(getApplicationContext()).getUser().getUsername());
                return param;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(readKeranjang);

        btnOrder = (Button)findViewById(R.id.orderBtn);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executePesan();

            }
        });
    }

    public void fetchCart(final ConstraintLayout cl,final EditText porsi){
        Runnable fetch = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<2;i++){
                    String param = SharedPref.getInstance(getApplicationContext()).getKeranjang().getNama();
                    if(param==null){
                        SystemClock.sleep(500);
                        if(i==1){
                            i=0;
                        }
                    } else{
                        recyclerView = (RecyclerView)findViewById(R.id.rv);
                        final RecyclerAdapterKeranjang adapter = new RecyclerAdapterKeranjang(keranjang.this, cl, porsi,myOrder,btnOrder);
                        final ProgressBar loading = (ProgressBar)findViewById(R.id.loading);
                        recyclerView.post(new Runnable() {
                            @Override
                            public void run() {
                                recyclerView.setAdapter(adapter);
                                recyclerView.setHasFixedSize(true);
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            }
                        });
                        loading.post(new Runnable() {
                            @Override
                            public void run() {
                                loading.setVisibility(View.GONE);
                            }
                        });
                    }
                }
            }
        };
        new Thread(fetch).start();
    }

    public void strReqUpdate(final String oldData,final String value){
        StringRequest update = new StringRequest(Request.Method.POST, URLs.URL_UPDATECART, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Update"+response);
                try {
                    JSONObject jobj = new JSONObject(response);
                    System.out.println(jobj.getString("old"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String, String>();
                param.put("username",SharedPref.getInstance(getApplicationContext()).getUser().getUsername());
                param.put("old",oldData);
                param.put("value",value);
                return param;
            }
        }; VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(update);
    }

    public void executePesan(){
        final String idCust = SharedPref.getInstance(this).getUser().getId();
        final String username = SharedPref.getInstance(this).getUser().getUsername();
        StringRequest executePesanan = new StringRequest(Request.Method.POST, URLs.URL_EXECUTEPESAN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println(response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    Boolean error = jsonObject.getBoolean("error");
                    if(error==true){
                        Toast.makeText(keranjang.this, "Error!", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(keranjang.this, "Berhasil!", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(keranjang.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<>();
                param.put("listPesanan",listPesanan);
                param.put("idCust",idCust);
                param.put("username",username);
                return param;
            }
        };VolleySingleton.getInstance(this).addToRequestQueue(executePesanan);
    }

    public void onBackPressed(){
        if(cl.getVisibility()==View.VISIBLE){
            cl.setVisibility(View.GONE);
            myOrder.setVisibility(View.VISIBLE);
            btnOrder.setVisibility(View.VISIBLE);
        } else{
            finish();
        }
    }
}

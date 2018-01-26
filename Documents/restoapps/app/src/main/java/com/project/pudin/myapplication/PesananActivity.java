package com.project.pudin.myapplication;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.pudin.myapplication.util.pesananUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WIN10 test on 1/11/2018.
 */

public class PesananActivity extends AppCompatActivity {
    String lstPesanan,idPesanan,status;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesanan);

        strReqPesanan();
    }

    public void strReqPesanan(){
        StringRequest pesanan = new StringRequest(Request.Method.POST, URLs.URL_MYORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    lstPesanan = jsonObject.getString("lstPesanan");
                    idPesanan = jsonObject.getString("idPesanan");
                    status = jsonObject.getString("status");

                    final pesananUser pu = new pesananUser(lstPesanan,idPesanan,status);
                    Runnable z = new Runnable() {
                        @Override
                        public void run() {
                            for(int i = 0; i<1; i++){
                                if(lstPesanan==null||idPesanan==null){
                                    SystemClock.sleep(500);
                                    i=0;
                                } else{
                                    final RecyclerView pesRcv = (RecyclerView)findViewById(R.id.pesananRcv);
                                    final RecyclerAdapterPesanan adapterPesanan = new RecyclerAdapterPesanan(PesananActivity.this,pu);
                                    pesRcv.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            pesRcv.setAdapter(adapterPesanan);
                                            pesRcv.setHasFixedSize(true);
                                            pesRcv.setLayoutManager(new LinearLayoutManager(PesananActivity.this));
                                        }
                                    });
                                }
                            }
                        }
                    }; new Thread(z).start();
                    System.out.println(response);
                    System.out.println(pu.getListP());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
                Toast.makeText(PesananActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<>();
                param.put("id", SharedPref.getInstance(getApplicationContext()).getUser().getId());
                return param;
            }
        }; VolleySingleton.getInstance(this).addToRequestQueue(pesanan);
    }

}

package com.project.pudin.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class profile_editor extends AppCompatActivity {

    EditText fname,lname,name,email,phone,address;
    Button save;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);

        fname = (EditText)findViewById(R.id.fName);
        lname = (EditText)findViewById(R.id.lName);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        address = (EditText)findViewById(R.id.address);
        save = (Button)findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileSave();
            }
        });

    }

    public void profileSave(){
        final String vfname,vlname,vname,vemail,vphone,vaddress,vidcustomer;

        vidcustomer = SharedPref.getInstance(this).getUser().getId();
        vfname = fname.getText().toString();
        vlname = lname.getText().toString();
        vname = vfname+" "+vlname;
        vemail = email.getText().toString();
        vphone = phone.getText().toString();
        vaddress = address.getText().toString();

        if(TextUtils.isEmpty(vfname) || TextUtils.isEmpty(vlname) || TextUtils.isEmpty(vname) || TextUtils.isEmpty(vemail) || TextUtils.isEmpty(vphone) || TextUtils.isEmpty(vaddress)){
            Toast.makeText(getApplicationContext(),"Tolong isi semua data.",Toast.LENGTH_LONG).show();
        }
        StringRequest strReq = new StringRequest(Request.Method.POST, URLs.URL_EDITOR, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject jObj = new JSONObject(response);

                        if(jObj.getBoolean("error")==true){
                            Toast.makeText(profile_editor.this, jObj.getString("message"), Toast.LENGTH_SHORT).show();
                            System.out.println("respon : "+response);
                        } else{
                            Toast.makeText(profile_editor.this, jObj.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(profile_editor.this,Profile.class);
                            startActivity(intent);
                            System.out.println("respon : "+response);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("respon : "+response);
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(profile_editor.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String,String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name",vname);
                    params.put("fname",vfname);
                    params.put("lname",vlname);
                    params.put("email",vemail);
                    params.put("phone",vphone);
                    params.put("address",vaddress);
                    params.put("id",vidcustomer);
                    return params;
                }
            }; VolleySingleton.getInstance(this).addToRequestQueue(strReq);
    }

}

package com.project.pudin.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.pudin.myapplication.util.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Select extends AppCompatActivity {
    TextView editTextUsername, editTextPassword,usr_ico,pas_ico;
    String idcustomer;
    ProgressBar progressBar;
    Button btnLogin,btnDaftar,btnTamu,btnDaftar2;
    ScrollView scrollView;
    FrameLayout fl,fl2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Typeface fa = Typeface.createFromAsset(getAssets(),"fa.ttf");
        usr_ico = (TextView)findViewById(R.id.usrIco);
        pas_ico = (TextView)findViewById(R.id.pasIco);
        usr_ico.setTypeface(fa);
        pas_ico.setTypeface(fa);
        progressBar = (ProgressBar)findViewById(R.id.pb);
        editTextUsername = (EditText)findViewById(R.id.username);
        editTextPassword = (EditText)findViewById(R.id.password);
        btnLogin = (Button)findViewById(R.id.btn_masuk);
        scrollView = (ScrollView)findViewById(R.id.regist);
        btnDaftar = (Button)findViewById(R.id.daftarBtn);
        btnTamu = (Button)findViewById(R.id.btnTamu);
        fl = (FrameLayout)findViewById(R.id.fRegist);
        fl2 = (FrameLayout)findViewById(R.id.loadingContainer);
        fl2.getBackground().setAlpha(100);
        fl.getBackground().setAlpha(100);
        final Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        btnLogin.setBackgroundResource(R.drawable.btn_masuk);
        btnDaftar.setBackgroundResource(R.drawable.btn_daftar);
        btnTamu.setBackgroundResource(R.drawable.btn_tamu);

        SharedPref.getInstance(this).nullUser();

        btnDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl.startAnimation(blink);
                fl.setVisibility(View.VISIBLE);
                btnDaftar.setVisibility(View.GONE);
                btnLogin.setVisibility(View.GONE);
                btnTamu.setVisibility(View.GONE);
                if(scrollView.getChildCount()<1) {
                    scrollView.addView(RegistInflater());
                }
                fl.bringToFront();
            }
        });

        fl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fl.setVisibility(View.GONE);
                btnLogin.setVisibility(View.VISIBLE);
                btnDaftar.setVisibility(View.VISIBLE);
                btnTamu.setVisibility(View.VISIBLE);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
                Toast.makeText(getApplicationContext(),"Clicked",Toast.LENGTH_LONG).show();

            }
        });
    }

    public void userLogin(){
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(),"Tolong isi Username atau Password.",Toast.LENGTH_LONG).show();
            editTextUsername.requestFocus();
            return;
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressBar.setVisibility(View.VISIBLE);
                try {
                    JSONObject obj = new JSONObject(response);
                    if (obj.getBoolean("error")==false) {
                        idcustomer = obj.getString("idcustomer");
                        User user = new User(username,idcustomer);
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                        SharedPref.getInstance(getApplicationContext()).userLogin(user);
                        System.out.println("user = "+SharedPref.getInstance(getApplicationContext()).getUser().getUsername());
                        finish();
                        Intent intent = new Intent(Select.this, MainMenu.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_LONG).show();
                        System.out.println("else respon"+response);
                    }
                    System.out.println("respon"+response);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(),Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
    }

    public int Height(){
        ConstraintLayout main = (ConstraintLayout)findViewById(R.id.mainLayout);
        int height = main.getHeight();
        int pad = height/3;
        return pad;
    }

    public int Width(){
        ConstraintLayout main = (ConstraintLayout)findViewById(R.id.mainLayout);
        int width = main.getWidth();
        int pad = width/4;
        return pad;
    }

    public View RegistInflater(){

        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.register_view,null);
        btnDaftar2 = layout.findViewById(R.id.btnDaftar2);

        btnDaftar2.setBackgroundResource(R.drawable.btn_daftar);
        btnDaftar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fl2.getChildCount()==0) {
                    fl2.addView(Loading());
                }
                fl2.setVisibility(View.VISIBLE);
                fl2.bringToFront();
                fl2.setPadding(Width(),Height(),Width(),Height());
                sendRegistration();
                fl.setEnabled(false);
            }
        });
        return layout;
    }

    public View Loading(){
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View loading = inflater.inflate(R.layout.loading,null);
        LinearLayout linearLayout = loading.findViewById(R.id.loadlayout);
        return linearLayout;
    }

    public void onBackPressed(){
        if(fl.getVisibility()==View.VISIBLE){
            fl.setVisibility(View.GONE);
            btnLogin.setVisibility(View.VISIBLE);
            btnDaftar.setVisibility(View.VISIBLE);
            btnTamu.setVisibility(View.VISIBLE);
        } else{
            finish();
        }
    }

    public void sendRegistration(){

        EditText rUname,rName,rPass,rCPass,rEmail,rPhone,rAddr;
        final String username,name,pass,cpass,email,phone,addr;
        rUname = (EditText)findViewById(R.id.rUname);
        rName = (EditText)findViewById(R.id.rName);
        rPass = (EditText)findViewById(R.id.rPass);
        rCPass = (EditText)findViewById(R.id.rCPass);
        rEmail = (EditText)findViewById(R.id.rEmail);
        rPhone = (EditText)findViewById(R.id.rPhone);
        rAddr = (EditText)findViewById(R.id.rAddr);

        username = rUname.getText().toString();
        name = rName.getText().toString();
        pass = rPass.getText().toString();
        cpass = rCPass.getText().toString();
        email = rEmail.getText().toString();
        phone = rPhone.getText().toString();
        addr = rAddr.getText().toString();
        System.out.println("data"+username+name+pass);
        if(!pass.equals(cpass)){
            Toast.makeText(this, "Password tidak sama, mohon periksa lagi.", Toast.LENGTH_SHORT).show();
        }

        StringRequest regist = new StringRequest(Request.Method.POST, URLs.URL_REGIST, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jobj = new JSONObject(response);

                    if (jobj.getBoolean("erruname") == true) {
                        fl2.setVisibility(View.GONE);
                        Toast.makeText(Select.this, "Username sudah digunakan.", Toast.LENGTH_SHORT).show();
                    } else {
                        fl2.setVisibility(View.GONE);
                        Toast.makeText(Select.this, "Registrasi Berhasil.", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println("Error regist");
                }
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> param = new HashMap<String,String>();
                param.put("username",username);
                param.put("name",name);
                param.put("pass",pass);
                param.put("email",email);
                param.put("phone",phone);
                param.put("addr",addr);
                return param;
            }
        };VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(regist);
    }
}

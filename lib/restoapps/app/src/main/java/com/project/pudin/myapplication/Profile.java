package com.project.pudin.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class Profile extends AppCompatActivity {
    TextView name,email,phone,address;
    Button editBtn,logoutBtn;
    Date d = new Date();
    CharSequence cs;
    ImageView pics,bg_clock;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        cs = DateFormat.format("HH",d.getTime());
        Integer hour = new Integer(cs.toString());
        name = (TextView)findViewById(R.id.fName);
        email = (TextView)findViewById(R.id.email);
        phone = (TextView)findViewById(R.id.phone);
        address = (TextView)findViewById(R.id.address);
        editBtn = (Button)findViewById(R.id.edit_btn);
        logoutBtn = (Button)findViewById(R.id.logout_btn);
        Typeface fntaws = Typeface.createFromAsset(getAssets(),"fa.ttf");
        logoutBtn.setTypeface(fntaws);
        editBtn.setTypeface(fntaws);

        timingSystem(hour);
        String username = SharedPref.getInstance(getApplicationContext()).getUser().getUsername();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLs.URL_PROFILE+username, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                            name.setText(jsonObject.getString("name"));
                            email.setText(jsonObject.getString("email"));
                            phone.setText(jsonObject.getString("phone"));
                            address.setText(jsonObject.getString("address"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPref.getInstance(Profile.this).nullUser(); // menghapus session
                Intent logoutIntent = new Intent(Profile.this,MainActivity.class);
                startActivity(logoutIntent);
            }
        });
        editBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, profile_editor.class);
                startActivity(intent);
            }
        });
    }

    public void timingSystem(Integer hour){
        pics = (ImageView)findViewById(R.id.pics);
        ConstraintLayout cl = (ConstraintLayout)findViewById(R.id.clayout);
        bg_clock = (ImageView)findViewById(R.id.bg_clock);
        if(hour>=3 && hour<=17){
            pics.setImageResource(R.drawable.user);
            bg_clock.setImageResource(R.drawable.app_bg);
            cl.setBackgroundColor(Color.parseColor("#58a9f5"));
        } else if(hour >=18){
           pics.setImageResource(R.drawable.user_night);
            bg_clock.setImageResource(R.drawable.app_bg_night);
            cl.setBackgroundColor(Color.parseColor("#20254e"));
        }
    }
}

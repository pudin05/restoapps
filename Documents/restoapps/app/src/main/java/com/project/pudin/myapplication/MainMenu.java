package com.project.pudin.myapplication;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import com.project.pudin.myapplication.FragmentSlide.*;

import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.pudin.myapplication.util.userMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainMenu extends AppCompatActivity {

    RecyclerView recyclerViewMak,recyclerViewMin;
    String id,name,price,username,idmak,pors,image,promo,status;
    Integer porsi = 1;
    ConstraintLayout clbg;
    Button btn,minPorsi,plusPorsi,btnOrder;
    FloatingActionButton floatProfile,floatCart;
    EditText porsiTxt;
    Intent profile;
    ProgressBar loading;
    ViewPager mPager;
    Runnable Min;
    float density;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        density = getResources().getDisplayMetrics().density;
        SharedPref.getInstance(this).nullMenu();
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(new ViewPagerAdapter(this));
        ImageView blck = (ImageView)findViewById(R.id.blckr);
        blck.getBackground().setAlpha(1);

        TabSlideFunction();

        Min = new Runnable() {
            @Override
            public void run() {
                SharedPref.getInstance(getApplicationContext()).nullMenu();
                        for (int i = 0; i < 2; i++) {
                            strReqMenu("minuman");
                            String param = SharedPref.getInstance(getApplicationContext()).getMenu().getNamaMakanan();
                            String param2 = SharedPref.getInstance(getApplicationContext()).getMenu().getHarga();
                            String param3 = SharedPref.getInstance(getApplicationContext()).getMenu().getPromo();

                            if (param == null || param2 == null || param3 == null) {
                                SystemClock.sleep(500);
                                if (i == 1) {
                                    i = 0; //Mengubah param i menjadi 0 agar looping terus berjalan
                                }
                            } else {
                                fetchMenuMin(clbg, porsiTxt, floatProfile, floatCart);

                            }
                        }
            }
        };

        final Runnable Mak = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 2; i++) {
                    strReqMenu("makanan");
                    String param = SharedPref.getInstance(getApplicationContext()).getMenu().getNamaMakanan();
                    String param2 = SharedPref.getInstance(getApplicationContext()).getMenu().getHarga();
                    String param3 = SharedPref.getInstance(getApplicationContext()).getMenu().getPromo();

                    if (param == null || param2 == null || param3 == null) {
                        SystemClock.sleep(500);
                        if (i == 1) {
                            i = 0; //Mengubah param i menjadi 0 agar looping terus berjalan
                        }
                    } else {
                        fetchMenu(clbg,porsiTxt,floatProfile,floatCart);

                    }
                }
            }
        };

        new Thread(Mak).start();

        clbg = (ConstraintLayout) findViewById(R.id.clbg);
        minPorsi = (Button) findViewById(R.id.minPorsi);
        plusPorsi = (Button) findViewById(R.id.plusPorsi);
        btnOrder = (Button) findViewById(R.id.btnUpdate);
        porsiTxt = (EditText) findViewById(R.id.porsiTXT);
        floatProfile = (FloatingActionButton) findViewById(R.id.floatProfile);
        floatCart = (FloatingActionButton) findViewById(R.id.floatCart);
        profile = new Intent(MainMenu.this, Profile.class);
        loading = (ProgressBar) findViewById(R.id.loading);
        recyclerViewMak = (RecyclerView) findViewById(R.id.makRcv);
        floatCart.bringToFront();
        floatProfile.bringToFront();
        ConstraintLayout clayout = (ConstraintLayout)findViewById(R.id.constraintLayout);
        clayout.bringToFront();
        floatCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart = new Intent(MainMenu.this, keranjang.class);
                startActivity(cart);
            }
        });
        floatProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(profile);
            }
        });
        minPorsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (porsi <= 0) {
                    porsi = 0;
                }
                porsi--;
                porsiTxt.setText(porsi.toString());
            }
        });
        plusPorsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                porsi++;
                porsiTxt.setText(porsi.toString());
            }
        });

        /*blck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                porsi = 1;
                clbg.setVisibility(View.GONE);
                floatCart.setVisibility(View.VISIBLE);
                floatProfile.setVisibility(View.VISIBLE);
                setTitle("Menu Makanan");
            }
        });*/
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                strReqAddCart();
                clbg.setVisibility(View.GONE);
                floatCart.setVisibility(View.VISIBLE);
                floatProfile.setVisibility(View.VISIBLE);
                porsi = 1;
            }
        });
        clbg.getBackground().setAlpha(120);


    }

    public void strReqAddCart(){
        idmak = SharedPref.getInstance(getApplicationContext()).getIdMak().getIdMak();
        username = SharedPref.getInstance(getApplicationContext()).getUser().getUsername();
        pors = porsi.toString();

        StringRequest CartReq = new StringRequest(Request.Method.POST, URLs.URL_ADDTOCART, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);

                    if(jObj.getBoolean("error")==false){
                        System.out.println("Berhasil");
                    } else {
                        System.out.println("Gagal");
                    }
                    System.out.println(jObj.getString("query"));
                } catch (JSONException e) {
                    e.printStackTrace();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("addcarterror"+error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> param = new HashMap<String, String>();
                param.put("username",username);
                param.put("idmak",idmak);
                param.put("porsi",pors);
                return param;
            }
        };
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(CartReq);
    }
    public void strReqMenu(String jenis){
        StringRequest menuReq = new StringRequest(Request.Method.POST, URLs.URL_MENU+jenis, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    id = jsonObject.getString("id");
                    name = jsonObject.getString("nama");
                    price = jsonObject.getString("harga");
                    promo = jsonObject.getString("promo");
                    status = jsonObject.getString("status");
                    image = jsonObject.getString("image");
                    userMenu um = new userMenu(id,name,price,promo,status,image);
                    SharedPref.getInstance(getApplicationContext()).saveMenu(um);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(MainMenu.this, "Error(PHP), Hubungi pengembang.", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Gagal mengambil data, mengulangi.", Toast.LENGTH_SHORT).show();
            }
        });
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(menuReq);
    }
    public void fetchMenu(final ConstraintLayout cl,final EditText porsi,final FloatingActionButton profil,final FloatingActionButton cart){
        Runnable fetch = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<2;i++){
                    String param1 = SharedPref.getInstance(getApplicationContext()).getMenu().getNamaMakanan();
                    String param2 = SharedPref.getInstance(getApplicationContext()).getMenu().getHarga();
                    String param3 = SharedPref.getInstance(getApplicationContext()).getMenu().getId();

                    if(param1==null||param2==null||param3==null){
                        SystemClock.sleep(500);
                        if(i==1){
                            i=0;
                        }
                    } else{
                        recyclerViewMak = (RecyclerView)findViewById(R.id.makRcv);
                        recyclerViewMak.setNestedScrollingEnabled(false); //Agar scroll recyclerView smooth, tidak ikut scroll parent

                        final RecyclerAdapterMenu adapter = new RecyclerAdapterMenu(MainMenu.this,cl,recyclerViewMak,porsi,profil,cart);
                        final ProgressBar loading = (ProgressBar)findViewById(R.id.loading);
                        recyclerViewMak.post(new Runnable() {
                            @Override
                            public void run() {
                                recyclerViewMak.setAdapter(adapter);
                                recyclerViewMak.setHasFixedSize(true);
                                recyclerViewMak.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                SystemClock.sleep(500);
//Mencegah agar fetchMenuMin tidak berjalan sebelum fetchMenu selesai.
                                if(recyclerViewMak.getAdapter()!=adapter){
                                    System.out.println("RecyMak adapter belum ada");
                                } else{
                                    System.out.println("RecyMak adapter sudah ada");
                                    SharedPref.getInstance(getApplicationContext()).nullMenu();
                                    new Thread(Min).start();
                                }
                            }
                        });


                    }
                }
            }
        };
        new Thread(fetch).start();
    }
    public void fetchMenuMin(final ConstraintLayout cl,final EditText porsi,final FloatingActionButton profil,final FloatingActionButton cart){
        Runnable fetch = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<2;i++){
                    String param = SharedPref.getInstance(getApplicationContext()).getMenu().getNamaMakanan();
                    String param2 = SharedPref.getInstance(getApplicationContext()).getMenu().getHarga();
                    String param3 = SharedPref.getInstance(getApplicationContext()).getMenu().getPromo();
                    if(param==null&&param2==null&&param3==null){
                        SystemClock.sleep(500);
                        if(i==1){
                            i=0;
                        }
                    } else{
                        recyclerViewMin = (RecyclerView)findViewById(R.id.minRcv);
                        final RecyclerAdapterMenu adapter = new RecyclerAdapterMenu(MainMenu.this,cl,recyclerViewMin,porsi,profil,cart);

                        recyclerViewMin.post(new Runnable() {
                            @Override
                            public void run() {
                                recyclerViewMin.setAdapter(adapter);
                                recyclerViewMin.setHasFixedSize(true);
                                recyclerViewMin.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                            }
                        });
                    }
                }
            }
        };
        new Thread(fetch).start();
    }

    public void onBackPressed(){
        if(clbg.getVisibility()==View.VISIBLE){
            porsi = 1;
            clbg.setVisibility(View.GONE);
            floatCart.setVisibility(View.VISIBLE);
            floatProfile.setVisibility(View.VISIBLE);

        } else{
            finish();
        }
    }

    public void TabSlideFunction(){
        final TabLayout tl = (TabLayout)findViewById(R.id.tabLayout);
        final ViewPager vp = (ViewPager)findViewById(R.id.pager);

        ImageView iv1 = new ImageView(this);
        ImageView iv2 = new ImageView(this);
        String density = Float.toString(getResources().getDisplayMetrics().density);

        iv2.setImageResource(R.drawable.drink);
        iv1.setImageResource(R.drawable.food);

        /*switch (density){
            case "1.0" : iv2.setImageResource(R.drawable.drink);
                iv1.setImageResource(R.drawable.food);
                break;
            case "1.5":iv2.setImageResource(R.drawable.drink_hdpi);
                iv1.setImageResource(R.drawable.food_hdpi);
                break;
            case "3.0":iv2.setImageResource(R.drawable.drink_xxhdpi);
                iv1.setImageResource(R.drawable.food_xxhdpi);
        }*/
        /*if(density==mdpi) {
            iv2.setImageResource(R.drawable.drink);
            iv1.setImageResource(R.drawable.food);
        } else if(density==hdpi){
            iv2.setImageResource(R.drawable.drink_hdpi);
            iv1.setImageResource(R.drawable.food_hdpi);
        } else if(density==xxhdpi){
            iv2.setImageResource(R.drawable.drink_xxhdpi);
            iv1.setImageResource(R.drawable.food_xxhdpi);
        }*/
        iv1.setPadding(10,20,10,20);
        iv2.setPadding(10,20,10,20);

        tl.getTabAt(0).setCustomView(iv1);
        tl.getTabAt(1).setCustomView(iv2);

        tl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
                    vp.setCurrentItem(0,true);
                } else {
                    vp.setCurrentItem(1,true);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(vp.getCurrentItem()==0){
                    tl.getTabAt(0).select();
                } else if(vp.getCurrentItem()==1){
                    tl.getTabAt(1).select();
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
    }
}
package com.project.pudin.myapplication.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.project.pudin.myapplication.SharedPref;
import com.project.pudin.myapplication.URLs;
import com.project.pudin.myapplication.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WIN10 test on 1/11/2018.
 */

public class StringReq {
    Context mContext;
    String lstPesanan,idPesanan;
    public StringReq(Context context){

        mContext = context;
    }



    public String getLstP(){
        return lstPesanan;
    }

    public int getItem(){
        return 2;
    }
}

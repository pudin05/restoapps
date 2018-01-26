package com.project.pudin.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.project.pudin.myapplication.util.IdMakUser;
import com.project.pudin.myapplication.util.User;
import com.project.pudin.myapplication.util.oldValueUser;
import com.project.pudin.myapplication.util.userMenu;
import com.project.pudin.myapplication.util.keranjangUser;

/**
 * Created by WIN10 test on 11/29/2017.
 */

public class SharedPref {
    private static final String PREF_NAME = "depot_project";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_IDUSER = "iduser";
    private static final String KEY_PROMOMENU = "promomenu";
    private static final String KEY_STATUSMENU = "statusmenu";
    private static final String KEY_IDMENU = "idmenu";
    private static final String KEY_MENU = "menu";
    private static final String KEY_HARGA = "harga";
    private static final String KEY_IDMAK = "idmak";
    private static final String KEY_IMAGE = "image";
    private static final String KEY_IDMAK_K = "idmakk";
    private static final String KEY_NAMAMAK_K = "namamak";
    private static final String KEY_PORSIMAK_K = "porsimak";
    private static SharedPref mInstance;
    private static Context mCtx;

    private SharedPref (Context context){
        mCtx = context;
    }

    public static synchronized SharedPref getInstance(Context context){
        if(mInstance == null){
            mInstance = new SharedPref(context);
        }
        return mInstance;
    }

    public void userLogin(User user){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_USERNAME,user.getUsername());
        editor.putString(KEY_IDUSER,user.getId());
        editor.apply();
    }

    public void saveMenu(userMenu menu){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_IDMENU,menu.getId());
        editor.putString(KEY_MENU,menu.getNamaMakanan());
        editor.putString(KEY_HARGA,menu.getHarga());
        editor.putString(KEY_PROMOMENU,menu.getPromo());
        editor.putString(KEY_STATUSMENU,menu.getStatus());
        editor.putString(KEY_IMAGE,menu.getImage());
        editor.apply();
    }

    public void saveIdMak(IdMakUser idMak){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_IDMAK,idMak.getIdMak());
        editor.apply();
    }

    public void saveOldValue(oldValueUser old){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_IDMAK_K,old.getIdmak());
        editor.putString(KEY_PORSIMAK_K, old.getPorsi());
        editor.apply();
    }

    public void saveKeranjang(keranjangUser cart){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_IDMAK_K,cart.getIdmak());
        editor.putString(KEY_NAMAMAK_K,cart.getNama());
        editor.putString(KEY_PORSIMAK_K,cart.getPorsi());
        editor.apply();
    }

    public void nullMenu(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_MENU);
        editor.remove(KEY_HARGA);
        editor.remove(KEY_PROMOMENU);
        editor.apply();
    }

    public void nullKeranjang(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_IDMAK_K);
        editor.remove(KEY_NAMAMAK_K);
        editor.remove(KEY_PORSIMAK_K);
        editor.apply();
    }

    public void nullUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_USERNAME);
        editor.remove(KEY_IDUSER);
        editor.apply();
    }

    public void nullAll(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public User getUser(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_USERNAME, null),sharedPreferences.getString(KEY_IDUSER,null)
        );
    }

    public userMenu getMenu(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return new userMenu(
                sharedPreferences.getString(KEY_IDMENU,null),sharedPreferences.getString(KEY_MENU, null),sharedPreferences.getString(KEY_HARGA,null),sharedPreferences.getString(KEY_PROMOMENU,null),sharedPreferences.getString(KEY_STATUSMENU,null),sharedPreferences.getString(KEY_IMAGE, null)
        );
    }

    public IdMakUser getIdMak(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return new IdMakUser(
                sharedPreferences.getString(KEY_IDMAK, null)
        );
    }

    public oldValueUser getOldValue(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return new oldValueUser(
                sharedPreferences.getString(KEY_IDMAK_K,null),sharedPreferences.getString(KEY_PORSIMAK_K, null)
        );
    }

    public keranjangUser getKeranjang(){
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        return new keranjangUser(
                sharedPreferences.getString(KEY_IDMAK_K, null),sharedPreferences.getString(KEY_NAMAMAK_K, null),sharedPreferences.getString(KEY_PORSIMAK_K, null)
        );
    }

}

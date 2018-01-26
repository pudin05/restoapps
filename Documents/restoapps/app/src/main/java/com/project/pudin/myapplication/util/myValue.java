package com.project.pudin.myapplication.util;

import android.graphics.drawable.Drawable;

import com.project.pudin.myapplication.R;

/**
 * Created by WIN10 test on 1/20/2018.
 */

public class myValue {
    int num;

    public myValue(int num){
        this.num = num;
    }

    public String getStatuspesanan(){
        String str = "Menunggu";
        switch (num){
            case 0:
                str = "Menunggu";
                break;
            case 1:
                str = "Sedang Dimasak";
                break;
            case 2:
                str = "Mengantar";
                break;
        } return str;
    }
    public int setMyImage(){
        int img = R.drawable.hg;

        switch (num){
            case 0:
                img = R.drawable.hg;
                break;
            case 1:
                img = R.drawable.cook;
                break;
            case 2:
                img = R.drawable.avatar_bg;
                break;
        } return img;
    }
}

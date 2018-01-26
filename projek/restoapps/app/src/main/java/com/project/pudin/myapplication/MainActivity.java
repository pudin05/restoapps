package com.project.pudin.myapplication;

import android.content.Intent;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        setContentView(R.layout.activity_main);
        TextView txt1 = (TextView)findViewById(R.id.txt1);
        ConstraintLayout cl = (ConstraintLayout)findViewById(R.id.cl1);
        TextView tiptxt = (TextView)findViewById(R.id.Tip);
        anim(cl,"move");
        anim(txt1,"fade");
        tip(tiptxt,blink);
        sessionUser();

    }

    public void anim(View view,String param){
        Animation move = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move_top_to_bott);
        Animation blink = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.blink);
        Animation bounce = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.bounce);
        Animation fade = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade);
        if(param == "move"){
            view.startAnimation(move);
        }
        else if(param == "blink"){
            view.startAnimation(blink);
        }
        else if(param == "bounce"){
            view.startAnimation(bounce);
        }
        else if(param == "fade"){
            view.startAnimation(fade);
        }
    }

    public void tip(final TextView tv,final Animation anim){
        final String[] tList = "Buatlah akun ,agar anda terUpdate tentang promo - promo restoran.;Restoran ini berdiri sejak 1987, didirikan oleh bapak Khodir.;Tahukah anda? ,Soto ternyata makanan yang enak.".split(";");
        final int[] z = {0};
        Runnable tip = new Runnable() {
            @Override
            public void run() {
                for(int i=0;i<tList.length+1;i++){
                    z[0] = i-1;
                        tv.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.addTextChangedListener(new TextWatcher() {
                                    @Override
                                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                    }

                                    @Override
                                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                                        tv.startAnimation(anim);
                                    }

                                    @Override
                                    public void afterTextChanged(Editable editable) {

                                    }
                                });
                            }
                        });
                    if(i==tList.length){
                        i=0;
                    }
                    SystemClock.sleep(5000);
                }
            }
        };
        new Thread(tip).start();
    }

    public void sessionUser() {

        Button lanjut = (Button)findViewById(R.id.next);
        String user = SharedPref.getInstance(this).getUser().getUsername();
        if(user!=null){
            lanjut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(MainActivity.this, MainMenu.class));
                    overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
                }
            });
        } else{
            startActivity(new Intent (MainActivity.this, Select.class));
            overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_left);
        }
    }
}

package com.project.pudin.myapplication.FragmentSlide;

import android.view.View;

import com.project.pudin.myapplication.R;

/**
 * Created by WIN10 test on 1/9/2018.
 */

public enum ModelObject {

    MAK(1,R.layout.fragment_slide),
    MIN(2,R.layout.fragment_slide2);

    private int mTitleResId;
    private int mLayoutResId;

    ModelObject(int titleResId, int layoutResId) {
        mTitleResId = titleResId;
        mLayoutResId = layoutResId;
    }

    public int getTitleResId() {
        return mTitleResId;
    }

    public int getLayoutResId() {
        return mLayoutResId;
    }
}

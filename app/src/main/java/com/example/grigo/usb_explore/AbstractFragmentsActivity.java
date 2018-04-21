package com.example.grigo.usb_explore;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

/**
 * Created by Jake on 27/02/2018.
 */

public abstract class AbstractFragmentsActivity extends FragmentActivity {

    private final int layout;

    protected abstract void onPageChanged(int page);
    protected AbstractFragmentsActivity(int layout) {
        this.layout = layout;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout);
    }

    @Override
    protected void onResume() {
        super.onResume();
        onPageChanged(1);
    }
}

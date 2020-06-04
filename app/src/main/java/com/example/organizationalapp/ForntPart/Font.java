package com.example.organizationalapp.ForntPart;

import android.app.Application;

import androidx.appcompat.widget.AppCompatTextView;

import com.example.organizationalapp.*;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
public class Font extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/iransans.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());
        //....
    }
}
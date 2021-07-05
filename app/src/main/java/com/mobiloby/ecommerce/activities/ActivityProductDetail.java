package com.mobiloby.ecommerce.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.mobiloby.ecommerce.R;
import com.mobiloby.ecommerce.adapters.ViewPagerAdapter;

import java.util.ArrayList;

import id.rizmaulana.floatingslideupsheet.view.FloatingSlideUpBuilder;

public class ActivityProductDetail extends AppCompatActivity {

    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    LinearLayout sliderDotspanel;
    private int dotscount;
    private ImageView[] dots;
    ArrayList<Integer> images;
    LinearLayout bottomSheet;
    ConstraintLayout bottomLayout;
    private GestureDetector mDetector;
    FloatingSlideUpBuilder floatingSlideUpSheet;
    ViewGroup viewGroup;
    ConstraintLayout container_floating_menu;
    boolean isButtonCliked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        prepareMe();


        floatingSlideUpSheet = new FloatingSlideUpBuilder(this, viewGroup)
                .setFloatingMenuRadiusInDp(15)
                .setFloatingMenuColor(android.R.color.white)
                .setFloatingMenu(container_floating_menu)
                .setPanel(bottomLayout)
                .build();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for(int i = 0; i< dotscount; i++){
                    dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));
                }

                dots[position].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    private void prepareMe() {

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));// set status background white

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        images = new ArrayList<>();
        images.add(R.drawable.o11);
        images.add(R.drawable.o12);
        images.add(R.drawable.o13);
        viewPagerAdapter = new ViewPagerAdapter(this, images);
        viewPager.setAdapter(viewPagerAdapter);
        dotscount = 3;

        sliderDotspanel = (LinearLayout) findViewById(R.id.SliderDots);
        dotscount = viewPagerAdapter.getCount();
        dots = new ImageView[dotscount];

        for(int i = 0; i < dotscount; i++) {

            dots[i] = new ImageView(this);
            dots[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.non_active_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(8, 0, 8, 0);

            sliderDotspanel.addView(dots[i], params);
        }

        dots[0].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.active_dot));

        bottomLayout = findViewById(R.id.bottomLayout);
        viewGroup = findViewById(R.id.bottomLayout2);
        container_floating_menu = findViewById(R.id.container_floating_menu);
        bottomLayout = findViewById(R.id.bottomLayout);


    }

    public void clickAddToBag(View view) {
        if(!isButtonCliked){
            floatingSlideUpSheet.expandBottomSheet();
            isButtonCliked = true;
        }
    }

    public void clickBack(View view) {
        finish();
    }
}
package com.mobiloby.ecommerce.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.mobiloby.ecommerce.R;
import com.mobiloby.ecommerce.adapters.MyMagazineAdapter;
import com.mobiloby.ecommerce.adapters.MyProductAdapter;
import com.mobiloby.ecommerce.adapters.MySortAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyMagazineAdapter.ItemClickListenerSub, MyProductAdapter.ItemClickListenerProducts, MySortAdapter.ItemClickListenerSort{

    RecyclerView recyclerView_magazine, recyclerView_products;
    MyMagazineAdapter adapterMagazines;
    MyProductAdapter adapterProducts;
    ArrayList<String> magazineList;
    ArrayList<Integer> productList;
    RecyclerView.LayoutManager layoutManager, layoutManagerGrid;
    public static int index_magazine = 0;
    BottomSheetDialog bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prepareMe();
    }

    private void prepareMe() {

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.white));// set status background white

        recyclerView_magazine = findViewById(R.id.recyclerview_magazines);
        productList = new ArrayList<>();
        magazineList = new ArrayList<>();
        magazineList.add("T-Shirt");
        magazineList.add("Dress");
        magazineList.add("Make-up");
        magazineList.add("Shirt");
        magazineList.add("Blouse");
        magazineList.add("Coat");
        magazineList.add("Sweatshirt");
        magazineList.add("Trousers");
        magazineList.add("Jeans");
        magazineList.add("Uniform");
        magazineList.add("Watches");
        magazineList.add("Bracelets");

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        adapterMagazines = new MyMagazineAdapter(this, magazineList);
        recyclerView_magazine.setLayoutManager(layoutManager);
        recyclerView_magazine.setAdapter(adapterMagazines);
        adapterMagazines.setClickListenerSub(this);

        layoutManagerGrid = new GridLayoutManager(this, 2);
        productList.clear();
        for(int i=0;i<5;i++){
            productList.add(R.drawable.hm1);
            productList.add(R.drawable.hm2);
            productList.add(R.drawable.hm3);
            productList.add(R.drawable.hm);
            productList.add(R.drawable.hm4);
        }
        adapterProducts = new MyProductAdapter(this, productList);
        recyclerView_products = findViewById(R.id.recyclerview_products);
        recyclerView_products.setLayoutManager(layoutManagerGrid);
        recyclerView_products.setAdapter(adapterProducts);
        adapterProducts.setClickListenerProducts(this);
    }

    @Override
    public void onItemClick(View view, int position, ArrayList<String> list) {
        MyMagazineAdapter.lastIndex = position;

        if(position==5){
            productList.clear();
            for(int i=0;i<4;i++){
                productList.add(R.drawable.sweatshirt1);
                productList.add(R.drawable.sweatshirt2);
                productList.add(R.drawable.sweatshirt3);
                productList.add(R.drawable.sweatshirt4);
                productList.add(R.drawable.sweatshirt5);
            }
        }
        else if(position==1){
            productList.clear();
            for(int i=0;i<5;i++){
                productList.add(R.drawable.o11);
                productList.add(R.drawable.o21);
                productList.add(R.drawable.o11);
            }
        }
        else{
            productList.clear();
            for(int i=0;i<5;i++){
                productList.add(R.drawable.hm);
                productList.add(R.drawable.hm1);
                productList.add(R.drawable.hm2);
                productList.add(R.drawable.hm3);
                productList.add(R.drawable.hm4);
            }
        }

        adapterMagazines.notifyDataSetChanged();
        adapterProducts.notifyDataSetChanged();
    }

    public void clickDrawerMenu(View view){

    }

    @Override
    public void onItemClickProduct(View view, int position, ArrayList<Integer> list) {

        Toast.makeText(this, "click", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(this, ActivityProductDetail.class);
        startActivity(intent);

    }

    public void clickEmptySpace(View view) {

    }

    public void clickFilter(View view) {
        popupFilter();
    }

    public void popupSort(){
        bd = new BottomSheetDialog(this);
        View view;
        view = LayoutInflater.from(this).inflate(R.layout.custom_layout_sort, null);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview_sort);
        ArrayList<String> sortTitles = new ArrayList<>();
        sortTitles.add("Meşhurlar");
        sortTitles.add("Iň täzeler");
        sortTitles.add("Halananlar");
        sortTitles.add("Iň köp satylanlar");
        sortTitles.add("Baha: arzandan gymmada");
        sortTitles.add("Baha: gymmatdan arzana");
        MySortAdapter adapter = new MySortAdapter(MainActivity.this, sortTitles);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        adapter.setClickListenerSort(this);

        bd.setContentView(view);
        bd.setDismissWithAnimation(true);
        bd.show();
    }

    public void popupFilter(){
        bd = new BottomSheetDialog(this);
        View view;
        view = LayoutInflater.from(this).inflate(R.layout.custom_layout_filter, null);

        bd.setContentView(view);
        bd.setDismissWithAnimation(true);
        bd.show();
    }

    public void clickSort(View view) {
        popupSort();
    }

    public void clickShowResults(View view) {
        bd.dismiss();
    }
}
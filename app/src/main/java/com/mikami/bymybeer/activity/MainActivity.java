package com.mikami.bymybeer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mikami.bymybeer.adapter.BeerAdapter;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.R;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mainRecyclerView;

    private FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ArrayList<BeerModel> beerList = new ArrayList<>();
        beerList.add(new BeerModel(0, "Beer0", "0.jpg", "Light", BigDecimal.valueOf(4.5)));
        beerList.add(new BeerModel(1, "Beer1", "1.jpg", "Light", BigDecimal.valueOf(4.5)));
        beerList.add(new BeerModel(2, "Beer2", "2.jpg", "Light", BigDecimal.valueOf(4.5)));
        beerList.add(new BeerModel(3, "Beer3", "3.jpg", "Light", BigDecimal.valueOf(4.5)));
        beerList.add(new BeerModel(4, "Beer4", "4.jpg", "Light", BigDecimal.valueOf(4.5)));
        beerList.add(new BeerModel(5, "Beer5", "5.jpg", "Light", BigDecimal.valueOf(4.5)));
        beerList.add(new BeerModel(6, "Beer6", "6.jpg", "Light", BigDecimal.valueOf(4.5)));
        beerList.add(new BeerModel(7, "Beer7", "7.jpg", "Light", BigDecimal.valueOf(4.5)));
        beerList.add(new BeerModel(8, "Beer8", "8.jpg", "Light", BigDecimal.valueOf(4.5)));
        beerList.add(new BeerModel(9, "Beer9", "9.jpg", "Light", BigDecimal.valueOf(4.5)));

        mainRecyclerView = findViewById(R.id.mainRecycler);
        mainRecyclerView.setHasFixedSize(true);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(new BeerAdapter(beerList));

        actionButton = findViewById(R.id.mainAddButton);
        actionButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mainAddButton :
                startActivity(new Intent(this, AddActivity.class));
            break;
        }
    }
}

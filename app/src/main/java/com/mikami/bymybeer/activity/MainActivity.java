package com.mikami.bymybeer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RatingBar;

import com.mikami.bymybeer.adapter.BeerAdapter;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.R;
import com.mikami.bymybeer.model.PriceModel;
import com.mikami.bymybeer.model.RatingModel;

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
        beerList.add(new BeerModel(0, "Gold mine beer", "0.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(1, "Три медведя", "1.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(2, "Жигулевское", "2.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(3, "Старый мельник", "3.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(4, "Балтика", "4.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(5, "Жигули", "5.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(6, "Клинское", "6.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(7, "Белый медведь", "7.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(8, "Арсенальное", "8.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(9, "Очаково", "9.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(10, "Gold mine beer", "0.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(11, "Три медведя", "1.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(12, "Жигулевское", "2.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(13, "Старый мельник", "3.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(14, "Балтика", "4.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(15, "Жигули", "5.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(16, "Клинское", "6.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(17, "Белый медведь", "7.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(18, "Арсенальное", "8.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));
        beerList.add(new BeerModel(19, "Очаково", "9.jpg", "Light", 3.5f, new PriceModel(50, 0.5f), new RatingModel(2.5f)));

        mainRecyclerView = findViewById(R.id.mainRecycler);
        mainRecyclerView.setHasFixedSize(true);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainRecyclerView.setAdapter(new BeerAdapter(this, beerList));

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

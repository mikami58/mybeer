package com.mikami.bymybeer.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;

import com.mikami.bymybeer.Adapter.BeerGridAdapter;
import com.mikami.bymybeer.Database.DBHelper;
import com.mikami.bymybeer.Domain.Beer.Beer;
import com.mikami.bymybeer.Domain.Beer.BeerQueries;
import com.mikami.bymybeer.R;

import java.util.ArrayList;
import java.util.Collection;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DBHelper dbHelper;
    private GridView gridView;
    private BeerGridAdapter gridAdapter;
    private FloatingActionButton actionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        dbHelper.onUpgrade(db, 2, 3);

        Collection<Beer> beers = BeerQueries.read(db);

        gridView = findViewById(R.id.mainGridView);
        gridAdapter = new BeerGridAdapter(this, R.layout.card_view_layout, (ArrayList<Beer>) beers);
        gridView.setAdapter(gridAdapter);

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

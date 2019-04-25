package com.mikami.bymybeer.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import com.mikami.bymybeer.R;
import com.mikami.bymybeer.adapter.BeerAdapter;
import com.mikami.bymybeer.utility.DataProvider;

import java.util.stream.Stream;

import static android.Manifest.permission.*;
import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mainRecyclerView;

    private BeerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadElements();
        loadToolbar();
        if (ensurePermissions()) {
            loadData();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        loadData();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.mainAddButton) {
            startActivityForResult(new Intent(this, AddActivity.class), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        adapter.reload();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    private void loadElements() {
        setContentView(R.layout.activity_main);

        mainRecyclerView = findViewById(R.id.mainRecycler);
        mainRecyclerView.setHasFixedSize(true);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton actionButton = findViewById(R.id.mainAddButton);
        actionButton.setOnClickListener(this);
    }

    private void loadToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
    }

    private void loadData() {
        adapter = new BeerAdapter(this, DataProvider.getInstance());
        mainRecyclerView.setAdapter(adapter);
    }

    private boolean ensurePermissions() {
        String[] permissions = {READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, CAMERA};
        boolean permissionsGranted = Stream.of(permissions).allMatch(this::checkPermission);
        if (!permissionsGranted) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
        return permissionsGranted;
    }

    private boolean checkPermission(String permission) {
        return ContextCompat.checkSelfPermission(this.getApplicationContext(), permission) == PERMISSION_GRANTED;
    }
}

package com.mikami.bymybeer.utility;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikami.bymybeer.model.BeerModel;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public final class DataProvider {

    private static final String TAG = "DataProvider";

    private static final String FILE = "beers.txt";

    private static DataProvider instance;

    private Gson gson;

    private ArrayList<BeerModel> beerList;

    private int currentMax;

    private DataProvider() {
        gson = new GsonBuilder().create();
        File file = FileService.getFile(FILE);
    }

    public static DataProvider getInstance() {
        if (instance == null) {
            instance = new DataProvider();
        }
        return instance;
    }

    public void save(ArrayList<BeerModel> beerList) {
        File file = FileService.getFile(FILE);
        file.mkdirs();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(gson.toJson(beerList));
            Log.d(TAG, "List was saved");
        } catch (IOException e) {

        }
    }
}

package com.mikami.bymybeer.utility;

import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mikami.bymybeer.model.BeerModel;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public final class DataProvider {

    private static final String TAG = "DataProvider";

    private static final String JSON_FILE_NAME = "beers.json";

    private static DataProvider instance;

    private final File jsonFile;

    private final Gson gson;

    private final List<BeerModel> beerList;

    private long currentMax;

    private DataProvider() {
        gson = new GsonBuilder().create();
        jsonFile = FileService.getFile(JSON_FILE_NAME);
        jsonFile.mkdirs();
        beerList = new ArrayList<>();
        load();
    }

    public static DataProvider getInstance() {
        if (instance == null)
            instance = new DataProvider();
        return instance;
    }

    public List<BeerModel> getBeerList() {
        return beerList;
    }

    public void addAndSave(BeerModel model) {
        model.setId(++currentMax);
        model.setCreated(new Date());
        beerList.add(model);
        save();
    }

    private void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(jsonFile))) {
            BeerModel[] models = gson.fromJson(reader, BeerModel[].class);
            beerList.addAll(Arrays.asList(models));
            currentMax = beerList.stream().mapToLong(BeerModel::getId).max().orElse(0);
            Log.d(TAG, "List was saved");
        } catch (Exception e) {
            Log.d(TAG, "Exception occurred while loading list");
        }
    }

    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile))) {
            writer.write(gson.toJson(beerList));
            Log.d(TAG, "List was saved");
        } catch (IOException e) {
            Log.d(TAG, "Exception occurred while saving list");
        }
    }
}

package com.mikami.bymybeer.activity;

import android.os.Bundle;
import com.mikami.bymybeer.model.BeerModel;
import com.mikami.bymybeer.utility.DataProvider;

import static com.mikami.bymybeer.utility.Constants.EXTRA_DEFAULT;
import static com.mikami.bymybeer.utility.Constants.EXTRA_NAME;

public class ModifyActivity extends MutableActivity {

    private long itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BeerModel model = extractDataFromExtra();
        setDataFromModel(model);
        itemId = model.getId();
    }

    @Override
    void makeAction() {
        DataProvider.getInstance().modifyAndSave(itemId, getModel());
    }

    private BeerModel extractDataFromExtra() {
        if (getIntent().hasExtra(EXTRA_NAME)) {
            long id = getIntent().getLongExtra(EXTRA_NAME, EXTRA_DEFAULT);
            return DataProvider.getInstance().findById(id);
        } else {
            throw new RuntimeException("Item was not present in intent");
        }
    }
}

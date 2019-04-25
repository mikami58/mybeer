package com.mikami.bymybeer.activity;

import com.mikami.bymybeer.utility.DataProvider;

public class AddActivity extends MutableActivity {

    @Override
    void makeAction() {
        DataProvider.getInstance().addAndSave(getModel());
    }
}

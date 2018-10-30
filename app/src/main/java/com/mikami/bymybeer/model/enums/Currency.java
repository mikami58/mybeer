package com.mikami.bymybeer.model.enums;

import com.mikami.bymybeer.R;

/**
 * Supported currency enum
 */
public enum Currency {

    /**
     * Russian rubles
     */
    RUB(R.string.rub),

    /**
     * United States dollars
     */
    USD(R.string.usd),

    /**
     * Euro
     */
    EUR(R.string.eur);

    private int mResourceId;

    Currency(int id) {
        mResourceId = id;
    }

    public int getResourceId() {
        return mResourceId;
    }
}

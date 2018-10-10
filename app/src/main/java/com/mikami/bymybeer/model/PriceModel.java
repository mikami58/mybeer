package com.mikami.bymybeer.model;

import com.mikami.bymybeer.model.enumerated.Currency;

import java.io.Serializable;

/**
 * Beer price model
 */
public class PriceModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private float price;

    private float volume;

    private Currency currency;

    public PriceModel() { }

    public PriceModel(float price, float volume) {
        this.price = price;
        this.volume = volume;
        this.currency = Currency.RUB;
    }

    /**
     * @return Price of one bottle
     */
    public float getPrice() { return price; }

    public void setPrice(float price) { this.price = price; }

    /**
     * @return Bottle volume
     */
    public float getVolume() { return volume; }

    public void setVolume(float volume) { this.volume = volume; }

    /**
     * @return Price currency
     */
    public Currency getCurrency() { return currency; }

    public void setCurrency(Currency currency) { this.currency = currency; }
}

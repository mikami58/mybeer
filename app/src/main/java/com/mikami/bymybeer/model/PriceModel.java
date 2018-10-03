package com.mikami.bymybeer.model;

import com.mikami.bymybeer.model.enumerated.Currency;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Beer price model
 */
public class PriceModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private BigDecimal price;

    private BigDecimal volume;

    private Currency currency;

    /**
     * @return Price of one bottle
     */
    public BigDecimal getPrice() { return price; }

    public void setPrice(BigDecimal price) { this.price = price; }

    /**
     * @return Bottle volume
     */
    public BigDecimal getVolume() { return volume; }

    public void setVolume(BigDecimal volume) { this.volume = volume; }

    /**
     * @return Price currency
     */
    public Currency getCurrency() { return currency; }

    public void setCurrency(Currency currency) { this.currency = currency; }
}

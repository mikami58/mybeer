package com.mikami.bymybeer.model;

import java.io.Serializable;

/**
 * Beer rating model
 */
public class RatingModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int cost;

    private int bitterness;

    private int taste;

    private int saturation;

    private int alcohol;

    /**
     * @return Beer cost rating
     */
    public int getCost() { return cost; }

    public void setCost(int cost) { this.cost = cost; }

    /**
     * @return Beer bitterness rating
     */
    public int getBitterness() { return bitterness; }

    public void setBitterness(int bitterness) { this.bitterness = bitterness; }

    /**
     * @return Beer taste rating
     */
    public int getTaste() { return taste; }

    public void setTaste(int taste) { this.taste = taste; }

    /**
     * @return Beer taste saturation rating
     */
    public int getSaturation() { return saturation; }

    public void setSaturation(int saturation) { this.saturation = saturation; }

    /**
     * @return Beer alcohol strength rating
     */
    public int getAlcohol() { return alcohol; }

    public void setAlcohol(int alcohol) { this.alcohol = alcohol; }
}

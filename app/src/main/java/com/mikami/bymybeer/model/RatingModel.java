package com.mikami.bymybeer.model;

import java.io.Serializable;

/**
 * Beer rating model
 */
public class RatingModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private static final int RATING_CRITERIA_COUNT = 5;

    private float cost;

    private float bitterness;

    private float taste;

    private float saturation;

    private float alcohol;

    public RatingModel() { }

    public RatingModel(float average) {
        this.cost = average;
        this.bitterness = average;
        this.taste = average;
        this.saturation = average;
        this.alcohol = average;
    }

    /**
     * @return Beer cost rating
     */
    public float getCost() { return cost; }

    public void setCost(float cost) { this.cost = cost; }

    /**
     * @return Beer bitterness rating
     */
    public float getBitterness() { return bitterness; }

    public void setBitterness(float bitterness) { this.bitterness = bitterness; }

    /**
     * @return Beer taste rating
     */
    public float getTaste() { return taste; }

    public void setTaste(float taste) { this.taste = taste; }

    /**
     * @return Beer taste saturation rating
     */
    public float getSaturation() { return saturation; }

    public void setSaturation(float saturation) { this.saturation = saturation; }

    /**
     * @return Beer alcohol strength rating
     */
    public float getAlcohol() { return alcohol; }

    public void setAlcohol(float alcohol) { this.alcohol = alcohol; }

    public float getAverage() {
        return (getCost() + getBitterness() + getTaste() + getSaturation() + getAlcohol()) / RATING_CRITERIA_COUNT;
    }
}

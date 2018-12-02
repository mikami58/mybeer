package com.mikami.bymybeer.model;

import java.io.Serializable;
import java.time.Instant;

/**
 * Beer model
 */
public class BeerModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private Instant created;

    private String name;

    private String imageName;

    private String type;

    private float alcoholPercentage;

    private PriceModel beerPrice;

    private RatingModel rating;

    private String note;

    public BeerModel() { }

    public BeerModel(long id, String name, String imageName, String type,
                     float alcoholPercentage, PriceModel beerPrice, RatingModel rating) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.type = type;
        this.alcoholPercentage = alcoholPercentage;
        this.beerPrice = beerPrice;
        this.rating = rating;
        this.note = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
    }

    /**
     * @return Beer identifier
     */
    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    /**
     * @return Date and time of review creation
     */
    public Instant getCreated() { return created; }

    public void setCreated(Instant created) { this.created = created; }

    /**
     * @return Beer name
     */
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    /**
     * @return Beer image name
     */
    public String getImageName() { return imageName; }

    public void setImageName(String imageName) { this.imageName = imageName; }

    /**
     * @return Beer type
     */
    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    /**
     * @return Beer alcohol percentage
     */
    public float getAlcoholPercentage() { return alcoholPercentage; }

    public void setAlcoholPercentage(float alcoholPercentage) { this.alcoholPercentage = alcoholPercentage; }

    /**
     * @return Beer price
     */
    public PriceModel getBeerPrice() { return beerPrice; }

    public void setBeerPrice(PriceModel beerPrice) { this.beerPrice = beerPrice; }

    /**
     * @return Beer rating
     */
    public RatingModel getRating() { return rating; }

    public void setRating(RatingModel rating) { this.rating = rating; }

    /**
     * @return Review note
     */
    public String getNote() { return note; }

    public void setNote(String note) { this.note = note; }
}

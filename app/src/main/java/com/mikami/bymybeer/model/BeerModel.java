package com.mikami.bymybeer.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

/**
 * Beer model
 */
public class BeerModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private Instant created;

    private String title;

    private String imageName;

    private String type;

    private BigDecimal alcoholPercentage;

    private PriceModel beerPrice;

    private RatingModel rating;

    private String note;

    public BeerModel() { }

    public BeerModel(long id, String title, String imageName, String type, BigDecimal alcoholPercentage) {
        this.id = id;
        this.title = title;
        this.imageName = imageName;
        this.type = type;
        this.alcoholPercentage = alcoholPercentage;
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
    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

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
    public BigDecimal getAlcoholPercentage() { return alcoholPercentage; }

    public void setAlcoholPercentage(BigDecimal alcoholPercentage) { this.alcoholPercentage = alcoholPercentage; }

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

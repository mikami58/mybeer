package com.mikami.bymybeer.Domain.Photo;

public class Photo {

    private long id;

    private long beer_id;

    private String path;

    public Photo(long id, long beer_id, String path) {
        this.id = id;
        this.beer_id = beer_id;
        this.path = path;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getBeer_id() {
        return beer_id;
    }

    public void setBeer_id(long beer_id) {
        this.beer_id = beer_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

package com.mikami.bymybeer.Domain.Beer;

import com.mikami.bymybeer.Domain.Photo.Photo;

public class Beer {

    private long id;

    private String name;

    private Photo photo;

    private int taste;

    public Beer(long id, String name, Photo photo, int taste) {
        this.id = id;
        this.name = name;
        this.photo = photo;
        this.taste = taste;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTaste() {
        return taste;
    }

    public void setTaste(int taste) {
        this.taste = taste;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}

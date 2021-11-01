package com.example.fitnessapp.Domain;

import java.io.Serializable;

public class PlanDomain implements Serializable {
    private String title;
    private String pic;
    private String description;
    private Double fee;
    private int numberInCart;

    public PlanDomain(String title, String pic, String description) {
        this.title = title;
        this.pic = pic;
        this.description = description;

    }

    public PlanDomain(String title, String pic, String description, int numberInCart) {
        this.title = title;
        this.pic = pic;
        this.description = description;
        this.numberInCart = numberInCart;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



    public int getNumberInCart() {
        return numberInCart;
    }

    public void setNumberInCart(int numberInCart) {
        this.numberInCart = numberInCart;
    }
}

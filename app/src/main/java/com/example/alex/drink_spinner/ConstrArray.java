package com.example.alex.drink_spinner;

/**
 * Created by Alex on 10/05/2017.
 */

public class ConstrArray {

    String text;
    Integer imageId;

    public ConstrArray(String text, Integer imageId){
        this.text=text;
        this.imageId=imageId;
    }

    public String getText(){
        return text;
    }

    public Integer getImageId(){
        return imageId;
    }
}

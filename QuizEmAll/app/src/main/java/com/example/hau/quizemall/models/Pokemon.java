package com.example.hau.quizemall.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hau on 20/11/2016.
 */
public class Pokemon {
    private int id;
    private String name;
    private String tag;
    private int gen;
    private String image;
    private String color;

    public Pokemon(int id, String name, String tag, int gen, String image, String color) {
        this.id = id;
        this.name = name;
        this.tag = tag;
        this.gen = gen;
        this.image = image;
        this.color = color;
    }

    public Pokemon(String name, String tag, int gen, String image, String color) {
        this(-1, name, tag, gen, image, color);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTag() {
        return tag;
    }

    public int getGen() {
        return gen;
    }

    public String getImage() {
        return image;
    }

    public String getColor() {
        return color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setGen(int gen) {
        this.gen = gen;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public static List<Pokemon> list = new ArrayList<>();
}

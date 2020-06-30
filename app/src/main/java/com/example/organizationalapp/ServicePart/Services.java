package com.example.organizationalapp.ServicePart;

import com.google.gson.annotations.SerializedName;

public class Services {
    @SerializedName("id")
     int id;
    @SerializedName("name")
     String name;
     int img;
     int color;
    @SerializedName("accesslevel")
     String accesslevel;

    @SerializedName("parent")
     String parent;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(String accesslevel) {
        this.accesslevel = accesslevel;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}

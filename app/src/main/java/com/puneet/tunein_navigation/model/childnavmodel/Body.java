package com.puneet.tunein_navigation.model.childnavmodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Body implements Serializable {

    public Body() {

    }

    private String element;

    private String text;

    @SerializedName("guide_id")
    private String key;

    private String URL;

    private List<Children> children = new ArrayList<>();

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public List<Children> getChildren() {
        return children;
    }

    public boolean hasChildren() {
        return (this.children.isEmpty() || this.children == null) ? false : true;
    }

    public void setChildren(List<Children> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "Body{" +
                "element='" + element + '\'' +
                ", text='" + text + '\'' +
                ", key='" + key + '\'' +
                ", children=" + children +
                '}';
    }
}

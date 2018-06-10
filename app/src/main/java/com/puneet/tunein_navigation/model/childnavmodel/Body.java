package com.puneet.tunein_navigation.model.childnavmodel;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Body implements Serializable {

    private String element;

    private String text;

    @SerializedName("guide_id")
    private String key;

    private String URL;

    private List<Children> children = new ArrayList<>();


    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
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
